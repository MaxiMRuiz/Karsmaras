package com.races.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.races.dto.SancionDto;
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
import com.races.exception.RacesException;
import com.races.repository.SancionRepository;
import com.races.services.impl.ResultadoServiceImpl;
import com.races.services.impl.SancionServiceImpl;

@RunWith(SpringRunner.class)
public class SancionServiceTest {

	@Mock
	SancionRepository sancionRepo;

	@Mock
	ResultadoServiceImpl resultadoService;

	@InjectMocks
	SancionServiceImpl service;

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

	private Sancion sancionTest = new Sancion(1L, resultadoTest, TEST, 1, 1);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearSancionTest() {
		try {
			Mockito.when(resultadoService.buscarResultado(Mockito.anyLong())).thenReturn(resultadoTest);
			Mockito.when(sancionRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.empty());
			Mockito.when(sancionRepo.save(Mockito.any())).thenReturn(sancionTest);
			Mockito.doNothing().when(resultadoService).aplicarSancion(Mockito.any());
			assertNotNull(service.crearSancion(new SancionDto(1L, TEST, 1, 1)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearSancionExceptionTest() {
		try {
			Mockito.when(resultadoService.buscarResultado(Mockito.anyLong())).thenReturn(resultadoTest);
			Mockito.when(sancionRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.of(sancionTest));
			service.crearSancion(new SancionDto(1L, TEST, 1, 1));
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarSancionesTest() {
		try {
			Mockito.when(resultadoService.buscarResultado(Mockito.anyLong())).thenReturn(resultadoTest);
			Mockito.when(sancionRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
			assertNotNull(service.buscarSanciones(1L, 1L, 1, 1));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSancionesExceptionTest() {
		try {
			Mockito.when(resultadoService.buscarResultado(Mockito.anyLong()))
					.thenThrow(new RacesException("Simulated Error"));
			Mockito.when(sancionRepo.findAll()).thenReturn(new ArrayList<>());
			service.buscarSanciones(1L, 1L, 1, 1);
			assertNotNull(service.buscarSanciones(1L, 1L, 1, 1));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarSancionesSinFiltrosTest() {
		Mockito.when(sancionRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarSanciones(null, null, null, null));
	}

	@Test
	public void buscarSancionTest() {
		try {
			Mockito.when(sancionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sancionTest));
			assertNotNull(service.buscarSancion(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSancionExceptionTest() {
		try {
			Mockito.when(sancionRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
			service.buscarSancion(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void existeSancionTest() {
		Mockito.when(sancionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sancionTest));
		assertNotNull(service.existeSancion(1L));
	}

	@Test
	public void borrarSancionTest() {
		try {
			Mockito.when(sancionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sancionTest));
			Mockito.doNothing().when(resultadoService).eliminarSancion(Mockito.any());
			Mockito.doNothing().when(sancionRepo).delete(Mockito.any());
			assertTrue(service.borrarSancion(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarSancionTest() {
		try {
			Mockito.when(sancionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sancionTest));
			Mockito.doNothing().when(resultadoService).eliminarSancion(Mockito.any());
			Mockito.when(sancionRepo.save(Mockito.any())).thenReturn(sancionTest);
			Mockito.doNothing().when(resultadoService).aplicarSancion(Mockito.any());
			assertNotNull(service.editarSancion(1L, new SancionDto(1L, TEST, 1, 1)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

}
