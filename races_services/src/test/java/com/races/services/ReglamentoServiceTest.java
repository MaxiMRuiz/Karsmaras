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

import com.races.dto.ReglamentoDto;
import com.races.entity.Reglamento;
import com.races.exception.RacesException;
import com.races.repository.ReglamentoRepository;
import com.races.services.impl.ReglamentoServiceImpl;
import com.races.services.impl.SesionServiceImpl;

@RunWith(SpringRunner.class)
public class ReglamentoServiceTest {

	@Mock
	ReglamentoRepository reglaRepo;

	@Mock
	SesionServiceImpl sesiones;

	@InjectMocks
	ReglamentoServiceImpl service;

	private static final String TEST = "test";

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearReglamentoTest() {

		try {
			Mockito.when(reglaRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.empty());
			Mockito.doNothing().when(sesiones).crearSesionesReglamento(Mockito.any());
			Mockito.when(reglaRepo.save(Mockito.any())).thenReturn(reglamentoTest);
			assertNotNull(service.crearReglamento(new ReglamentoDto(TEST, 1, 1, 1, 1, 1)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearReglamentoExistsTest() {

		try {
			Mockito.when(reglaRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.of(reglamentoTest));
			service.crearReglamento(new ReglamentoDto(TEST, 1, 1, 1, 1, 1));
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarReglamentosTest() {

		try {
			Mockito.when(reglaRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
			Mockito.doNothing().when(sesiones).crearSesionesReglamento(Mockito.any());
			assertNotNull(service.buscarReglamentos(1L, TEST, 1, 1, 1, 1, 1));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarReglamentoTest() {

		try {
			Mockito.when(reglaRepo.findById(Mockito.any())).thenReturn(Optional.of(reglamentoTest));
			Mockito.doNothing().when(sesiones).crearSesionesReglamento(Mockito.any());
			assertNotNull(service.buscarReglamento(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarReglamentoExceptionTest() {

		try {
			Mockito.when(reglaRepo.findById(Mockito.any())).thenReturn(Optional.empty());
			Mockito.doNothing().when(sesiones).crearSesionesReglamento(Mockito.any());
			service.buscarReglamento(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void borrarReglamentoTest() {

		try {
			Mockito.when(reglaRepo.findById(Mockito.any())).thenReturn(Optional.of(reglamentoTest));
			Mockito.doNothing().when(reglaRepo).delete(Mockito.any());
			assertTrue(service.borrarReglamento(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void existeReglamentoTest() {

		Mockito.when(reglaRepo.findById(Mockito.any())).thenReturn(Optional.of(reglamentoTest));
		assertNotNull(service.existeReglamento(1L));
	}

	@Test
	public void actualizarReglamentoTest() {
		Mockito.when(reglaRepo.findById(Mockito.any())).thenReturn(Optional.of(reglamentoTest));
		Mockito.when(reglaRepo.save(Mockito.any())).thenReturn(reglamentoTest);
		try {
			assertNotNull(service.actualizarReglamento(1L, new ReglamentoDto(TEST, 1, 1, 1, 1, 1)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

}
