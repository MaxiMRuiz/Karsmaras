package com.races.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.races.dto.ClasificacionDto;
import com.races.dto.GranPremioSesionesDto;
import com.races.dto.ResultadoResponseDto;
import com.races.entity.Piloto;
import com.races.entity.Puntuacion;
import com.races.entity.Sancion;
import com.races.entity.Sesion;
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
		if (granPremio.isEmpty()) {
			LOGGER.info("GP no encontrado");
			return new ArrayList<>();
		} else {
			LOGGER.info("Obteniendo las puntuaciones del Reglamento "
					+ granPremio.get(0).getGp().getCampeonato().getReglamento());
			List<Puntuacion> puntuaciones = puntuacionService
					.buscarPuntuacionesValidas(granPremio.get(0).getGp().getCampeonato().getReglamento().getId());
			LOGGER.info("Encontradas " + puntuaciones.size() + " posiciones.");
			return calcularClasificacionGranPremio(granPremio.get(0), puntuaciones);
		}
	}

	private List<ClasificacionDto> calcularClasificacionGranPremio(GranPremioSesionesDto granPremio,
			List<Puntuacion> puntuaciones) {
		List<ClasificacionDto> clasificaciones = new ArrayList<>();
		List<ResultadoResponseDto> resultados;
		for (Sesion sesion : granPremio.getSesiones()) {
			LOGGER.info("Calculando resultados de la sesion " + sesion);
			resultados = resultadoService.buscarResultadosValidos(sesion.getId());
			LOGGER.info("Encontrados " + resultados.size() + " resultados válidos.");
			int j = 1;
			for (ResultadoResponseDto resultado : resultados) {
				if (resultado.getnVueltas() > 0) {
					List<Sancion> sanciones = sancionService.buscarSanciones(null, resultado.getId(), null, null);
					LOGGER.info("Encontradas " + sanciones.size() + " sanciones");
					LOGGER.info("Actualizando el resultado del piloto " + resultado.getPiloto());
					actualizarClasificacion(clasificaciones, resultado.getPiloto(),
							getPuntuacion(puntuaciones, j, sanciones, sesion.getTipoSesion()));
					j++;
				}

			}
		}
		Collections.sort(clasificaciones);
		return clasificaciones;
	}

	private void actualizarClasificacion(List<ClasificacionDto> clasificaciones, Piloto piloto, Integer puntuacion) {
		for (ClasificacionDto clasificacion : clasificaciones) {
			if (clasificacion.getPiloto().getId().equals(piloto.getId())) {
				LOGGER.info("Sumando los puntos del piloto " + piloto + " de " + clasificacion.getPuntos() + " a "
						+ (clasificacion.getPuntos() + puntuacion));
				clasificacion.setPuntos(clasificacion.getPuntos() + puntuacion);
				return;
			}
		}
		LOGGER.info("Piloto no encontrado en la lista, añadiendo el resultado del piloto " + piloto);
		clasificaciones.add(new ClasificacionDto(piloto, puntuacion));
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
			if (puntuacion.getTipoSesion().getId().equals(tipoSesion.getId()) && puntuacion.getPosicion() == j) {
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
		LOGGER.info(
				"Obteniendo las puntuaciones del Reglamento " + listaGp.get(0).getGp().getCampeonato().getReglamento());
		List<Puntuacion> puntuaciones = puntuacionService
				.buscarPuntuacionesValidas(listaGp.get(0).getGp().getCampeonato().getReglamento().getId());
		LOGGER.info("Encontradas " + puntuaciones.size() + " posiciones.");
		for (GranPremioSesionesDto granPremio : listaGp) {
			clasificacionGp = calcularClasificacionGranPremio(granPremio, puntuaciones);
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

	private void actualizarClasificacionGeneral(List<ClasificacionDto> clasificaciones,
			List<ClasificacionDto> clasificacionGp) {
		for (ClasificacionDto clasificacionGeneral : clasificaciones) {
			for (ClasificacionDto clasficacionGranPremio : clasificacionGp) {
				if (clasificacionGeneral.getPiloto().getId().equals(clasficacionGranPremio.getPiloto().getId())) {
					LOGGER.info("Sumando " + clasificacionGeneral.getPuntos() + " con "
							+ clasficacionGranPremio.getPuntos());
					clasificacionGeneral
							.setPuntos(clasificacionGeneral.getPuntos() + clasficacionGranPremio.getPuntos());
				}
			}
		}
	}

}
