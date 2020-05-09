package com.races.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import com.races.dto.EquipoDto;
import com.races.entity.Equipo;
import com.races.exception.RacesException;
import com.races.repository.EquipoRepository;
import com.races.services.impl.EquipoServiceImpl;

public class EquipoServiceTest {

	@Mock
	EquipoRepository equipoRepo;

	@InjectMocks
	EquipoServiceImpl service;

	private static final String TEST = "test";

	Equipo equipoTest = new Equipo(1L, TEST, TEST);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void crearEquipoExceptionTest() {
		try {
			Mockito.when(equipoRepo.findOne(Mockito.any())).thenReturn(Optional.of(equipoTest));
			Mockito.when(equipoRepo.save(Mockito.any())).thenReturn(equipoTest);
			service.crearEquipo(new EquipoDto(TEST, TEST));
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void crearEquipoTest() {
		Mockito.when(equipoRepo.findOne(Mockito.any())).thenReturn(Optional.empty());
		Mockito.when(equipoRepo.save(Mockito.any())).thenReturn(equipoTest);
		try {
			assertNotNull(service.crearEquipo(new EquipoDto(TEST, TEST)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void actualizarEquipoTest() {
		try {
			Mockito.when(equipoRepo.findById(Mockito.any())).thenReturn(Optional.of(equipoTest));
			Mockito.when(equipoRepo.save(Mockito.any())).thenReturn(equipoTest);
			assertNotNull(service.actualizarEquipo(1L, new EquipoDto(TEST, TEST)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void actualizarEquipoExceptionTest() {
		Mockito.when(equipoRepo.findById(Mockito.any())).thenReturn(Optional.empty());
		try {
			service.actualizarEquipo(1L, new EquipoDto(TEST, TEST));
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void borrarEquipoTest() {
		try {
			Mockito.when(equipoRepo.findById(Mockito.any())).thenReturn(Optional.of(equipoTest));
			Mockito.doNothing().when(equipoRepo).delete(Mockito.any());
			assertTrue(service.borrarEquipo(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarEquipoExceptionTest() {
		Mockito.when(equipoRepo.findById(Mockito.any())).thenReturn(Optional.of(equipoTest));
		try {
			assertNotNull(service.buscarEquipo(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarEquipoTest() {
		try {
			Mockito.when(equipoRepo.findById(Mockito.any())).thenReturn(Optional.empty());
			service.buscarEquipo(1L);
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void buscarEquiposExceptionTest() {
		Mockito.when(equipoRepo.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarEquipos(null, null, null));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarEquiposTest() {
		Mockito.when(equipoRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarEquipos(null, TEST, TEST));
	}

	@Test
	public void existeEquipoTest() {
		Mockito.when(equipoRepo.findById(Mockito.any())).thenReturn(Optional.of(equipoTest));
		assertTrue(service.existeEquipo(1L));
	}

}
