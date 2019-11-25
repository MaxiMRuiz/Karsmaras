package com.races.portal.component;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class Utils {

	private static final Log LOGGER = LogFactory.getLog(Utils.class);

	/**
	 * Método para enviar una peticion rest.
	 * 
	 * @param baseURI
	 * @param queryString
	 * @param body
	 * @param headers
	 * @param method
	 * @param isAsync
	 * @return HttpResponse - String
	 * @throws Exception
	 */
	public HttpResponse<String> executeHttpMethod(final String baseURI, final Map<String, Object> queryString,
			final Map<String, Object> body, final Map<String, String> headers, final HttpMethod method)
			throws UnirestException {

		Map<String, String> newHeaders = null;
		Map<String, Object> newBody = null;
		Map<String, Object> newQueryString = null;

		try {
			if (headers == null) {
				newHeaders = new HashMap<>();
			} else {
				newHeaders = new HashMap<>(headers);
			}
			if (body == null) {
				newBody = new HashMap<>();
			} else {
				newBody = new HashMap<>(body);
			}
			if (queryString == null) {
				newQueryString = new HashMap<>();
			} else {
				newQueryString = new HashMap<>(queryString);
			}
			return processHttpResponse(baseURI, newQueryString, newBody, newHeaders, method);
		} catch (UnirestException ue) {
			LOGGER.error(ue);
			throw new UnirestException("Server error: " + ue.getMessage());
		}
	}

	/**
	 * Processes the HttpResponse
	 * 
	 * @param baseURI
	 * @param newQueryString
	 * @param newBody
	 * @param newHeaders
	 * @param method
	 * @return
	 * @throws UnirestException
	 */
	private HttpResponse<String> processHttpResponse(String baseURI, Map<String, Object> newQueryString,
			Map<String, Object> newBody, Map<String, String> newHeaders, HttpMethod method) throws UnirestException {
		HttpResponse<String> result = null;
		switch (method) {
		case GET:
			result = Unirest.get(baseURI).headers(newHeaders).queryString(newQueryString).asString();
			break;
		case PUT:
			result = Unirest.put(baseURI).headers(newHeaders).queryString(newQueryString).body(new JSONObject(newBody))
					.asString();
			break;
		case POST:
			result = Unirest.post(baseURI).headers(newHeaders).queryString(newQueryString).body(new JSONObject(newBody))
					.asString();
			break;
		case DELETE:
			result = Unirest.delete(baseURI).headers(newHeaders).queryString(newQueryString)
					.body(new JSONObject(newBody)).asString();
			break;
		case HEAD:
			result = Unirest.head(baseURI).headers(newHeaders).queryString(newQueryString).asString();
			break;
		case PATCH:
			result = Unirest.patch(baseURI).headers(newHeaders).queryString(newQueryString)
					.body(new JSONObject(newBody)).asString();
			break;
		default:
			throw new UnirestException("Server doesn't support http method: " + method);
		}
		return result;
	}

}
