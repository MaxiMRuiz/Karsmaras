package com.races.services;

import static org.junit.Assert.assertNotNull;
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

import com.races.entity.TipoSesion;
import com.races.exception.RacesException;
import com.races.repository.TipoSesionRepository;
import com.races.services.impl.TipoSesionServiceImpl;

public class TipoSesionServiceTest {

	private static final String TEST = "test";

	@Mock
	TipoSesionRepository tipoSesionRepo;

	@InjectMocks
	TipoSesionServiceImpl service;

	TipoSesion tipoSesionTest = new TipoSesion(1L, TEST);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void buscarTipoSesionesTest() {
		Mockito.when(tipoSesionRepo.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarTipoSesiones(null, null));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarTipoSesionesFiltersTest() {
		Mockito.when(tipoSesionRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarTipoSesiones(1L, TEST));
	}

	@Test
	public void buscarTipoSesionTest() {
		Mockito.when(tipoSesionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(tipoSesionTest));
		try {
			assertNotNull(service.buscarTipoSesion(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarTipoSesionNotFoundTest() {
		Mockito.when(tipoSesionRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		try {
			service.buscarTipoSesion(null);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void existeTipoSesionTest() {
		Mockito.when(tipoSesionRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(tipoSesionTest));
		assertNotNull(service.existeTipoSesion(1L));
	}

}
