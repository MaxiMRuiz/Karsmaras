package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.ResultadoDto;
import com.races.entity.Resultado;
import com.races.repository.ResultadoRepository;
import com.races.services.PilotoService;
import com.races.services.ResultadoService;
import com.races.services.SesionService;

/**
 * Implementacion de la interfaz ResultadoService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("ResultadoService")
public class ResultadoServiceImpl implements ResultadoService {

	@Autowired
	ResultadoRepository resultadoRepo;

	@Autowired
	PilotoService pilotoService;

	@Autowired
	SesionService sesionService;

	private static final Log LOGGER = LogFactory.getLog(ResultadoServiceImpl.class);

	@Override
	public Resultado crearResultado(ResultadoDto resultadoDto) throws RacesException {
		Resultado newResultado = new Resultado();
		newResultado.setnVueltas(resultadoDto.getnVueltas());
		newResultado.setPiloto(pilotoService.buscarPiloto(resultadoDto.getIdPiloto()));
		newResultado.setSesion(sesionService.buscarSesion(resultadoDto.getIdSesion()));
		newResultado.setTiempo(resultadoDto.getTiempo());
		if (resultadoRepo.findOne(Example.of(newResultado)).isPresent()) {
			throw new RacesException("Resultado duplicado");
		}
		return resultadoRepo.save(newResultado);
	}

	public List<Resultado> buscarResultados(Long id, Long idPiloto, Long idSesion, Integer nVueltas, Integer tiempo) {
		if (idPiloto == null && idSesion == null && nVueltas == null && tiempo == null && id == null) {
			return resultadoRepo.findAll();
		} else {
			try {
				Resultado probe = new Resultado(id, pilotoService.buscarPiloto(idPiloto),
						sesionService.buscarSesion(idSesion), nVueltas, tiempo);
				return resultadoRepo.findAll(Example.of(probe));
			} catch (RacesException e) {
				LOGGER.error(e);
				return resultadoRepo.findAll();
			}

		}
	}

	@Override
	public Resultado buscarResultado(Long id) throws RacesException {
		Optional<Resultado> op = resultadoRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new RacesException("Resultado no existe");
		}
	}

	@Override
	public boolean existeResultado(Long id) {
		return resultadoRepo.findById(id).isPresent();
	}

	@Override
	public boolean borrarResultado(Long id) throws RacesException {
			resultadoRepo.delete(buscarResultado(id));
			return true;
	}

}
