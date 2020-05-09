package com.races.portal.component;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpMethod;

import kong.unirest.GetRequest;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.RequestBodyEntity;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Unirest.class })
public class UtilsTest {

	@Mock
	Unirest unirest;

	@Mock
	GetRequest getRequest;

	@Mock
	HttpRequestWithBody requestWithBody;

	@Mock
	RequestBodyEntity requestBodyEntity;

	@Mock
	HttpServletRequest req;

	HttpResponse<String> mockedResponse;

	@InjectMocks
	Utils utils;

	private String testUrl = "http://localhost:8080/test";

	private Map<String, Object> testQS = new HashMap<>();
	private Map<String, Object> testBody = new HashMap<>();
	private Map<String, String> testHeaders = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Before
	public void init() throws UnirestException {

		MockitoAnnotations.initMocks(this);

		PowerMockito.mockStatic(Unirest.class);
		mockedResponse = PowerMockito.mock(HttpResponse.class);

		PowerMockito.when(Unirest.get(Mockito.anyString())).thenReturn(getRequest);
		PowerMockito.when(Unirest.post(Mockito.anyString())).thenReturn(requestWithBody);
		PowerMockito.when(Unirest.patch(Mockito.anyString())).thenReturn(requestWithBody);
		PowerMockito.when(Unirest.delete(Mockito.anyString())).thenReturn(requestWithBody);
		PowerMockito.when(Unirest.put(Mockito.anyString())).thenReturn(requestWithBody);
		PowerMockito.when(Unirest.head(Mockito.anyString())).thenReturn(getRequest);
		PowerMockito.when(Unirest.delete(Mockito.anyString())).thenReturn(requestWithBody);
		PowerMockito.when(requestWithBody.headers(Mockito.anyMap())).thenReturn(requestWithBody);
		PowerMockito.when(requestWithBody.queryString(Mockito.anyMap())).thenReturn(requestWithBody);
		PowerMockito.when(requestWithBody.body(Mockito.any(JSONObject.class))).thenReturn(requestBodyEntity);
		PowerMockito.when(requestBodyEntity.asString()).thenReturn(mockedResponse);
		PowerMockito.when(getRequest.headers(Mockito.any())).thenReturn(getRequest);
		PowerMockito.when(getRequest.queryString(Mockito.any())).thenReturn(getRequest);
		PowerMockito.when(getRequest.asString()).thenReturn(mockedResponse);

	}

	@Test
	public void executeHttpMethodGetTest() {

		try {
			HttpResponse<String> response = utils.executeHttpMethod(testUrl, testQS, testBody, testHeaders,
					HttpMethod.GET);
			assertNotNull(response);
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void executeHttpMethodPutTest() {

		try {
			HttpResponse<String> response = utils.executeHttpMethod(testUrl, null, null, null, HttpMethod.PUT);
			assertNotNull(response);
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void executeHttpMethodPostTest() {

		try {
			HttpResponse<String> response = utils.executeHttpMethod(testUrl, testQS, testBody, testHeaders,
					HttpMethod.POST);
			assertNotNull(response);
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void executeHttpMethodDeleteTest() {

		try {
			HttpResponse<String> response = utils.executeHttpMethod(testUrl, null, null, null, HttpMethod.DELETE);
			assertNotNull(response);
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void executeHttpMethodDefaultTest() {

		try {
			utils.executeHttpMethod(testUrl, testQS, testBody, testHeaders, HttpMethod.OPTIONS);
			fail("Exception Expected");
		} catch (UnirestException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void executeHttpMethodHeadTest() {

		try {
			HttpResponse<String> response = utils.executeHttpMethod(testUrl, testQS, testBody, testHeaders,
					HttpMethod.HEAD);
			assertNotNull(response);
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void executeHttpMethodPatchTest() {

		try {
			HttpResponse<String> response = utils.executeHttpMethod(testUrl, testQS, testBody, testHeaders,
					HttpMethod.PATCH);
			assertNotNull(response);
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

}
