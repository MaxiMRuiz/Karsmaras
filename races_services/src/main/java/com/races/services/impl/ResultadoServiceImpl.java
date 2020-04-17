package com.races.services.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.races.component.RacesException;
import com.races.dto.FileUploadDto;
import com.races.dto.GranPremioSesionesDto;
import com.races.dto.ResultadoDto;
import com.races.dto.ResultadoResponseDto;
import com.races.dto.VueltaDto;
import com.races.entity.Campeonato;
import com.races.entity.Inscripcion;
import com.races.entity.Resultado;
import com.races.entity.Sancion;
import com.races.entity.SesionGP;
import com.races.entity.TipoSesion;
import com.races.entity.Vuelta;
import com.races.repository.ResultadoRepository;
import com.races.services.GranPremioService;
import com.races.services.InscripcionService;
import com.races.services.PilotoService;
import com.races.services.ResultadoService;
import com.races.services.SesionGpService;
import com.races.services.VueltaService;

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
	SesionGpService sesionGpService;

	@Autowired
	InscripcionService inscripciones;

	@Autowired
	VueltaService vueltas;

	@Autowired
	GranPremioService gpService;

	@Autowired
	Environment env;

	private static final Log LOGGER = LogFactory.getLog(ResultadoServiceImpl.class);

	public List<ResultadoResponseDto> buscarResultados(Long id, Long idInscripcion, Long idSesion, Integer nVueltas,
			Integer tiempo) {
		List<Resultado> lista = buscarListaResultados(id, idInscripcion, idSesion, nVueltas, tiempo);
		return resultado2Response(lista);
	}

	@Override
	public List<ResultadoResponseDto> buscarResultadosValidos(SesionGP sesion) {
		List<Resultado> lista = resultadoRepo.findBySesionGPAndVueltasGreaterThan(sesion, 0,
				new Sort(Sort.Direction.DESC, "vueltas").and(new Sort(Sort.Direction.ASC, "tiempo")));
		return resultado2Response(lista);
	}

	public List<Resultado> buscarListaResultados(Long id, Long idInscripcion, Long idSesion, Integer nVueltas,
			Integer tiempo) {
		try {
			Resultado probe = new Resultado(id == null ? null : id,
					idInscripcion == null ? null : inscripciones.buscarInscripcion(idInscripcion),
					idSesion == null ? null : sesionGpService.buscarSesion(idSesion),
					nVueltas == null ? null : nVueltas, tiempo == null ? null : tiempo);
			return resultadoRepo.findAll(Example.of(probe),
					new Sort(Sort.Direction.DESC, "vueltas").and(new Sort(Sort.Direction.ASC, "tiempo")));
		} catch (RacesException e) {
			LOGGER.error(e);
			return resultadoRepo.findAll();
		}
	}

	/**
	 * Transforma el listado de resultados en la respuesta del servicio, añadiendo
	 * la vuelta rapida
	 * 
	 * @param lista
	 * @return
	 */
	private List<ResultadoResponseDto> resultado2Response(List<Resultado> lista) {
		List<ResultadoResponseDto> response = new ArrayList<>();

		if (lista.isEmpty()) {
			return response;
		}
		for (Resultado resultado : lista) {
			if (resultado.getnVueltas() > 0) {
				Vuelta vRapida = vueltas.buscarVueltaRapida(resultado);
				response.add(new ResultadoResponseDto(resultado, vRapida));
			}
		}
		TipoSesion tipo = lista.get(0).getSesionGP().getSesion().getTipoSesion();
		if (tipo.getDescripcion().equals("Carrera")) {
			return response;
		} else {
			return orderVRapidaList(response);
		}
	}

	/**
	 * Metodo que ordena los resultados por V.Rapida de menor a mayor
	 * 
	 * @param response
	 * @return
	 */
	private List<ResultadoResponseDto> orderVRapidaList(List<ResultadoResponseDto> response) {
		Collections.sort(response);
		return response;
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
	public void crearResultados(List<SesionGP> listSesionGp, Campeonato campeonato) {
		List<Inscripcion> listInscripciones = inscripciones.buscarInscripciones(campeonato.getId(), null, null);
		List<Resultado> resultados = new ArrayList<>();
		for (SesionGP sesionGp : listSesionGp) {
			for (Inscripcion inscripcion : listInscripciones) {
				resultados.add(new Resultado(null, inscripcion, sesionGp, 0, 0));
			}
		}
		resultadoRepo.saveAll(resultados);
	}

	@Override
	public Boolean actualizarResultado(Long id, ResultadoDto resultadoDto) throws RacesException {
		Resultado resultado = buscarResultado(id);
		resultado.setnVueltas(resultadoDto.getnVueltas());
		resultado.setTiempo(resultadoDto.getTiempo());
		resultadoRepo.save(resultado);
		return true;
	}

	@Override
	public void processFile(Long idSesion, MultipartFile file) {
		String rootPath = env.getProperty("race.files.upload.basepath");
		File dir = new File(rootPath);
		if (!dir.exists())
			dir.mkdirs();

		File serverFile = new File(
				dir.getAbsolutePath() + File.separator + "Sesion" + idSesion + System.currentTimeMillis() + ".txt");
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {

			stream.write(file.getBytes());
		} catch (IOException e) {
			LOGGER.error(e);
		}

		List<FileUploadDto> listLines = new ArrayList<>();
		String line;
		try (InputStream inputStream = file.getInputStream();
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));) {
			while ((line = bufferReader.readLine()) != null) {
				List<VueltaDto> listaVueltas = new ArrayList<>();
				String[] parts = line.split(";");
				if (parts.length > 1) {
					for(int i = 1; i< parts.length;i++) {
						listaVueltas.add(new VueltaDto(parts[i],i));
					}
					listLines.add(new FileUploadDto(parts[0].replaceAll("[^a-zA-Z]", ""), listaVueltas));
				}
			}
			LOGGER.info("Fichero procesado");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		try {
			SesionGP sesionGp = sesionGpService.buscarSesion(idSesion);
			vueltas.cargarVueltas(listLines, sesionGp);
			actualizarResultados(sesionGp);
		} catch (RacesException e) {
			LOGGER.error(e.getMessage());
		}

	}

	/**
	 * Actuliza los datos de nVueltas y Tiempo en base a las vueltas registradas en
	 * la plataforma
	 * 
	 * @param sesion
	 */
	private void actualizarResultados(SesionGP sesionGp) {
		List<Resultado> resultados = buscarListaResultados(null, null, sesionGp.getId(), null, null);
		Integer nVueltas;
		Integer tiempo;
		for (Resultado resultado : resultados) {
			List<Vuelta> vueltasResultado = vueltas.buscarVueltas(null, resultado.getId(), null, null);
			nVueltas = 0;
			tiempo = 0;
			for (Vuelta vuelta : vueltasResultado) {
				nVueltas++;
				tiempo += vuelta.getTiempo();
			}
			resultado.setnVueltas(nVueltas);
			resultado.setTiempo(tiempo);
		}
		resultadoRepo.saveAll(resultados);
	}

	@Override
	public void aplicarSancion(Sancion sancion) {
		Resultado resultado = sancion.getResultado();
		resultado.setTiempo(resultado.getTiempo() + sancion.getTiempo());
		resultadoRepo.save(resultado);
	}

	@Override
	public void eliminarSancion(Sancion sancion) {
		Resultado resultado = sancion.getResultado();
		resultado.setTiempo(resultado.getTiempo() - sancion.getTiempo());
		resultadoRepo.save(resultado);
	}

	@Override
	public void crearResultados(Inscripcion newInscripcion) {
		List<Resultado> resultados = new ArrayList<>();
		List<GranPremioSesionesDto> listaGp = gpService.buscarGrandesPremios(null, null,
				newInscripcion.getCampeonato().getId());
		for (GranPremioSesionesDto gp : listaGp) {
			for (SesionGP sesion : gp.getSesiones()) {
				resultados.add(new Resultado(newInscripcion, sesion));
			}
		}
		resultadoRepo.saveAll(resultados);
	}

}
