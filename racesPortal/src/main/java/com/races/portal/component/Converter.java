package com.races.portal.component;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Campeonato;
import com.races.portal.dto.Reglamento;

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
		campeonato.setId(json.isNull(Constants.PARAM_ID) ? "0" : "" + json.getLong(Constants.PARAM_ID));
		campeonato.setNombre(json.isNull(Constants.PARAM_NOMBRE) ? "null" : json.getString(Constants.PARAM_NOMBRE));
		campeonato.setTemporada(
				json.isNull(Constants.PARAM_TEMPORADA) ? "null" : json.getString(Constants.PARAM_TEMPORADA));
		campeonato.setReglamento(json.isNull(Constants.PARAM_REGLAMENTO) ? "#0"
				: "#" + json.getJSONObject(Constants.PARAM_REGLAMENTO).getLong(Constants.PARAM_ID));
		campeonato.setDescripcion(
				json.isNull(Constants.PARAM_DESCRIPCION) ? "" : json.getString(Constants.PARAM_DESCRIPCION));
		return campeonato;
	}

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

}
