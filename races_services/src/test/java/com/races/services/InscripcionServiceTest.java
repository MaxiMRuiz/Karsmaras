package com.races.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.races.dto.InscripcionDto;
import com.races.entity.Campeonato;
import com.races.entity.Equipo;
import com.races.entity.Inscripcion;
import com.races.entity.Piloto;
import com.races.entity.Reglamento;
import com.races.exception.RacesException;
import com.races.repository.InscripcionRepository;
import com.races.services.impl.CampeonatoServiceImpl;
import com.races.services.impl.EquipoServiceImpl;
import com.races.services.impl.InscripcionServiceImpl;
import com.races.services.impl.PilotoServiceImpl;
import com.races.services.impl.ResultadoServiceImpl;

public class InscripcionServiceTest {

	@Mock
	InscripcionRepository inscriptionRepo;

	@Mock
	PilotoServiceImpl pilotoService;

	@Mock
	CampeonatoServiceImpl campeonatoService;

	@Mock
	ResultadoServiceImpl resultadoService;

	@Mock
	EquipoServiceImpl equipoService;

	@InjectMocks
	InscripcionServiceImpl service;

	private static final String TEST = "test";

	private Piloto pilotoTest = new Piloto(1L, TEST, TEST, TEST, TEST, true, TEST);

	private Equipo equipoTest = new Equipo(1L, TEST, TEST);

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	private Campeonato campeonatoTest = new Campeonato(1L, TEST, TEST, TEST, reglamentoTest);

	private Inscripcion inscriptionTest = new Inscripcion(campeonatoTest, pilotoTest, equipoTest);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearInscripcionTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyLong())).thenReturn(pilotoTest);
			Mockito.when(equipoService.buscarEquipo(Mockito.anyLong())).thenReturn(equipoTest);
			Mockito.when(inscriptionRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.empty());
			Mockito.when(inscriptionRepo.countDisctinctEquipoByCampeonato(Mockito.any())).thenReturn(0);
			Mockito.when(inscriptionRepo.save(Mockito.any())).thenReturn(inscriptionTest);
			Mockito.doNothing().when(resultadoService).crearResultados(Mockito.any());
			assertNotNull(service.crearInscripcion(new InscripcionDto(1L, 1L, 1L)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearInscripcionLiminTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyLong())).thenReturn(pilotoTest);
			Mockito.when(equipoService.buscarEquipo(Mockito.anyLong())).thenReturn(equipoTest);
			Mockito.when(inscriptionRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.empty());
			Mockito.when(inscriptionRepo.countDisctinctEquipoByCampeonato(Mockito.any())).thenReturn(5);
			Mockito.when(inscriptionRepo.save(Mockito.any())).thenReturn(inscriptionTest);
			Mockito.doNothing().when(resultadoService).crearResultados(Mockito.any());
			service.crearInscripcion(new InscripcionDto(1L, 1L, 1L));
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearInscripcionExistenteTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyLong())).thenReturn(pilotoTest);
			Mockito.when(equipoService.buscarEquipo(Mockito.anyLong())).thenReturn(equipoTest);
			Mockito.when(inscriptionRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.of(inscriptionTest));
			service.crearInscripcion(new InscripcionDto(1L, 1L, 1L));
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void buscarInscripcionTest() {
		Mockito.when(inscriptionRepo.findById(Mockito.any())).thenReturn(Optional.of(inscriptionTest));
		try {
			assertNotNull(service.buscarInscripcion(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarInscripcionExceptionTest() {
		Mockito.when(inscriptionRepo.findById(Mockito.any())).thenReturn(Optional.empty());
		try {
			service.buscarInscripcion(1L);
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void buscarPilotosTest() {
		List<Inscripcion> listaPilotos = new ArrayList<>();
		listaPilotos.add(inscriptionTest);
		Mockito.when(inscriptionRepo.findDistinctPilotoByCampeonato(Mockito.any())).thenReturn(listaPilotos);
		assertNotNull(service.buscarPilotos(campeonatoTest));
	}

	@Test
	public void existeInscripcionTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyLong())).thenReturn(pilotoTest);
			Mockito.when(equipoService.buscarEquipo(Mockito.anyLong())).thenReturn(equipoTest);
			Mockito.when(
					inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(Optional.of(inscriptionTest));
			assertTrue(service.existeInscripcion(new InscripcionDto(1L, 1L, 1L)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void existeInscripcionFalseTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyLong())).thenReturn(pilotoTest);
			Mockito.when(equipoService.buscarEquipo(Mockito.anyLong())).thenReturn(equipoTest);
			Mockito.when(
					inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(Optional.empty());
			assertFalse(service.existeInscripcion(new InscripcionDto(1L, 1L, 1L)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void existeInscripcionExceptionTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong()))
					.thenThrow(new RacesException("Simulated Error"));
			assertFalse(service.existeInscripcion(new InscripcionDto(1L, 1L, 1L)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarInscripcionSingleTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyLong())).thenReturn(pilotoTest);
			Mockito.when(equipoService.buscarEquipo(Mockito.anyLong())).thenReturn(equipoTest);
			Mockito.when(
					inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(Optional.of(inscriptionTest));
			assertNotNull(service.buscarInscripcion(1L, 1L, 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarInscripcionSingleExceptionTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyLong())).thenReturn(pilotoTest);
			Mockito.when(equipoService.buscarEquipo(Mockito.anyLong())).thenReturn(equipoTest);
			Mockito.when(
					inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(Optional.empty());
			service.buscarInscripcion(1L, 1L, 1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void borrarInscripcionTest() {
		try {
			Mockito.when(inscriptionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(inscriptionTest));
			assertTrue(service.borrarInscripcion(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarInscripcionExceptionTest() {
		try {
			Mockito.when(inscriptionRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
			service.borrarInscripcion(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarInscripcionesTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(pilotoService.buscarPiloto(Mockito.anyLong())).thenReturn(pilotoTest);
			Mockito.when(equipoService.buscarEquipo(Mockito.anyLong())).thenReturn(equipoTest);
			Mockito.when(inscriptionRepo.findAll(Mockito.any(Example.class), Mockito.any(Sort.class)))
					.thenReturn(new ArrayList<>());
			assertNotNull(service.buscarInscripciones(1L, 1L, 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void buscarInscripcionesSinFiltrosTest() {
			Mockito.when(inscriptionRepo.findAll(Mockito.any(Example.class), Mockito.any(Sort.class)))
					.thenReturn(new ArrayList<>());
			assertNotNull(service.buscarInscripciones(null,null,null));
	}

	@Test
	public void buscarInscripcionesExceptionTest() {
		try {
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong()))
					.thenThrow(new RacesException("Simulated Error"));
			Mockito.when(inscriptionRepo.findAll()).thenReturn(new ArrayList<>());
			assertNotNull(service.buscarInscripciones(1L, 1L, 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

}
