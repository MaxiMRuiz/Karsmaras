package com.races.portal.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Campeonato;
import com.races.portal.dto.Equipo;
import com.races.portal.dto.GranPremio;
import com.races.portal.dto.Inscripcion;
import com.races.portal.dto.Piloto;
import com.races.portal.dto.Puntuacion;
import com.races.portal.dto.Reglamento;
import com.races.portal.dto.Resultado;
import com.races.portal.dto.Sesion;
import com.races.portal.dto.TipoSesion;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

@Component
public class Converter {

	/**
	 * Conversor de JSONObject a Campeonato
	 * 
	 * @param json
	 * @return
	 */
	public Campeonato json2Campeonato(JSONObject json) {
		Campeonato campeonato = new Campeonato();
		campeonato.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		campeonato.setNombre(json.isNull(Constants.PARAM_NOMBRE) ? "null" : json.getString(Constants.PARAM_NOMBRE));
		campeonato.setTemporada(
				json.isNull(Constants.PARAM_TEMPORADA) ? "null" : json.getString(Constants.PARAM_TEMPORADA));
		campeonato.setReglamento(json.isNull(Constants.PARAM_REGLAMENTO) ? "0"
				: "" + json.getJSONObject(Constants.PARAM_REGLAMENTO).getLong(Constants.PARAM_ID));
		campeonato.setDescripcion(
				json.isNull(Constants.PARAM_DESCRIPCION) ? "" : json.getString(Constants.PARAM_DESCRIPCION));
		return campeonato;
	}

	/**
	 * Conversor de JSONObject a Reglamento
	 * 
	 * @param json
	 * @return
	 */
	public Reglamento json2Reglamento(JSONObject json) {
		Reglamento reglamento = new Reglamento();
		reglamento.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		reglamento.setDescripcion(
				json.isNull(Constants.PARAM_DESCRIPCION) ? "" : json.getString(Constants.PARAM_DESCRIPCION));
		reglamento.setnCarreras(json.isNull(Constants.PARAM_N_CARRERAS) ? 0 : json.getLong(Constants.PARAM_N_CARRERAS));
		reglamento.setnClasificaciones(
				json.isNull(Constants.PARAM_N_CLASIFICACIONES) ? 0 : json.getLong(Constants.PARAM_N_CLASIFICACIONES));
		reglamento.setnEntrenamientos(
				json.isNull(Constants.PARAM_N_ENTRENAMIENTOS) ? 0 : json.getLong(Constants.PARAM_N_ENTRENAMIENTOS));
		reglamento.setnEquipos(json.isNull(Constants.PARAM_N_EQUIPOS) ? 0 : json.getLong(Constants.PARAM_N_EQUIPOS));
		reglamento.setnPilotos(json.isNull(Constants.PARAM_N_PILOTOS) ? 0 : json.getLong(Constants.PARAM_N_PILOTOS));
		return reglamento;
	}

	/**
	 * Conversor de JSONObject a Piloto
	 * 
	 * @param json
	 * @return
	 */
	public Piloto json2Piloto(JSONObject json) {
		Piloto piloto = new Piloto();
		piloto.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		piloto.setNombre(json.isNull(Constants.PARAM_NOMBRE) ? "null" : json.getString(Constants.PARAM_NOMBRE));
		piloto.setApellido(json.isNull(Constants.PARAM_APELLIDO) ? "null" : json.getString(Constants.PARAM_APELLIDO));
		piloto.setApodo(json.isNull(Constants.PARAM_APODO) ? "null" : json.getString(Constants.PARAM_APODO));
		return piloto;
	}

	/**
	 * Conversor de JSONObject a Equipo
	 * 
	 * @param jsonObject
	 * @return
	 */
	public Equipo json2Equipo(JSONObject json) {
		Equipo equipo = new Equipo();
		equipo.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		equipo.setNombre(json.isNull(Constants.PARAM_NOMBRE) ? "null" : json.getString(Constants.PARAM_NOMBRE));
		equipo.setAlias(json.isNull(Constants.PARAM_ALIAS) ? "null" : json.getString(Constants.PARAM_ALIAS));
		return equipo;
	}

	/**
	 * Conversor de JSONObject a Puntuacion
	 * 
	 * @param jsonObject
	 * @return
	 */
	public Puntuacion json2Puntuacion(JSONObject json) {
		Puntuacion puntuacion = new Puntuacion();
		puntuacion.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		puntuacion.setPosicion(json.isNull(Constants.PARAM_POSICION) ? 0 : json.getInt(Constants.PARAM_POSICION));
		puntuacion.setPuntos(json.isNull(Constants.PARAM_PUNTOS) ? 0 : json.getInt(Constants.PARAM_PUNTOS));
		puntuacion.setTipoSesion(json.isNull(Constants.PARAM_TIPO_SESION) ? new TipoSesion()
				: json2TipoSesion(json.getJSONObject(Constants.PARAM_TIPO_SESION)));
		return puntuacion;
	}

	/**
	 * Conversor de JSONObject a TipoSesion
	 * 
	 * @param jsonObject
	 * @return
	 */
	public TipoSesion json2TipoSesion(JSONObject json) {
		TipoSesion tSesion = new TipoSesion();
		tSesion.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		tSesion.setDescripcion(
				json.isNull(Constants.PARAM_DESCRIPCION) ? "" : json.getString(Constants.PARAM_DESCRIPCION));
		return tSesion;
	}

	/**
	 * Conversor de JSONObject a Gp
	 * 
	 * @param jsonObject
	 * @return
	 */
	public GranPremio json2Gp(JSONObject json) {
		GranPremio gp = new GranPremio();
		JSONObject jsonGp;
		String fecha = null;
		if (json.isNull(Constants.PARAM_GP)) {
			return gp;
		} else {
			jsonGp = json.getJSONObject(Constants.PARAM_GP);
		}
		gp.setId(jsonGp.isNull(Constants.PARAM_ID) ? 0 : jsonGp.getLong(Constants.PARAM_ID));
		gp.setUbicacion(jsonGp.isNull(Constants.PARAM_UBICACION) ? "N/A" : jsonGp.getString(Constants.PARAM_UBICACION));
		JSONArray array = json.isNull(Constants.PARAM_SESIONES) ? new JSONArray()
				: json.getJSONArray(Constants.PARAM_SESIONES);
		List<Sesion> listaSesiones = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			listaSesiones.add(json2Sesion(array.getJSONObject(i)));
			fecha = listaSesiones.get(i).getFecha();
		}
		gp.setFecha(fecha);
		gp.setSesiones(listaSesiones);
		return gp;
	}

	/**
	 * Conversor de JSONObject a Sesion
	 * 
	 * @param json
	 * @return
	 */
	public Sesion json2Sesion(JSONObject json) {
		Sesion sesion = new Sesion();
		sesion.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		sesion.setFecha(json.isNull(Constants.PARAM_FECHA) ? "N/A" : json.getString(Constants.PARAM_FECHA));
		sesion.setTipoSesion(json.isNull(Constants.PARAM_FECHA) ? new TipoSesion()
				: json2TipoSesion(json.getJSONObject(Constants.PARAM_TIPO_SESION)));
		return sesion;
	}

	/**
	 * Conversor de JSONObject a Inscripcion
	 * 
	 * @param jsonObject
	 * @return
	 */
	public Inscripcion json2Inscripcion(JSONObject json) {
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		inscripcion.setCampeonato(json.isNull(Constants.PARAM_CAMPEONATO) ? new Campeonato()
				: json2Campeonato(json.getJSONObject(Constants.PARAM_CAMPEONATO)));
		inscripcion.setPiloto(json.isNull(Constants.PARAM_PILOTO) ? new Piloto()
				: json2Piloto(json.getJSONObject(Constants.PARAM_PILOTO)));
		inscripcion.setEquipo(json.isNull(Constants.PARAM_EQUIPO) ? new Equipo()
				: json2Equipo(json.getJSONObject(Constants.PARAM_EQUIPO)));
		return inscripcion;
	}

	public Resultado json2Resultado(JSONObject json) {
		Resultado resultado = new Resultado();
		resultado.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		resultado.setPiloto(json.isNull(Constants.PARAM_PILOTO) ? new Piloto()
				: json2Piloto(json.getJSONObject(Constants.PARAM_PILOTO)));
		resultado.setSesion(json.isNull(Constants.PARAM_SESION) ? new Sesion()
				: json2Sesion(json.getJSONObject(Constants.PARAM_SESION)));
		resultado.setTiempo(json.isNull(Constants.PARAM_TIEMPO) ? 0 : json.getInt(Constants.PARAM_TIEMPO));
		resultado.setVueltas(json.isNull(Constants.PARAM_N_VUELTAS) ? 0 : json.getInt(Constants.PARAM_N_VUELTAS));
		resultado.setvRapida(json.isNull(Constants.PARAM_V_RAPIDA) ? 0 : json.getInt(Constants.PARAM_V_RAPIDA));
		return resultado;
	}

}
