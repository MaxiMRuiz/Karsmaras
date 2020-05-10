package com.races.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Optional;

import org.jose4j.lang.JoseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.races.dto.PilotoDto;
import com.races.entity.Piloto;
import com.races.exception.RacesException;
import com.races.repository.PilotoRepository;
import com.races.services.impl.JwtServiceImpl;
import com.races.services.impl.PilotoServiceImpl;

@RunWith(SpringRunner.class)
public class PilotoServiceTest {

	@Mock
	PilotoRepository pilotoRepo;

	@Mock
	JwtServiceImpl jwtService;

	@InjectMocks
	PilotoServiceImpl service;

	private static final String TEST = "test";

	private Piloto pilotoTest = new Piloto(1L, TEST, TEST, TEST, TEST, true, TEST);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void crearPilotoExceptionTest() {
		try {
			Mockito.when(pilotoRepo.findOne(Mockito.any())).thenReturn(Optional.of(pilotoTest));
			service.crearPiloto(new PilotoDto(TEST, TEST, TEST, TEST, true));
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void crearPilotoTest() {
		try {
			Mockito.when(pilotoRepo.findOne(Mockito.any())).thenReturn(Optional.empty());
			Mockito.when(pilotoRepo.save(Mockito.any())).thenReturn(pilotoTest);
			Mockito.when(jwtService.encryptData(Mockito.anyString())).thenReturn(TEST);
			Mockito.when(jwtService.encodeBase64(Mockito.anyString())).thenReturn(TEST);
			Mockito.when(jwtService.getJWK(Mockito.anyString())).thenReturn(TEST);
			assertNotNull(service.crearPiloto(new PilotoDto(TEST, TEST, TEST, TEST, true)));
		} catch (RacesException | JoseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearPilotoJoseExceptionTest() {
		try {
			Mockito.when(pilotoRepo.findOne(Mockito.any())).thenReturn(Optional.empty());
			Mockito.when(pilotoRepo.save(Mockito.any())).thenReturn(pilotoTest);
			Mockito.when(jwtService.getJWK(Mockito.anyString())).thenThrow(new JoseException(TEST));
			service.crearPiloto(new PilotoDto(TEST, TEST, TEST, TEST, true));
			fail("Exception Expected");
		} catch (RacesException | JoseException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void actualizarpilotoTest() {
		try {
			Mockito.when(pilotoRepo.findById(Mockito.any())).thenReturn(Optional.of(pilotoTest));
			Mockito.when(pilotoRepo.save(Mockito.any())).thenReturn(pilotoTest);
			assertNotNull(service.editarPiloto(1L, new PilotoDto(TEST, TEST, TEST, TEST, true)));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void actualizarPilotoExceptionTest() {
		Mockito.when(pilotoRepo.findById(Mockito.any())).thenReturn(Optional.empty());
		try {
			service.editarPiloto(1L, new PilotoDto(TEST, TEST, TEST, TEST, true));
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void borrarPilotoTest() {
		try {
			Mockito.when(pilotoRepo.findById(Mockito.any())).thenReturn(Optional.of(pilotoTest));
			Mockito.doNothing().when(pilotoRepo).delete(Mockito.any());
			assertTrue(service.borrarPiloto(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPilotoExceptionTest() {
		Mockito.when(pilotoRepo.findById(Mockito.any())).thenReturn(Optional.of(pilotoTest));
		try {
			assertNotNull(service.buscarPiloto(1L));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPilotoTest() {
		try {
			Mockito.when(pilotoRepo.findById(Mockito.any())).thenReturn(Optional.empty());
			service.buscarPiloto(1L);
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void buscarPilotoAliasExceptionTest() {
		Mockito.when(pilotoRepo.findByApodo(Mockito.any())).thenReturn(Optional.of(pilotoTest));
		try {
			assertNotNull(service.buscarPiloto(TEST));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPilotoAliasTest() {
		try {
			Mockito.when(pilotoRepo.findByApodo(Mockito.any())).thenReturn(Optional.empty());
			service.buscarPiloto(TEST);
			fail("Exception expected");
		} catch (RacesException e) {
			assertNotNull(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscarPilotosTest() {
		Mockito.when(pilotoRepo.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList<>());
		assertNotNull(service.buscarPilotos(null, TEST, TEST, TEST));
	}

	@Test
	public void existePilotoTest() {
		Mockito.when(pilotoRepo.findById(Mockito.any())).thenReturn(Optional.of(pilotoTest));
		assertTrue(service.existePiloto(1L));
	}

	@Test
	public void setPilotoAdminTest() {
		Mockito.when(pilotoRepo.findById(Mockito.any())).thenReturn(Optional.of(pilotoTest));
		Mockito.when(pilotoRepo.save(Mockito.any())).thenReturn(pilotoTest);
		try {
			assertNotNull(service.setPilotoAdmin(1L, true));
		} catch (RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void changePasswordTest() {
		try {
			Mockito.when(pilotoRepo.findByApodo(Mockito.any())).thenReturn(Optional.of(pilotoTest));
			Mockito.when(jwtService.decodeData(Mockito.anyString())).thenReturn(TEST);
			Mockito.when(jwtService.encryptData(Mockito.anyString())).thenReturn(TEST);
			Mockito.when(jwtService.encodeBase64(Mockito.anyString())).thenReturn(TEST);
			Mockito.when(pilotoRepo.save(Mockito.any())).thenReturn(pilotoTest);
			assertTrue(service.changePassword(TEST, TEST.toCharArray(), TEST.toCharArray()));
		} catch (JoseException | RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void changePasswordInvalidPassTest() {
		try {
			Mockito.when(pilotoRepo.findByApodo(Mockito.any())).thenReturn(Optional.of(pilotoTest));
			Mockito.when(jwtService.decodeData(Mockito.anyString())).thenReturn("fail");
			service.changePassword(TEST, TEST.toCharArray(), TEST.toCharArray());
			fail("Exception Expected");
		} catch (JoseException | RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void authenticarUsuarioTest() {
		Mockito.when(pilotoRepo.findByApodo(Mockito.any())).thenReturn(Optional.of(pilotoTest));
		try {
			Mockito.when(jwtService.decodeData(Mockito.anyString())).thenReturn(TEST);
			Mockito.when(jwtService.generateJwt(Mockito.anyString(), Mockito.anyString()))
					.thenThrow(new JoseException("Simulated Error"));
			service.authenticarUsuario(TEST, TEST.toCharArray());
			fail("Exception Expected");
		} catch (JoseException | RacesException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void authenticarUsuarioJwtErrorTest() {
		Mockito.when(pilotoRepo.findByApodo(Mockito.any())).thenReturn(Optional.of(pilotoTest));
		try {
			Mockito.when(jwtService.decodeData(Mockito.anyString())).thenReturn(TEST);
			Mockito.when(jwtService.generateJwt(Mockito.anyString(), Mockito.anyString())).thenReturn(TEST);
			assertNotNull(service.authenticarUsuario(TEST, TEST.toCharArray()));
		} catch (JoseException | RacesException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void authenticarUsuarioInvalidPassTest() {
		Mockito.when(pilotoRepo.findByApodo(Mockito.any())).thenReturn(Optional.of(pilotoTest));
		try {
			Mockito.when(jwtService.decodeData(Mockito.anyString())).thenReturn("fail");
			service.authenticarUsuario(TEST, TEST.toCharArray());
			fail("Exception Expected");
		} catch (JoseException | RacesException e) {
			assertNotNull(e);
		}
	}

}
