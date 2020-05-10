package com.races.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import com.races.entity.Reglamento;
import com.races.entity.Sesion;
import com.races.entity.TipoSesion;
import com.races.exception.RacesException;
import com.races.repository.SesionRepository;
import com.races.services.impl.PuntuacionServiceImpl;
import com.races.services.impl.ReglamentoServiceImpl;
import com.races.services.impl.ResultadoServiceImpl;
import com.races.services.impl.SesionServiceImpl;
import com.races.services.impl.TipoSesionServiceImpl;

@RunWith(SpringRunner.class)
public class SesionServiceTest {

	@Mock
	SesionRepository sesionRepo;

	@Mock
	ReglamentoServiceImpl reglamentoService;

	@Mock
	TipoSesionServiceImpl tipoSesionService;

	@Mock
	ResultadoServiceImpl resultados;

	@Mock
	PuntuacionServiceImpl puntuaciones;

	@InjectMocks
	SesionServiceImpl service;

	private static final String TEST = "test";

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);
	
	private Reglamento reglamentoTest2 = new Reglamento(1L, TEST, 2, 2, 2, 10, 5);

	private TipoSesion tipoSesionTest = new TipoSesion(1L, TEST);

	private Sesion sesionTest = new Sesion(1L, TEST, reglamentoTest, tipoSesionTest);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarSesionesTest() {
		try {
			Mockito.when(reglamentoService.buscarReglamento(Mockito.anyLong())).thenReturn(reglamentoTest);
			Mockito.when(tipoSesionService.buscarTipoSesion(Mockito.anyLong())).thenReturn(tipoSesionTest);
			Mockito.when(sesionRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
			assertNotNull(service.buscarSesiones(1L, 1L, TEST, 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarSesionesSinFiltrosTest() {
		Mockito.when(sesionRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarSesiones(null, null, null, null));
	}

	@Test
	public void buscarSesionesCatchTest() {
		try {
			Mockito.when(reglamentoService.buscarReglamento(Mockito.anyLong()))
					.thenThrow(new RacesException("Simulated Error", new Exception("Simulated Exception")));
			Mockito.when(sesionRepo.findAll()).thenReturn(new ArrayList<>());
			assertNotNull(service.buscarSesiones(1L, 1L, TEST, 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSesionTest() {
		try {
			Mockito.when(sesionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sesionTest));
			assertNotNull(service.buscarSesion(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSesionExceptionTest() {
		try {
			Mockito.when(sesionRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
			service.buscarSesion(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void existeSesionTest() {
		Mockito.when(sesionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sesionTest));
		assertNotNull(service.existeSesion(1L));
	}

	@Test
	public void crearSesionesReglamentoTest() {
		try {
			Mockito.when(tipoSesionService.buscarTipoSesion(Mockito.anyLong())).thenReturn(tipoSesionTest);
			Mockito.doNothing().when(puntuaciones).crearPuntuacionesSesion(Mockito.any());
			service.crearSesionesReglamento(reglamentoTest);
			assertTrue(true);
		} catch (RacesException e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	public void crearSesionesReglamento2Test() {
		try {
			Mockito.when(tipoSesionService.buscarTipoSesion(Mockito.anyLong())).thenReturn(tipoSesionTest);
			Mockito.doNothing().when(puntuaciones).crearPuntuacionesSesion(Mockito.any());
			service.crearSesionesReglamento(reglamentoTest2);
			assertTrue(true);
		} catch (RacesException e) {
			fail(e.getMessage());
		}

	}
}
