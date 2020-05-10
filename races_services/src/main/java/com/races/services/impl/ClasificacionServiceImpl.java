package com.races.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.races.constants.Constants;
import com.races.dto.ClasificacionDto;
import com.races.dto.GranPremioSesionesDto;
import com.races.dto.ResultadoResponseDto;
import com.races.entity.Inscripcion;
import com.races.entity.Puntuacion;
import com.races.entity.Sancion;
import com.races.entity.SesionGP;
import com.races.entity.TipoSesion;
import com.races.services.CampeonatoService;
import com.races.services.ClasificacionService;
import com.races.services.GranPremioService;
import com.races.services.PuntuacionService;
import com.races.services.ResultadoService;
import com.races.services.SancionService;
import com.races.services.SesionService;

/**
 * @author Maximino Mañanes Ruiz
 *
 */
@Service
public class ClasificacionServiceImpl implements ClasificacionService {

	@Autowired
	GranPremioService gpService;

	@Autowired
	CampeonatoService campeonatoService;

	@Autowired
	PuntuacionService puntuacionService;

	@Autowired
	SesionService sesionService;

	@Autowired
	ResultadoService resultadoService;

	@Autowired
	SancionService sancionService;

	private static final Log LOGGER = LogFactory.getLog(ClasificacionServiceImpl.class);

	@Override
	public List<ClasificacionDto> calcularClasificacionGp(Long idGp) {

		LOGGER.info("Calculando la clasificacion del GP " + idGp);
		List<GranPremioSesionesDto> granPremio = gpService.buscarGrandesPremios(idGp, null, null);
		int count = 0;
		List<ClasificacionDto> clasificacionGp;
		List<ClasificacionDto> clasificaciones = new ArrayList<>();
		if (granPremio.isEmpty()) {
			LOGGER.info("GP no encontrado");
			return new ArrayList<>();
		} else {
			for (SesionGP sesion : granPremio.get(0).getSesiones()) {
				LOGGER.info("Obteniendo las puntuaciones de la Sesion " + sesion.getSesion().getDescripcion());
				List<Puntuacion> puntuaciones = puntuacionService.buscarPuntuacionesValidas(sesion.getSesion().getId());
				LOGGER.info(Constants.ENCONTRADAS + puntuaciones.size() + " posiciones.");
				clasificacionGp = calcularClasificacionSesion(sesion, puntuaciones);
				if (count == 0) {
					clasificaciones.addAll(clasificacionGp);
				} else {
					actualizarClasificacionGeneral(clasificaciones, clasificacionGp);
				}
				count++;
			}
			Collections.sort(clasificaciones);
			return clasificaciones;
		}
	}

	private List<ClasificacionDto> calcularClasificacionSesion(SesionGP sesionGP, List<Puntuacion> puntuaciones) {
		List<ClasificacionDto> clasificaciones = new ArrayList<>();
		LOGGER.info("Calculando resultados de la sesion " + sesionGP);
		List<ResultadoResponseDto> resultados = resultadoService.buscarResultadosValidos(sesionGP);
		LOGGER.info("Encontrados " + resultados.size() + " resultados válidos.");
		int j = 1;
		for (ResultadoResponseDto resultado : resultados) {
			if (resultado.getnVueltas() > 0) {
				List<Sancion> sanciones = sancionService.buscarSanciones(null, resultado.getId(), null, null);
				LOGGER.info(Constants.ENCONTRADAS + sanciones.size() + " sanciones");
				LOGGER.info("Actualizando el resultado del piloto " + resultado.getInscripcion().getPiloto());
				actualizarClasificacion(clasificaciones, resultado.getInscripcion(),
						getPuntuacion(puntuaciones, j, sanciones, sesionGP.getSesion().getTipoSesion()),
						resultados.size(), (j - 1),
						resultado.getSesionGP().getSesion().getTipoSesion().getId().equals(Constants.CARRERA_ID));
				j++;
			}

		}
		Collections.sort(clasificaciones);
		return clasificaciones;
	}

	private void actualizarClasificacion(List<ClasificacionDto> clasificaciones, Inscripcion inscripcion,
			Integer puntuacion, int totalResultados, Integer posicion, boolean isCarrera) {
		for (ClasificacionDto clasificacion : clasificaciones) {
			if (clasificacion.getInscripcion().getId().equals(inscripcion.getId())) {
				LOGGER.info("Sumando los puntos del piloto " + inscripcion.getPiloto() + " de "
						+ clasificacion.getPuntos() + " a " + (clasificacion.getPuntos() + puntuacion));
				clasificacion.setPuntos(clasificacion.getPuntos() + puntuacion);
				if (isCarrera) {
					clasificacion.addPuesto(posicion);
				}
				return;
			}
		}
		LOGGER.info("Piloto no encontrado en la lista, añadiendo el resultado del piloto " + inscripcion.getPiloto());
		clasificaciones
				.add(new ClasificacionDto(inscripcion, puntuacion, new Integer[totalResultados], posicion, isCarrera));
	}

	private Integer getPuntuacion(List<Puntuacion> puntuaciones, int j, List<Sancion> sanciones,
			TipoSesion tipoSesion) {
		Integer puntos = 0;
		LOGGER.info("Obteniendo puntuacion del puesto " + j + " para el tipo de sesion " + tipoSesion);
		for (Sancion sancion : sanciones) {
			LOGGER.info("Aplicando sancion: " + puntos + " -> " + (puntos + sancion.getPuntos()));
			puntos = puntos + sancion.getPuntos();
		}
		for (Puntuacion puntuacion : puntuaciones) {
			if (puntuacion.getSesion().getTipoSesion().getId().equals(tipoSesion.getId())
					&& puntuacion.getPosicion() == j) {
				LOGGER.info("Aplicando puntuacion: " + puntos + " -> " + (puntos + puntuacion.getPuntos()));
				return puntos + puntuacion.getPuntos();
			}
		}
		return puntos;
	}

	@Override
	public List<ClasificacionDto> calcularClasificacionCampeonato(Long id) {
		List<ClasificacionDto> clasificaciones = new ArrayList<>();
		List<GranPremioSesionesDto> listaGp = gpService.buscarGrandesPremios(null, null, id);
		int count = 0;
		List<ClasificacionDto> clasificacionGp;

		for (GranPremioSesionesDto granPremio : listaGp) {
			for (SesionGP sesion : granPremio.getSesiones()) {
				LOGGER.info("Obteniendo las puntuaciones de la Sesion " + sesion.getSesion().getDescripcion());
				List<Puntuacion> puntuaciones = puntuacionService.buscarPuntuacionesValidas(sesion.getSesion().getId());
				LOGGER.info(Constants.ENCONTRADAS + puntuaciones.size() + " posiciones.");
				clasificacionGp = calcularClasificacionSesion(sesion, puntuaciones);
				if (count == 0) {
					clasificaciones.addAll(clasificacionGp);
				} else {
					actualizarClasificacionGeneral(clasificaciones, clasificacionGp);
				}
				count++;
			}
		}
		Collections.sort(clasificaciones);
		return clasificaciones;
	}

	private void actualizarClasificacionGeneral(List<ClasificacionDto> clasificaciones,
			List<ClasificacionDto> clasificacionGp) {
		boolean nuevo = true;
		for (ClasificacionDto clasficacionGranPremio : clasificacionGp) {
			for (ClasificacionDto clasificacionGeneral : clasificaciones) {
				if (clasificacionGeneral.getInscripcion().getId()
						.equals(clasficacionGranPremio.getInscripcion().getId())) {
					LOGGER.info("Sumando " + clasificacionGeneral.getPuntos() + " con "
							+ clasficacionGranPremio.getPuntos());
					clasificacionGeneral
							.setPuntos(clasificacionGeneral.getPuntos() + clasficacionGranPremio.getPuntos());
					clasificacionGeneral.mergePuestos(clasficacionGranPremio.getPuestos());
					nuevo = false;
				}
			}
			if (nuevo) {
				clasificaciones.add(clasficacionGranPremio);
			}
			nuevo = true;
		}
	}

}
