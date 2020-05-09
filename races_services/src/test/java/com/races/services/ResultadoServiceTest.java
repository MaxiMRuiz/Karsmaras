package com.races.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.races.dto.GranPremioSesionesDto;
import com.races.dto.ResultadoDto;
import com.races.entity.Campeonato;
import com.races.entity.Equipo;
import com.races.entity.GranPremio;
import com.races.entity.Inscripcion;
import com.races.entity.Piloto;
import com.races.entity.Reglamento;
import com.races.entity.Resultado;
import com.races.entity.Sancion;
import com.races.entity.Sesion;
import com.races.entity.SesionGP;
import com.races.entity.TipoSesion;
import com.races.entity.Vuelta;
import com.races.exception.RacesException;
import com.races.repository.ResultadoRepository;
import com.races.services.impl.GranPremioServiceImpl;
import com.races.services.impl.InscripcionServiceImpl;
import com.races.services.impl.PilotoServiceImpl;
import com.races.services.impl.ResultadoServiceImpl;
import com.races.services.impl.SesionGpServiceImpl;
import com.races.services.impl.VueltaServiceImpl;

public class ResultadoServiceTest {

	@Mock
	ResultadoRepository resultadoRepo;

	@Mock
	PilotoServiceImpl pilotoService;

	@Mock
	SesionGpServiceImpl sesionGpService;

	@Mock
	InscripcionServiceImpl inscripciones;

	@Mock
	VueltaServiceImpl vueltas;

	@Mock
	GranPremioServiceImpl gpService;

	@Mock
	Environment env;

	@InjectMocks
	ResultadoServiceImpl service;

	private static final String TEST = "test";

	private Piloto pilotoTest = new Piloto(1L, TEST, TEST, TEST, TEST, true, TEST);

	private Equipo equipoTest = new Equipo(1L, TEST, TEST);

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	private Campeonato campeonatoTest = new Campeonato(1L, TEST, TEST, TEST, reglamentoTest);

	private Inscripcion inscriptionTest = new Inscripcion(campeonatoTest, pilotoTest, equipoTest);

	private GranPremio granPremioTest = new GranPremio(1L, campeonatoTest, TEST);

	private TipoSesion tipoSesionTest = new TipoSesion(1L, TEST);

	private TipoSesion tipoSesionTest2 = new TipoSesion(1L, "Carrera");

	private Sesion sesionTest = new Sesion(1L, TEST, reglamentoTest, tipoSesionTest);

	private Sesion sesionTest2 = new Sesion(1L, TEST, reglamentoTest, tipoSesionTest2);

	private SesionGP sesionGpTest = new SesionGP(1L, new Date(System.currentTimeMillis()), granPremioTest, sesionTest);

	private SesionGP sesionGpTest2 = new SesionGP(1L, new Date(System.currentTimeMillis()), granPremioTest,
			sesionTest2);

	private Resultado resultadoTest = new Resultado(1L, inscriptionTest, sesionGpTest, 1, 1);

	private Resultado resultadoTest2 = new Resultado(1L, inscriptionTest, sesionGpTest2, 0, 1);

	private Vuelta vueltaTest = new Vuelta(1, 2, resultadoTest);

	private Sancion sancionTest = new Sancion(1L, resultadoTest, TEST, 1, 1);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void buscarResultadosTest() {
		try {
			Mockito.when(inscripciones.buscarInscripcion(Mockito.anyLong())).thenReturn(inscriptionTest);
			Mockito.when(sesionGpService.buscarSesion(Mockito.anyLong())).thenReturn(sesionGpTest);
			List<Resultado> lista = new ArrayList<>();
			lista.add(resultadoTest);
			Mockito.when(resultadoRepo.findAll(Mockito.any(Example.class), Mockito.any(Sort.class))).thenReturn(lista);
			Mockito.when(vueltas.buscarVueltaRapida(Mockito.any())).thenReturn(vueltaTest);
			assertNotNull(service.buscarResultados(1L, 1L, 1L, 1, 1));
		} catch (RacesException e) {
			fail(e.getMessage());
		}

	}

	@Test
	@SuppressWarnings("unchecked")
	public void buscarResultados2Test() {
		List<Resultado> lista = new ArrayList<>();
		lista.add(resultadoTest2);
		Mockito.when(resultadoRepo.findAll(Mockito.any(Example.class), Mockito.any(Sort.class))).thenReturn(lista);
		Mockito.when(vueltas.buscarVueltaRapida(Mockito.any())).thenReturn(vueltaTest);
		assertNotNull(service.buscarResultados(null, null, null, null, null));

	}

	@Test
	public void buscarResultadosCatchTest() {
		try {
			Mockito.when(inscripciones.buscarInscripcion(Mockito.anyLong()))
					.thenThrow(new RacesException("Simulated Error"));
			Mockito.when(resultadoRepo.findAll()).thenReturn(new ArrayList<>());
			Mockito.when(vueltas.buscarVueltaRapida(Mockito.any())).thenReturn(vueltaTest);
			assertNotNull(service.buscarResultados(1L, 1L, 1L, 1, 1));
		} catch (RacesException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarResultadosValidos() {
		Mockito.when(resultadoRepo.findBySesionGPAndVueltasGreaterThan(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(new ArrayList<>());
		assertNotNull(service.buscarResultadosValidos(
				new SesionGP(1L, new Date(System.currentTimeMillis()), granPremioTest, sesionTest)));
	}

	@Test
	public void buscarResultadoTest() {
		try {
			Mockito.when(resultadoRepo.findById(Mockito.any())).thenReturn(Optional.of(resultadoTest));
			assertNotNull(service.buscarResultado(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarResultadoExceptionTest() {
		try {
			Mockito.when(resultadoRepo.findById(Mockito.any())).thenReturn(Optional.empty());
			service.buscarResultado(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void existeResultadoTest() {
		Mockito.when(resultadoRepo.findById(Mockito.any())).thenReturn(Optional.of(resultadoTest));
		assertNotNull(service.existeResultado(1L));
	}

	@Test
	public void crearResultadosTest() {
		List<SesionGP> listGP = new ArrayList<>();
		listGP.add(sesionGpTest);
		listGP.add(sesionGpTest2);
		List<Inscripcion> listInsc = new ArrayList<>();
		listInsc.add(inscriptionTest);
		Mockito.when(inscripciones.buscarInscripciones(Mockito.anyLong(), Mockito.any(), Mockito.any()))
				.thenReturn(listInsc);
		Mockito.when(resultadoRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
		service.crearResultados(listGP, campeonatoTest);
		assertTrue(true);
	}

	@Test
	public void actualizarResultadoTest() {
		Mockito.when(resultadoRepo.findById(Mockito.any())).thenReturn(Optional.of(resultadoTest));
		Mockito.when(resultadoRepo.save(Mockito.any())).thenReturn(resultadoTest);
		try {
			assertTrue(service.actualizarResultado(1L, new ResultadoDto(1L, 1L, 1, 1)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void aplicarSancionTest() {

		Mockito.when(resultadoRepo.save(Mockito.any())).thenReturn(resultadoTest);
		service.aplicarSancion(sancionTest);
		assertTrue(true);
	}

	@Test
	public void eliminarSancionTest() {

		Mockito.when(resultadoRepo.save(Mockito.any())).thenReturn(resultadoTest);
		service.eliminarSancion(sancionTest);
		assertTrue(true);
	}

	@Test
	public void crearResultadosInscripcipnTest() {
		List<GranPremioSesionesDto> lista = new ArrayList<>();
		List<SesionGP> listaSesiones = new ArrayList<>();
		listaSesiones.add(sesionGpTest);
		listaSesiones.add(sesionGpTest2);
		GranPremioSesionesDto dto = new GranPremioSesionesDto(granPremioTest, listaSesiones);
		lista.add(dto);
		Mockito.when(gpService.buscarGrandesPremios(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(lista);
		Mockito.when(resultadoRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
		service.crearResultados(inscriptionTest);
		assertTrue(true);
	}

}
