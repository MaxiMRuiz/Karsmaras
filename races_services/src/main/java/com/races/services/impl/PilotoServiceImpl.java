package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.LoginResponse;
import com.races.dto.PilotoDto;
import com.races.entity.Piloto;
import com.races.repository.PilotoRepository;
import com.races.services.JwtService;
import com.races.services.PilotoService;

/**
 * Implementacion de la interfaz PilotoService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("PilotoService")
public class PilotoServiceImpl implements PilotoService {

	private static final String NOT_FOUND_DRIVER = "Piloto no encontrado";

	@Autowired
	@Qualifier("PilotoRepository")
	PilotoRepository pilotoRepo;

	@Autowired
	JwtService jwtService;

	public Piloto crearPiloto(PilotoDto pilotoDto) throws RacesException {

		try {
			Piloto piloto = new Piloto(pilotoDto);
			if (pilotoRepo.findOne(Example.of(piloto)).isPresent()) {
				throw new RacesException("Piloto duplicado");
			}

			String jwk = jwtService.encryptData(
					jwtService.encodeBase64(jwtService.getJWK(pilotoDto.getApodo())));

			piloto.setJwk(jwk);
			return pilotoRepo.save(piloto);
		} catch (JoseException e) {
			throw new RacesException("Error creando set de claves: " + e.getMessage());
		}
	}

	@Override
	public Piloto actualizarPiloto(Long id, PilotoDto pilotoDto) throws RacesException {
		Optional<Piloto> optPiloto = pilotoRepo.findById(id);
		if (optPiloto.isPresent()) {
			Piloto piloto = optPiloto.get();
			piloto.setNombre(pilotoDto.getNombre());
			piloto.setApellido(pilotoDto.getApellido());
			piloto.setApodo(pilotoDto.getApodo());
			piloto.setPassword(pilotoDto.getPassword());
			return pilotoRepo.save(piloto);
		} else {
			throw new RacesException(NOT_FOUND_DRIVER);
		}
	}

	@Override
	public boolean borrarPiloto(Long id) throws RacesException {

		pilotoRepo.delete(buscarPiloto(id));
		return true;
	}

	@Override
	public Piloto buscarPiloto(Long id) throws RacesException {
		Optional<Piloto> opPiloto = pilotoRepo.findById(id);
		if (opPiloto.isPresent()) {
			return opPiloto.get();
		} else {
			throw new RacesException(NOT_FOUND_DRIVER);
		}
	}

	@Override
	public Piloto buscarPiloto(String apodo) throws RacesException {
		Optional<Piloto> opPiloto = pilotoRepo.findByApodo(apodo);
		if (opPiloto.isPresent()) {
			return opPiloto.get();
		} else {
			throw new RacesException(NOT_FOUND_DRIVER);
		}
	}

	@Override
	public List<Piloto> buscarPilotos(Long id, String nombre, String apellido, String apodo) {

		Example<Piloto> example = Example.of(new Piloto(id, nombre, apellido, apodo, null, null, null));
		return pilotoRepo.findAll(example);

	}

	@Override
	public boolean existePiloto(Long id) {
		return pilotoRepo.findById(id).isPresent();
	}

	@Override
	public LoginResponse authenticarUsuario(String alias, char[] password) throws RacesException {
		Piloto piloto = buscarPiloto(alias);
		if (piloto.getPassword().equals(new String(password))) {
			return generateRespose(piloto);
		} else {
			throw new RacesException("Usuario/Contraseña no válido");
		}
	}

	/**
	 * 
	 * @param piloto
	 * @return
	 * @throws RacesException
	 */
	private LoginResponse generateRespose(Piloto piloto) throws RacesException {
		LoginResponse response = new LoginResponse();
		response.setJwt(getJWT(piloto.getApodo(), piloto.getJwk()));
		response.setAdmin(piloto.getAdmin());
		return response;
	}

	/**
	 * 
	 * @param apodo
	 * @return
	 * @throws RacesException
	 */
	private String getJWT(String apodo, String jwk) throws RacesException {
		String result = "";
		try {

			if (StringUtils.isNotEmpty(jwk)) {
				result = jwtService.generateJwt(apodo, jwtService.decodeData(jwk));
			}
		} catch (JoseException e) {
			throw new RacesException("Error generando Token: " + e.getMessage());
		}

		return result;
	}

}
