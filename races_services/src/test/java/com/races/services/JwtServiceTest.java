package com.races.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jose4j.lang.JoseException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.races.services.impl.JwtServiceImpl;

public class JwtServiceTest {

	private static final String TEST = "test";

	private static final String TEST2 = "test2";

	@InjectMocks
	JwtServiceImpl service;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(service, "publicKey",
				"ewogICAgInAiOiAiX2ZqZjQwZ3dGbjlIYmR4S0FKWmp0QXMtWDlUdVE3d0VYSmF6dlQtTjFIRFZiWFo0Vmt0aFhSTXEtX1pMaHROTDVCcjlBSkJVLXJFdUU3clZGa3hjcDM1TGtPUXVORENJZVpJb2pzWEJtLWJDVnpUNW42ZTBxc2NNc0tVYWNURzF6WlFfSDRlYlk1djZaVnViYThOR0U0Z20xXzYyek5hQk0yVHVaWTJpekJFIiwKICAgICJrdHkiOiAiUlNBIiwKICAgICJxIjogInN3YWZvS0NIdVpJS0xsc1pNdmhoWkx1VHpXcVZZVVNlUmJfdF9lTVRLdDZFVjJwUUNKQXJiQTZoVzVwYkJTSXhZUkJZMFRFbUU4aWk2MFJ1d29wOXdjc3BWQXFIdkxhYmREcWF4cnh5SmtrZVBtSHRCY2NnSW4wcGRxM2VTaG44ajJBdXc1V0R0elcyNFhnMTJyRDFwYl90Njd2YWlDRjloVUZYQ25zQkhlTSIsCiAgICAiZCI6ICJTaEM2UWpnQ3NyU1VsMl9jamVuTGpDRTZjNDNZU2FkdURYOEhuTnlTc2lveWEtUVpIeDBnWDRfcW5oUFFkczZzUVdTYThnSXhlSFlaQm56Rk5tSUJsU2duM0MzR21DakdEVnpOVmlJTEw0bmlYMkc5RXFTay1iWmdSMnRiZGo3Zm51bzgtblI1dTZCOXM0UkdwX3hZaFRaUlc5bmpZTHNEY0x4YWI4eEFPa1VhcjNBTFBPZHlyZ0lwVGdlY213aDVmYm82RFJOVlRvVV8yNUdIeEdPMGJWZTBmX1prZ2xiclNLWVhwVy02QzZocUwxZ21MQVltdFlSeDBheWtrakVOQWV4dnFGSjVEVG1tM3JPOEhXZXZQUDRZaWl5STZmWHR2ODI2cU02b0hJVW54ZmoyZ2xocndfV1haNVBaVUl3VmJGQkJrRllNQnFGNkRPTUpqZXpFUVEiLAogICAgImUiOiAiQVFBQiIsCiAgICAidXNlIjogImVuYyIsCiAgICAia2lkIjogInJhY2VzIiwKICAgICJxaSI6ICI5ZDFoVlZZdDltRk1qTzh3UWE4RGlGcjEyRnJVU0pnT1VwVXRXSDIzQWw1WHF2SVZEMjhaMGEtNE5HbzhsaUkzaEkyQzY3S0thMFNkNHgtYTlCT2NId1lJbDhVTmE5ZHBVa3QyYWN4RzR2NEU4TFl4Z204dzIxOWp2VXpBQ2dNbXpoM2pScmFQLWc3aTZZMTg4QTA1V2FoTTBhVzhOUko1blUzS2RCb3k4bkEiLAogICAgImRwIjogInp0OVZSMlk2dldpSnYyUXI1MVNZX0VRWVY2a3pOM3RFTmtNUFAzMHdRelp4c3d0SU5FcU1rV3NXblV4ZTh3RFVGWWNpUkw1TWFqTy1xNVdKRXZPNm1UaFh4enNHbzc1UXZHNGdtVU5lVkdaZ01QYld5b1NwdGw0UzZTUmphSWFzWnp0MU8zS1RVVXp4Wm5SN0lnbGE0SGdMNC03MzZkeGtPM19nYkZvMW5IRSIsCiAgICAiYWxnIjogIlJTMjU2IiwKICAgICJkcSI6ICJoREQzM1p1cm5KTUJnWWt5NmpnTmNsd3ZueFo4aHY0c0FFVGdid0FsUHhwZmpreXFIQ2lZWjRuR0MtWEdIOXhIcWV4d0tNZXAzaUhnYXVoMUFLRlNpcTZCTGRVNHRWZ0JJYW5FYjhybVh1N09yakdRMnh0VjF5R2taRVd1UmZrNlRJUXhBNnA3UC1VaHpRci1lU1VCdmdvaW5GWngzdU1iMURHVEctSUh5UkUiLAogICAgIm4iOiAic1p1V3ZoaXBtd1Jid2w2UnNUQjZnRGtEVXNBMWt4UHk3cTVpNmhvSlBpWWwzTHFRQlFFWnVteTNETDRfcnNMbVVQbnp2Q2Y1TktnLXc2eTBzejFRNTM1Vk5KQnRka3lhOHpzLVVXbUE0czNCTFpTeUtmRllzS2ZNMzM0ODVvWmdyeVNNaklUQU5qTVUwRVA4dmlRQzctV0l3bjBfalM3YTllYURsc2NSVUtzLU00S3dDVmNmYTNTMkZ2eDlKcDU0NGtoSzRQVkFSTGhHZ3hUd1ZzeFJaTFZxRFhQdzI4VDNJMzlGN3BPeUNoVVJfRnBfa2tZb20wNEtEU0hzREtkVnZOOGNzU0lmdkN2UkdrbzU5aW5wRmNzWGtZUUEwbTNXU3pIR01ZR2NXN0JPR192Tk9kbHo2LW5fU3hJeVpNV3BiN05UOHRkSnZCUTctbXB3XzRuZ0V3Igp9");
	}

	@Test
	public void validateJwtTest() {
		try {
			String jwk = service.getJWK(TEST);
			String jwt = service.generateJwt(TEST, jwk);
			assertTrue(service.validateJWT(jwt, jwk));
		} catch (JoseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void validateJwtFailTest() {
		try {
			String jwk = service.getJWK(TEST);
			String jwk2 = service.getJWK(TEST2);
			String jwt = service.generateJwt(TEST, jwk);
			assertFalse(service.validateJWT(jwt, jwk2));
		} catch (JoseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void encodeBase64Test() {
		String encode = service.encodeBase64(TEST);
		String decode = service.decodeBase64(encode);
		assertTrue(decode.equals(TEST));
	}

	@Test
	public void encryptDataTest() {
		try {
			assertNotNull(service.encryptData(TEST));
		} catch (JoseException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void decodeDataTest() {
		try {
			String data = service.encryptData(TEST);
			assertNotNull(service.decodeData(data));
		} catch (JoseException e) {
			fail(e.getMessage());
		}
	}

}
