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
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.entity.Reglamento;
import com.races.entity.Sesion;
import com.races.entity.TipoSesion;
import com.races.exception.RacesException;
import com.races.repository.PuntuacionRepository;
import com.races.services.impl.PuntuacionServiceImpl;

@RunWith(SpringRunner.class)
public class PuntuacionServiceTest {

	@Mock
	PuntuacionRepository puntuacionRepo;

	@Mock
	SesionService sesionService;

	@Mock
	Environment env;

	@InjectMocks
	PuntuacionServiceImpl service;

	private static final String TEST = "test";

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	private TipoSesion tipoSesionTest = new TipoSesion(1L, TEST);

	private Sesion sesionTest = new Sesion(1L, TEST, reglamentoTest, tipoSesionTest);

	private Puntuacion puntuacionTest = new Puntuacion(sesionTest, 1, 1);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarPuntuacionesTest() {
		try {
			Mockito.when(sesionService.buscarSesion(Mockito.anyLong())).thenReturn(sesionTest);
			Mockito.when(puntuacionRepo.findAll(Mockito.any(Example.class), Mockito.any(Sort.class)))
					.thenReturn(new ArrayList<>());
			assertNotNull(service.buscarPuntuaciones(1L, 1L, 1, 1));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarPuntuacionesSinFiltrosTest() {
		Mockito.when(puntuacionRepo.findAll(Mockito.any(Example.class), Mockito.any(Sort.class)))
				.thenReturn(new ArrayList<>());
		assertNotNull(service.buscarPuntuaciones(null, null, null, null));
	}

	@Test
	public void buscarPuntuacionesCatchTest() {
		try {
			Mockito.when(sesionService.buscarSesion(Mockito.anyLong()))
					.thenThrow(new RacesException("Simulated Error"));
			Mockito.when(puntuacionRepo.findAll()).thenReturn(new ArrayList<>());
			assertNotNull(service.buscarPuntuaciones(1L, 1L, 1, 1));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPuntuacionesValidasTest() {
		Mockito.when(puntuacionRepo.findBySesionIdAndPuntosGreaterThan(Mockito.anyLong(), Mockito.anyInt()))
				.thenReturn(new ArrayList<>());
		assertNotNull(service.buscarPuntuacionesValidas(1L));
	}

	@Test
	public void buscarPuntuacionTest() {
		Mockito.when(puntuacionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(puntuacionTest));
		try {
			assertNotNull(service.buscarPuntuacion(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPuntuacionExceptionTest() {
		Mockito.when(puntuacionRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		try {
			service.buscarPuntuacion(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void actualizarPuntuacionTest() {
		Mockito.when(puntuacionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(puntuacionTest));
		Mockito.when(puntuacionRepo.save(Mockito.any())).thenReturn(puntuacionTest);
		try {
			assertNotNull(service.actualizarPuntuacion(1L, new PuntuacionDto(1L, 1, 2, 1L)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void existePuntuacionTest() {
		Mockito.when(puntuacionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(puntuacionTest));
		assertNotNull(service.existePuntuacion(1L));
		
	}

	@Test
	public void crearPuntuacionesSesionTest() {
		Mockito.when(puntuacionRepo.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
		service.crearPuntuacionesSesion(sesionTest);
		assertTrue(true);
	}
}
