package com.races.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.GranPremioDto;
import com.races.dto.GranPremioSesionesDto;
import com.races.entity.GranPremio;
import com.races.repository.GranPremioRepository;
import com.races.services.CampeonatoService;
import com.races.services.GranPremioService;
import com.races.services.SesionGpService;

/**
 * Implementacion de la interfaz GranPremioService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("GranPremioService")
public class GranPremioServiceImpl implements GranPremioService {

	private static final Log LOGGER = LogFactory.getLog(GranPremioServiceImpl.class);

	@Autowired
	GranPremioRepository gpRepo;

	@Autowired
	SesionGpService sesionesGP;

	@Autowired
	CampeonatoService campeonatoService;

	@Override
	public GranPremio crearGranPremio(GranPremioDto gpDto) throws RacesException {
		if (campeonatoService.existeCampeonato(gpDto.getIdCampeonato())) {

			GranPremio newGp = new GranPremio();
			newGp.setUbicacion(gpDto.getUbicacion());
			newGp.setCampeonato(campeonatoService.buscarCampeonato(gpDto.getIdCampeonato()));

			if (gpRepo.findOne(Example.of(newGp)).isPresent()) {
				throw new RacesException("GP ya existe");
			}

			newGp = gpRepo.save(newGp);
			sesionesGP.crearSesionesGranPremio(newGp, gpDto.getFecha());
			return newGp;
		} else {
			throw new RacesException("El campeonato asociado no existe");
		}
	}

	@Override
	public List<GranPremioSesionesDto> buscarGrandesPremios(Long id, String ubicacion, Long idCampeonato) {

		List<GranPremioSesionesDto> lista = new ArrayList<>();
		List<GranPremio> listaGp;
		try {
			listaGp = gpRepo.findAll(Example.of(new GranPremio(id == null ? null : id,
					idCampeonato == null ? null : campeonatoService.buscarCampeonato(idCampeonato),
					StringUtils.isBlank(ubicacion) ? null : ubicacion)));
			for (GranPremio gp : listaGp) {
				lista.add(new GranPremioSesionesDto(gp, sesionesGP.buscarSesiones(null, gp.getId(), null, null)));
			}

		} catch (RacesException e) {
			LOGGER.error(e);
		}
		return lista;
	}

	@Override
	public GranPremio buscarGranPremio(Long id) throws RacesException {
		Optional<GranPremio> campeonato = gpRepo.findById(id);
		if (campeonato.isPresent()) {
			return campeonato.get();
		} else {
			throw new RacesException("GP no encontrado");
		}
	}

	@Override
	public boolean borrarGranPremio(Long id) throws RacesException {
		Optional<GranPremio> gpOpt = gpRepo.findById(id);
		if (gpOpt.isPresent()) {
			gpRepo.delete(gpOpt.get());
			return true;
		} else {
			throw new RacesException("GP no encontrado");
		}
	}

	@Override
	public boolean existeGranPremio(Long id) {
		return gpRepo.findById(id).isPresent();
	}

}
