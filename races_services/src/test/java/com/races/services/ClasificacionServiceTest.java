package com.races.services;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.races.dto.GranPremioSesionesDto;
import com.races.dto.ResultadoResponseDto;
import com.races.entity.Campeonato;
import com.races.entity.Equipo;
import com.races.entity.GranPremio;
import com.races.entity.Inscripcion;
import com.races.entity.Piloto;
import com.races.entity.Puntuacion;
import com.races.entity.Reglamento;
import com.races.entity.Resultado;
import com.races.entity.Sancion;
import com.races.entity.Sesion;
import com.races.entity.SesionGP;
import com.races.entity.TipoSesion;
import com.races.services.impl.CampeonatoServiceImpl;
import com.races.services.impl.ClasificacionServiceImpl;
import com.races.services.impl.GranPremioServiceImpl;
import com.races.services.impl.PuntuacionServiceImpl;
import com.races.services.impl.ResultadoServiceImpl;
import com.races.services.impl.SancionServiceImpl;
import com.races.services.impl.SesionServiceImpl;

public class ClasificacionServiceTest {

	@Mock
	GranPremioServiceImpl gpService;

	@Mock
	CampeonatoServiceImpl campeonatoService;

	@Mock
	PuntuacionServiceImpl puntuacionService;

	@Mock
	SesionServiceImpl sesionService;

	@Mock
	ResultadoServiceImpl resultadoService;

	@Mock
	SancionServiceImpl sancionService;

	@InjectMocks
	ClasificacionServiceImpl service;

	private static final String TEST = "test";

	private Piloto pilotoTest = new Piloto(1L, TEST, TEST, TEST, TEST, true, TEST);

	private Equipo equipoTest = new Equipo(1L, TEST, TEST);

	private Reglamento reglamentoTest = new Reglamento(1L, TEST, 1, 1, 1, 10, 5);

	private Campeonato campeonatoTest = new Campeonato(1L, TEST, TEST, TEST, reglamentoTest);

	private Inscripcion inscriptionTest = new Inscripcion(1L, campeonatoTest, pilotoTest, equipoTest);

	private GranPremio granPremioTest = new GranPremio(1L, campeonatoTest, TEST);

	private GranPremio granPremioTest2 = new GranPremio(1L, campeonatoTest, TEST);

	private TipoSesion tipoSesionTest = new TipoSesion(3L, TEST);

	private Sesion sesionTest = new Sesion(1L, TEST, reglamentoTest, tipoSesionTest);

	private SesionGP sesionGpTest = new SesionGP(1L, new Date(System.currentTimeMillis()), granPremioTest, sesionTest);

	private SesionGP sesionGpTest2 = new SesionGP(1L, new Date(System.currentTimeMillis()), granPremioTest2,
			sesionTest);

	private Resultado resultadoTest = new Resultado(1L, inscriptionTest, sesionGpTest, 1, 1);

	private Sancion sancionTest = new Sancion(1L, resultadoTest, TEST, 1, 1);

	private Puntuacion puntuacionTest = new Puntuacion(sesionTest, 1, 1);

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void calcularClasificacionGpTest() {

		List<SesionGP> listaSesiones = new ArrayList<>();
		listaSesiones.add(sesionGpTest);
		listaSesiones.add(sesionGpTest2);

		List<GranPremioSesionesDto> listaGP = new ArrayList<>();
		listaGP.add(new GranPremioSesionesDto(granPremioTest, listaSesiones));

		Mockito.when(gpService.buscarGrandesPremios(Mockito.anyLong(), Mockito.any(), Mockito.any()))
				.thenReturn(listaGP);

		List<Puntuacion> listaPuntuacion = new ArrayList<>();
		listaPuntuacion.add(puntuacionTest);
		Mockito.when(puntuacionService.buscarPuntuacionesValidas(Mockito.anyLong())).thenReturn(listaPuntuacion);

		List<ResultadoResponseDto> listaResultados = new ArrayList<>();
		listaResultados.add(new ResultadoResponseDto(1L, inscriptionTest, sesionGpTest, 1, 1, 1));
		listaResultados.add(new ResultadoResponseDto(1L, inscriptionTest, sesionGpTest2, 1, 1, 1));
		Mockito.when(resultadoService.buscarResultadosValidos(Mockito.any())).thenReturn(listaResultados);
		List<Sancion> listaSanciones = new ArrayList<>();
		listaSanciones.add(sancionTest);
		Mockito.when(sancionService.buscarSanciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(listaSanciones);
		assertNotNull(service.calcularClasificacionGp(1L));
	}
	
	@Test
	public void calcularClasificacionGpEmptyTest() {

		List<SesionGP> listaSesiones = new ArrayList<>();
		listaSesiones.add(sesionGpTest);
		listaSesiones.add(sesionGpTest2);

		List<GranPremioSesionesDto> listaGP = new ArrayList<>();

		Mockito.when(gpService.buscarGrandesPremios(Mockito.anyLong(), Mockito.any(), Mockito.any()))
				.thenReturn(listaGP);

		assertNotNull(service.calcularClasificacionGp(1L));
	}

	@Test
	public void calcularClasificacionCampeonatoTest() {

		List<SesionGP> listaSesiones = new ArrayList<>();
		listaSesiones.add(sesionGpTest);
		listaSesiones.add(sesionGpTest2);

		List<GranPremioSesionesDto> listaGP = new ArrayList<>();
		listaGP.add(new GranPremioSesionesDto(granPremioTest, listaSesiones));

		Mockito.when(gpService.buscarGrandesPremios(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(listaGP);

		List<Puntuacion> listaPuntuacion = new ArrayList<>();
		listaPuntuacion.add(puntuacionTest);
		Mockito.when(puntuacionService.buscarPuntuacionesValidas(Mockito.anyLong())).thenReturn(listaPuntuacion);

		List<ResultadoResponseDto> listaResultados = new ArrayList<>();
		listaResultados.add(new ResultadoResponseDto(1L, inscriptionTest, sesionGpTest, 1, 1, 1));
		listaResultados.add(new ResultadoResponseDto(1L, inscriptionTest, sesionGpTest2, 1, 1, 1));
		Mockito.when(resultadoService.buscarResultadosValidos(Mockito.any())).thenReturn(listaResultados);
		List<Sancion> listaSanciones = new ArrayList<>();
		listaSanciones.add(sancionTest);
		Mockito.when(sancionService.buscarSanciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(listaSanciones);
		assertNotNull(service.calcularClasificacionCampeonato(1L));
	}
	
}
