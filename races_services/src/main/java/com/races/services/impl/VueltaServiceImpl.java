package com.races.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.constants.Constants;
import com.races.dto.FileUploadDto;
import com.races.dto.VueltaDto;
import com.races.entity.Inscripcion;
import com.races.entity.Resultado;
import com.races.entity.Sesion;
import com.races.entity.Vuelta;
import com.races.repository.VueltaRepository;
import com.races.services.InscripcionService;
import com.races.services.PilotoService;
import com.races.services.ResultadoService;
import com.races.services.SesionService;
import com.races.services.VueltaService;

/**
 * Implementacion de la interfaz VueltaSevice
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("VueltaService")
public class VueltaServiceImpl implements VueltaService {

	@Autowired
	VueltaRepository vueltaRepo;

	@Autowired
	PilotoService pilotoService;

	@Autowired
	SesionService sesionService;

	@Autowired
	InscripcionService inscripciones;

	@Autowired
	ResultadoService resultadoService;

	private static final Log LOGGER = LogFactory.getLog(VueltaServiceImpl.class);

	@Override
	public Vuelta crearVuelta(VueltaDto vueltaDto) throws RacesException {

		Vuelta newVuelta = new Vuelta();
		newVuelta.setnVuelta(vueltaDto.getnVuelta());
		newVuelta.setTiempo(vueltaDto.getTiempo());
		newVuelta.setResultado(resultadoService.buscarResultado(vueltaDto.getIdResultado()));
		if (vueltaRepo.findOne(Example.of(newVuelta)).isPresent()) {
			throw new RacesException("Vuelta duplicada");
		}
		return vueltaRepo.save(newVuelta);
	}

	@Override
	public List<Vuelta> buscarVueltas(Long id, Long idResultado, Integer nVuelta, Integer tiempo) {
		if (id == null && idResultado == null && nVuelta == null && tiempo == null) {
			return vueltaRepo.findAll();
		} else {
			try {
				return vueltaRepo.findAll(
						Example.of(new Vuelta(id, tiempo, nVuelta, resultadoService.buscarResultado(idResultado))),
						new Sort(Sort.Direction.ASC, "nVuelta"));
			} catch (RacesException e) {
				LOGGER.error(e);
				return vueltaRepo.findAll();
			}
		}
	}

	@Override
	public Vuelta buscarVuelta(Long id) throws RacesException {
		Optional<Vuelta> opVuelta = vueltaRepo.findById(id);
		if (opVuelta.isPresent()) {
			return opVuelta.get();
		} else {
			throw new RacesException("Vuelta no encontrada");
		}
	}

	@Override
	public boolean existeVuelta(Long id) {
		return vueltaRepo.findById(id).isPresent();
	}

	@Override
	public boolean borrarVuelta(Long id) throws RacesException {
		vueltaRepo.delete(buscarVuelta(id));
		return true;
	}

	@Override
	public void cargarVueltas(List<FileUploadDto> listLines, Sesion sesion) throws RacesException {
		List<Vuelta> listaVueltas = new ArrayList<>();
		for (FileUploadDto registro : listLines) {
			try {
				List<Inscripcion> inscritos = inscripciones.buscarInscripciones(sesion.getGranPremio().getCampeonato().getId(),pilotoService.buscarPiloto(registro.getPiloto()).getId(),null);
				if (!inscritos.isEmpty()) {
					List<Resultado> resultado = resultadoService.buscarListaResultados(null, inscritos.get(0).getId(),
							sesion.getId(), null, null);
					vueltaRepo.deleteAll(vueltaRepo.findByResultadoOrderByTiempoAsc(resultado.get(0)));
					listaVueltas
							.add(new Vuelta(parseTiempo(registro.getTiempo()), registro.getVuelta(), resultado.get(0)));
				}
			} catch (RacesException e) {
				LOGGER.warn(e.getMessage());
			}
		}
		actualizarRegistros(listaVueltas);
	}

	/**
	 * Metodo para crear/modificar vueltas de una sesion
	 * 
	 * @param listaVueltas
	 */
	private void actualizarRegistros(List<Vuelta> listaVueltas) {
		Optional<Vuelta> prueba;
		Vuelta registro;
		List<Vuelta> saveAllList = new ArrayList<>();

		for (Vuelta vuelta : listaVueltas) {
			LOGGER.info(vuelta);
			prueba = vueltaRepo.findOne(Example.of(new Vuelta(null, vuelta.getnVuelta(), vuelta.getResultado())));
			if (prueba.isPresent()) {
				registro = prueba.get();
				registro.setTiempo(vuelta.getTiempo());
				saveAllList.add(registro);
			} else {
				saveAllList.add(vuelta);
			}
		}
		vueltaRepo.saveAll(saveAllList);
	}

	/**
	 * Parsea el tiempo de vuelta a un numero entero (en milisegundos)
	 * 
	 * @param tiempo
	 * @return
	 */
	private Integer parseTiempo(String tiempo) {
		Integer tiempoInteger = 0;
		String[] sub = tiempo.split(":");
		String[] submilis;
		switch (sub.length) {
		case 1:
			LOGGER.debug("Vuelta inferior a 1 min");
			submilis = sub[0].split("\\.");
			if (submilis.length != 2) {
				LOGGER.debug(Constants.FORMATO_INCORRECTO);
				return tiempoInteger;
			}
			tiempoInteger = Integer.parseInt(submilis[1]) + (Integer.parseInt(submilis[0]) * 1000);
			break;
		case 2:
			LOGGER.debug("Vuelta estandar con minutos y segundos");
			submilis = sub[1].split("\\.");
			if (submilis.length != 2) {
				LOGGER.debug(Constants.FORMATO_INCORRECTO);
				return tiempoInteger;
			}
			tiempoInteger = Integer.parseInt(submilis[1]) + (Integer.parseInt(submilis[0]) * 1000)
					+ (Integer.parseInt(sub[0]) * 60000);
			break;
		case 3:
			LOGGER.debug("Vuelta larga de más de una hora");
			submilis = sub[2].split("\\.");
			if (submilis.length != 2) {
				LOGGER.debug(Constants.FORMATO_INCORRECTO);
				return tiempoInteger;
			}
			tiempoInteger = Integer.parseInt(submilis[1]) + (Integer.parseInt(submilis[0]) * 1000)
					+ (Integer.parseInt(sub[1]) * 60000) + (Integer.parseInt(sub[0]) * 3600000);
			break;
		default:
			LOGGER.debug("Tiempo no válido");

		}
		return tiempoInteger;
	}

	@Override
	public Vuelta buscarVueltaRapida(Resultado resultado) {
		List<Vuelta> lista = vueltaRepo.findByResultadoOrderByTiempoAsc(resultado);
		return lista.isEmpty() ? null : lista.get(0);
	}

}
