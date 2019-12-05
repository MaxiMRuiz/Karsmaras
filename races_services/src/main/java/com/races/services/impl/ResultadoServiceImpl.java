package com.races.services.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import com.races.dto.ResultadoDto;
import com.races.dto.ResultadoResponseDto;
import com.races.entity.Campeonato;
import com.races.entity.Piloto;
import com.races.entity.Resultado;
import com.races.entity.Sesion;
import com.races.entity.Vuelta;
import com.races.repository.ResultadoRepository;
import com.races.services.InscripcionService;
import com.races.services.PilotoService;
import com.races.services.ResultadoService;
import com.races.services.SesionService;
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
	SesionService sesionService;

	@Autowired
	InscripcionService inscripciones;

	@Autowired
	VueltaService vueltas;

	@Autowired
	Environment env;

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

	public List<ResultadoResponseDto> buscarResultados(Long id, Long idPiloto, Long idSesion, Integer nVueltas,
			Integer tiempo) {
		List<Resultado> lista = buscarListaResultados(id, idPiloto, idSesion, nVueltas, tiempo);
		return resultado2Response(lista);
	}

	public List<Resultado> buscarListaResultados(Long id, Long idPiloto, Long idSesion, Integer nVueltas,
			Integer tiempo) {
		try {
			Resultado probe = new Resultado(id == null ? null : id,
					idPiloto == null ? null : pilotoService.buscarPiloto(idPiloto),
					idSesion == null ? null : sesionService.buscarSesion(idSesion), nVueltas == null ? null : nVueltas,
					tiempo == null ? null : tiempo);
			return resultadoRepo.findAll(Example.of(probe),
					new Sort(Sort.Direction.DESC, "nVueltas").and(new Sort(Sort.Direction.ASC, "tiempo")));
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
		for (Resultado resultado : lista) {
			Vuelta vRapida = vueltas.buscarVueltaRapida(resultado);
			response.add(new ResultadoResponseDto(resultado, vRapida));
		}
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
	public boolean borrarResultado(Long id) throws RacesException {
		resultadoRepo.delete(buscarResultado(id));
		return true;
	}

	@Override
	public void crearResultados(List<Sesion> listSesion, Campeonato campeonato) {
		List<Piloto> pilotos = inscripciones.buscarPilotos(campeonato);
		List<Resultado> resultados = new ArrayList<>();
		for (Sesion sesion : listSesion) {
			for (Piloto piloto : pilotos) {
				resultados.add(new Resultado(null, piloto, sesion, 0, 0));
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
		String rootPath = env.getProperty("files.upload.basepath");
		File dir = new File(rootPath + File.separator + "tmpFiles");
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
				String[] parts = line.split(";");
				if (parts.length == 3) {
					listLines.add(new FileUploadDto(parts[0], Integer.parseInt(parts[1]), parts[2]));
				}
			}
			LOGGER.info("Fichero procesado");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		try {
			Sesion sesion = sesionService.buscarSesion(idSesion);
			vueltas.cargarVueltas(listLines, sesion);
			actualizarResultados(sesion);
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
	private void actualizarResultados(Sesion sesion) {
		List<Resultado> resultados = buscarListaResultados(null, null, sesion.getId(), null, null);
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

}
