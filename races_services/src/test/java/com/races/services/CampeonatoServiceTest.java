package com.races.services;

import static org.junit.Assert.assertNotNull;
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

import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;
import com.races.entity.Reglamento;
import com.races.exception.RacesException;
import com.races.repository.CampeonatoRepository;
import com.races.services.impl.CampeonatoServiceImpl;
import com.races.services.impl.ReglamentoServiceImpl;

@RunWith(SpringRunner.class)
public class CampeonatoServiceTest {

	private static final String TEST = "TEST";

	@Mock
	CampeonatoRepository campeoRepo;

	@Mock
	ReglamentoServiceImpl reglaService;

	@InjectMocks
	CampeonatoServiceImpl service;

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	private Campeonato campeonatoTest = new Campeonato(1L, TEST, TEST, TEST, reglamentoTest);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void crearCampeonatoTest() {
		try {
			Mockito.when(reglaService.buscarReglamento(Mockito.anyLong())).thenReturn(reglamentoTest);
			Optional<Campeonato> op = Optional.empty();
			Mockito.when(campeoRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(campeoRepo.save(Mockito.any())).thenReturn(campeonatoTest);
			assertNotNull(service.crearCampeonato(new CampeonatoDto(TEST, TEST, TEST, 1L)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}
 
	@Test
	public void crearCampeonatoExistenteTest() {
		try {
			Mockito.when(reglaService.buscarReglamento(Mockito.anyLong())).thenReturn(reglamentoTest);
			Optional<Campeonato> op = Optional.of(campeonatoTest);
			Mockito.when(campeoRepo.findOne(Mockito.any())).thenReturn(op);
			Mockito.when(campeoRepo.save(Mockito.any())).thenReturn(campeonatoTest);
			service.crearCampeonato(new CampeonatoDto(TEST, TEST, TEST, 1L));
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void buscarCampeonatosFiltrosTest() {
		Mockito.when(campeoRepo.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarCampeonatos(null, null, null));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarCampeonatosAllTest() {
		Mockito.when(campeoRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarCampeonatos(1L, TEST, TEST));
	}

	@Test
	public void buscarCampeonatoTest() {
		try {
			Optional<Campeonato> op = Optional.of(campeonatoTest);
			Mockito.when(campeoRepo.findById(Mockito.any())).thenReturn(op);
			assertNotNull(service.buscarCampeonato(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarCampeonatoNoExistsTest() {
		try {
			Optional<Campeonato> op = Optional.empty();
			Mockito.when(campeoRepo.findById(Mockito.any())).thenReturn(op);
			service.buscarCampeonato(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void actualizarCampeonatoTest() {
		try {
			Optional<Campeonato> op = Optional.of(campeonatoTest);
			Mockito.when(campeoRepo.findById(Mockito.any())).thenReturn(op);
			Mockito.when(reglaService.buscarReglamento(Mockito.anyLong())).thenReturn(reglamentoTest);
			Mockito.when(campeoRepo.save(Mockito.any())).thenReturn(campeonatoTest);
			assertNotNull(service.actualizarCampeonato(1L, new CampeonatoDto(TEST, TEST, TEST, 1L)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void actualizarCampeonatoNoExistsTest() {
		try {
			Optional<Campeonato> op = Optional.empty();
			Mockito.when(campeoRepo.findById(Mockito.any())).thenReturn(op);
			service.actualizarCampeonato(1L, new CampeonatoDto(TEST, TEST, TEST, 1L));
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void borrarCampeonatoTest() {
		try {
			Optional<Campeonato> op = Optional.of(campeonatoTest);
			Mockito.when(campeoRepo.findById(Mockito.any())).thenReturn(op);
			assertNotNull(service.borrarCampeonato(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarCampeonatoNoExistsTest() {
		try {
			Optional<Campeonato> op = Optional.empty();
			Mockito.when(campeoRepo.findById(Mockito.any())).thenReturn(op);
			service.borrarCampeonato(1L);
			fail("Exception Expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void existeCampeonatoTest() {
		Optional<Campeonato> op = Optional.of(campeonatoTest);
		Mockito.when(campeoRepo.findById(Mockito.any())).thenReturn(op);
		assertNotNull(service.existeCampeonato(1L));
	}

}
