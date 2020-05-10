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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.races.dto.SesionGpDto;
import com.races.entity.Campeonato;
import com.races.entity.GranPremio;
import com.races.entity.Reglamento;
import com.races.entity.Sesion;
import com.races.entity.SesionGP;
import com.races.entity.TipoSesion;
import com.races.exception.RacesException;
import com.races.repository.SesionGpRepository;
import com.races.services.impl.GranPremioServiceImpl;
import com.races.services.impl.ResultadoServiceImpl;
import com.races.services.impl.SesionGpServiceImpl;
import com.races.services.impl.SesionServiceImpl;

@RunWith(SpringRunner.class)
public class SesionGpServiceTest {

	@Mock
	SesionGpRepository sesionGpRepo;

	@Mock
	GranPremioServiceImpl gpService;

	@Mock
	SesionServiceImpl sesionService;

	@Mock
	ResultadoServiceImpl resultados;

	@InjectMocks
	SesionGpServiceImpl service;

	private static final String TEST = "test";

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	private Campeonato campeonatoTest = new Campeonato(1L, TEST, TEST, TEST, reglamentoTest);

	private GranPremio granPremioTest = new GranPremio(1L, campeonatoTest, TEST);

	private TipoSesion tipoSesionTest = new TipoSesion(1L, TEST);

	private Sesion sesionTest = new Sesion(1L, TEST, reglamentoTest, tipoSesionTest);

	private SesionGP sesionGpTest = new SesionGP(1L, new Date(System.currentTimeMillis()), granPremioTest, sesionTest);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	@SuppressWarnings("unchecked")
	public void buscarSesionesFullTest() {

		try {
			Mockito.when(gpService.buscarGranPremio(Mockito.anyLong())).thenReturn(granPremioTest);
			Mockito.when(sesionService.buscarSesion(Mockito.anyLong())).thenReturn(sesionTest);
			List<SesionGP> lista = new ArrayList<>();
			lista.add(sesionGpTest);
			Mockito.when(sesionGpRepo.findAll(Mockito.any(Example.class))).thenReturn(lista);
			assertNotNull(service.buscarSesiones(1L, 1L, new Date(System.currentTimeMillis() - 1000), 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void buscarSesionesDateBefroreTest() {

		try {
			Mockito.when(gpService.buscarGranPremio(Mockito.anyLong())).thenReturn(granPremioTest);
			Mockito.when(sesionService.buscarSesion(Mockito.anyLong())).thenReturn(sesionTest);
			List<SesionGP> lista = new ArrayList<>();
			lista.add(sesionGpTest);
			Mockito.when(sesionGpRepo.findAll(Mockito.any(Example.class))).thenReturn(lista);
			assertNotNull(service.buscarSesiones(1L, 1L, new Date(System.currentTimeMillis() + 1000), 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSesionesCatchTest() {

		try {
			Mockito.when(gpService.buscarGranPremio(Mockito.anyLong()))
					.thenThrow(new RacesException("Simulated Error", new Exception("Simulated Error"), false, false));
			Mockito.when(sesionGpRepo.findAll()).thenReturn(new ArrayList<>());
			assertNotNull(service.buscarSesiones(1L, 1L, new Date(System.currentTimeMillis() - 1000), 1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarSesionesSinFiltrosTest() {

		Mockito.when(sesionGpRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarSesiones(null, null, null, null));
	}

	@Test
	public void buscarSesionTest() {

		try {
			Mockito.when(sesionGpRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sesionGpTest));
			assertNotNull(service.buscarSesion(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSesionExceptionTest() {

		try {
			Mockito.when(sesionGpRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
			service.buscarSesion(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void existeSesionTest() {

		Mockito.when(sesionGpRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sesionGpTest));
		assertNotNull(service.existeSesion(1L));
	}

	@Test
	public void updateSesionGpTest() {
		Mockito.when(sesionGpRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(sesionGpTest));
		Mockito.when(sesionGpRepo.save(Mockito.any())).thenReturn(sesionGpTest);
		try {
			assertNotNull(service.updateSesionGp(1L, new SesionGpDto(new Date(System.currentTimeMillis()))));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearSesionesGranPremioTest() {
		List<Sesion> list = new ArrayList<>();
		list.add(sesionTest);
		Mockito.when(sesionService.buscarSesiones(Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(list);
		Mockito.doNothing().when(resultados).crearResultados(Mockito.any(), Mockito.any());
		service.crearSesionesGranPremio(granPremioTest, new Date(System.currentTimeMillis()));
		assertTrue(true);
	}

}
