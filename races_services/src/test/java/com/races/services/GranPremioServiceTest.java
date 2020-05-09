package com.races.services;

import static org.junit.Assert.assertNotNull;
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
import org.springframework.data.domain.Example;

import com.races.dto.GranPremioDto;
import com.races.entity.Campeonato;
import com.races.entity.GranPremio;
import com.races.entity.Reglamento;
import com.races.exception.RacesException;
import com.races.repository.GranPremioRepository;
import com.races.services.impl.CampeonatoServiceImpl;
import com.races.services.impl.GranPremioServiceImpl;
import com.races.services.impl.SesionGpServiceImpl;

public class GranPremioServiceTest {

	@Mock
	GranPremioRepository gpRepo;

	@Mock
	SesionGpServiceImpl sesionesGP;

	@Mock
	CampeonatoServiceImpl campeonatoService;

	@InjectMocks
	GranPremioServiceImpl service;

	private static final String TEST = "test";

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	private Campeonato campeonatoTest = new Campeonato(1L, TEST, TEST, TEST, reglamentoTest);

	private GranPremio granPremioTest = new GranPremio(1L, campeonatoTest, TEST);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearGranPremioTest() {
		try {
			Mockito.when(campeonatoService.existeCampeonato(Mockito.anyLong())).thenReturn(true);
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(gpRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.empty());
			Mockito.when(gpRepo.save(Mockito.any())).thenReturn(granPremioTest);
			Mockito.doNothing().when(sesionesGP).crearSesionesGranPremio(Mockito.any(), Mockito.any());
			assertNotNull(service.crearGranPremio(new GranPremioDto(1L, TEST, new Date(System.currentTimeMillis()))));
		} catch (RacesException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void crearGranPremioCampeonatoNoExisteTest() {
		try {
			service.crearGranPremio(new GranPremioDto(1L, TEST, new Date(System.currentTimeMillis())));
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void crearGranPremioExceptionTest() {
		try {
			Mockito.when(campeonatoService.existeCampeonato(Mockito.anyLong())).thenReturn(true);
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			Mockito.when(gpRepo.findOne(Mockito.any(Example.class))).thenReturn(Optional.of(granPremioTest));
			service.crearGranPremio(new GranPremioDto(1L, TEST, new Date(System.currentTimeMillis())));
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarGrandesPremiosTest() {
		try {
			List<GranPremio> lista = new ArrayList<>();
			lista.add(granPremioTest);
			Mockito.when(gpRepo.findAll(Mockito.any(Example.class))).thenReturn(lista);
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			assertNotNull(service.buscarGrandesPremios(1L, TEST, 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarGrandesPremiosSinFiltrosTest() {
		try {
			List<GranPremio> lista = new ArrayList<>();
			lista.add(granPremioTest);
			Mockito.when(gpRepo.findAll(Mockito.any(Example.class))).thenReturn(lista);
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong())).thenReturn(campeonatoTest);
			assertNotNull(service.buscarGrandesPremios(null, null, null));
		} catch (RacesException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarGrandesPremiosExceptionTest() {
		try {
			List<GranPremio> lista = new ArrayList<>();
			lista.add(granPremioTest);
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.anyLong()))
					.thenThrow(new RacesException("Simulated Error"));
			assertNotNull(service.buscarGrandesPremios(1L, TEST, 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarGranPremioTest() {
		try {
			Mockito.when(gpRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(granPremioTest));
			assertNotNull(service.buscarGranPremio(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarGranPremioExceptionTest() {
		try {
			Mockito.when(gpRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
			service.buscarGranPremio(1L);
			fail("Expected Error");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void borrarGranPremioTest() {
		try {
			Mockito.when(gpRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(granPremioTest));
			Mockito.doNothing().when(gpRepo).delete(Mockito.any());
			assertNotNull(service.borrarGranPremio(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarGranPremioExceptionTest() {
		try {
			Mockito.when(gpRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
			service.borrarGranPremio(1L);
			fail("Expected Error");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void existeGranPremioTest() {
		Mockito.when(gpRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(granPremioTest));
		assertNotNull(service.existeGranPremio(1L));
	}

}
