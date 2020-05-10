package com.races.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.races.dto.FileUploadDto;
import com.races.dto.VueltaDto;
import com.races.entity.Campeonato;
import com.races.entity.Equipo;
import com.races.entity.GranPremio;
import com.races.entity.Inscripcion;
import com.races.entity.Piloto;
import com.races.entity.Reglamento;
import com.races.entity.Resultado;
import com.races.entity.Sesion;
import com.races.entity.SesionGP;
import com.races.entity.TipoSesion;
import com.races.entity.Vuelta;
import com.races.exception.RacesException;
import com.races.repository.VueltaRepository;
import com.races.services.impl.InscripcionServiceImpl;
import com.races.services.impl.PilotoServiceImpl;
import com.races.services.impl.ResultadoServiceImpl;
import com.races.services.impl.VueltaServiceImpl;

@RunWith(SpringRunner.class)
public class VueltaServiceTest {

	@Mock
	VueltaRepository vueltaRepo;

	@Mock
	PilotoServiceImpl pilotoService;

	@Mock
	InscripcionServiceImpl inscripciones;

	@Mock
	ResultadoServiceImpl resultadoService;

	@InjectMocks
	VueltaServiceImpl service;

	private static final String TEST = "test";

	private Piloto pilotoTest = new Piloto(1L, TEST, TEST, TEST, TEST, true, TEST);

	private Equipo equipoTest = new Equipo(1L, TEST, TEST);

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	private Campeonato campeonatoTest = new Campeonato(1L, TEST, TEST, TEST, reglamentoTest);

	private Inscripcion inscriptionTest = new Inscripcion(campeonatoTest, pilotoTest, equipoTest);

	private GranPremio granPremioTest = new GranPremio(1L, campeonatoTest, TEST);

	private TipoSesion tipoSesionTest = new TipoSesion(1L, TEST);

	private Sesion sesionTest = new Sesion(1L, TEST, reglamentoTest, tipoSesionTest);

	private SesionGP sesionGpTest = new SesionGP(1L, new Date(System.currentTimeMillis()), granPremioTest, sesionTest);

	private Resultado resultadoTest = new Resultado(inscriptionTest, sesionGpTest);

	private Vuelta vueltaTest = new Vuelta(1, 2, resultadoTest);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void buscarVueltasTest() {
		Mockito.when(vueltaRepo.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarVueltas(null, null, null, null));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarVueltasFiltersTest() {
		Mockito.when(vueltaRepo.findAll(Mockito.any(Example.class), Mockito.any(Sort.class)))
				.thenReturn(new ArrayList<>());
		assertNotNull(service.buscarVueltas(1L, 1L, 1, 1));
	}

	@Test
	public void buscarVueltasExceptionTest() throws RacesException {
		Mockito.when(vueltaRepo.findAll()).thenReturn(new ArrayList<>());
		Mockito.when(resultadoService.buscarResultado(Mockito.any())).thenThrow(new RacesException());
		assertNotNull(service.buscarVueltas(1L, 1L, 1, 1));
	}

	@Test
	public void buscarVueltaTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findById(Mockito.any())).thenReturn(op);
			assertNotNull(service.buscarVuelta(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarVueltaNoExistsTest() {
		try {
			Optional<Vuelta> op = Optional.empty();
			Mockito.when(vueltaRepo.findById(Mockito.any())).thenReturn(op);
			service.buscarVuelta(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void cargarVueltasTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto(TEST, 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString())).thenReturn(pilotoTest);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			List<Resultado> listResultado = new ArrayList<>();
			listResultado.add(resultadoTest);
			Mockito.when(resultadoService.buscarListaResultados(Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listResultado);
			Mockito.doNothing().when(vueltaRepo).deleteAll(Mockito.any());
			Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(Optional.of(vueltaTest));
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			service.cargarVueltas(listaRegistros, sesionGpTest);

		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cargarVueltasUnderMinTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto("1.000", 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString())).thenReturn(pilotoTest);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			List<Resultado> listResultado = new ArrayList<>();
			listResultado.add(resultadoTest);
			Mockito.when(resultadoService.buscarListaResultados(Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listResultado);
			Mockito.doNothing().when(vueltaRepo).deleteAll(Mockito.any());
			Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(Optional.of(vueltaTest));
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			service.cargarVueltas(listaRegistros, sesionGpTest);

		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cargarVueltasMasMinTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto("1:00.000", 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString())).thenReturn(pilotoTest);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			List<Resultado> listResultado = new ArrayList<>();
			listResultado.add(resultadoTest);
			Mockito.when(resultadoService.buscarListaResultados(Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listResultado);
			Mockito.doNothing().when(vueltaRepo).deleteAll(Mockito.any());
			Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(Optional.of(vueltaTest));
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			service.cargarVueltas(listaRegistros, sesionGpTest);

		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cargarVueltasMasMinFormatTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto("1:00.0.0", 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString())).thenReturn(pilotoTest);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			List<Resultado> listResultado = new ArrayList<>();
			listResultado.add(resultadoTest);
			Mockito.when(resultadoService.buscarListaResultados(Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listResultado);
			Mockito.doNothing().when(vueltaRepo).deleteAll(Mockito.any());
			Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(Optional.of(vueltaTest));
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			service.cargarVueltas(listaRegistros, sesionGpTest);

		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cargarVueltasMasHoraTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto("1:01:01.000", 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString())).thenReturn(pilotoTest);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			List<Resultado> listResultado = new ArrayList<>();
			listResultado.add(resultadoTest);
			Mockito.when(resultadoService.buscarListaResultados(Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listResultado);
			Mockito.doNothing().when(vueltaRepo).deleteAll(Mockito.any());
			Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(Optional.of(vueltaTest));
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			service.cargarVueltas(listaRegistros, sesionGpTest);

		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cargarVueltasTiempoInvalidoTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto("1:01:01:00:0", 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString())).thenReturn(pilotoTest);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			List<Resultado> listResultado = new ArrayList<>();
			listResultado.add(resultadoTest);
			Mockito.when(resultadoService.buscarListaResultados(Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listResultado);
			Mockito.doNothing().when(vueltaRepo).deleteAll(Mockito.any());
			Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(Optional.of(vueltaTest));
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			service.cargarVueltas(listaRegistros, sesionGpTest);

		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cargarVueltasMasHoraFormatTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto("1:01:01.1.000", 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString())).thenReturn(pilotoTest);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			List<Resultado> listResultado = new ArrayList<>();
			listResultado.add(resultadoTest);
			Mockito.when(resultadoService.buscarListaResultados(Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listResultado);
			Mockito.doNothing().when(vueltaRepo).deleteAll(Mockito.any());
			Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(Optional.of(vueltaTest));
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			service.cargarVueltas(listaRegistros, sesionGpTest);

		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cargarVueltasNuevaTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto(TEST, 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString())).thenReturn(pilotoTest);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			List<Resultado> listResultado = new ArrayList<>();
			listResultado.add(resultadoTest);
			Mockito.when(resultadoService.buscarListaResultados(Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listResultado);
			Mockito.doNothing().when(vueltaRepo).deleteAll(Mockito.any());
			Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(Optional.empty());
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			service.cargarVueltas(listaRegistros, sesionGpTest);

		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cargarVueltasNoExistsTest() {
		try {
			Optional<Vuelta> op = Optional.of(vueltaTest);
			Mockito.when(vueltaRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(vueltaRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
			List<FileUploadDto> listaRegistros = new ArrayList<>();
			List<VueltaDto> listaVueltas = new ArrayList<>();
			listaVueltas.add(new VueltaDto(TEST, 1));
			listaRegistros.add(new FileUploadDto(TEST, listaVueltas));
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscriptionTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyString()))
					.thenThrow(new RacesException("Simulated Error"));
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(listInscripciones);
			service.cargarVueltas(listaRegistros, sesionGpTest);
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void existeVueltaTest() {
		Optional<Vuelta> op = Optional.of(vueltaTest);
		Mockito.when(vueltaRepo.findById(Mockito.any())).thenReturn(op);
		assertNotNull(service.existeVuelta(1L));
	}

	@Test
	public void buscarVueltaRapidaTest() {
		List<Vuelta> lista = new ArrayList<>();
		lista.add(vueltaTest);
		Mockito.when(vueltaRepo.findByResultadoOrderByTiempoAsc(Mockito.any())).thenReturn(lista);
		assertNotNull(service.buscarVueltaRapida(resultadoTest));
	}

}