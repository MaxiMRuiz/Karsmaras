package com.races.portal.component;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Campeonato;
import com.races.portal.dto.Equipo;
import com.races.portal.dto.Piloto;
import com.races.portal.dto.Puntuacion;
import com.races.portal.dto.Reglamento;
import com.races.portal.dto.TipoSesion;

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
				: json2Sesion(json.getJSONObject(Constants.PARAM_TIPO_SESION)));
		return puntuacion;
	}

	public TipoSesion json2Sesion(JSONObject json) {
		TipoSesion tSesion = new TipoSesion();
		tSesion.setId(json.isNull(Constants.PARAM_ID) ? 0 : json.getLong(Constants.PARAM_ID));
		tSesion.setDescripcion(
				json.isNull(Constants.PARAM_DESCRIPCION) ? "" : json.getString(Constants.PARAM_DESCRIPCION));
		return tSesion;
	}

}
