package com.races.services;

import org.jose4j.lang.JoseException;

public interface JwtService {

	/**
	 * Generates a signed JWT using a previously generated JWT for sign and validation
	 * 
	 * @param kid
	 * @param jwk
	 * @return
	 * @throws JoseException
	 */
	String generateJwt(String kid, String jwk) throws JoseException;


	/**
	 * JWT bare validation.
	 * 
	 * @param jwt
	 * @param jwk
	 * @return boolean
	 * @throws JoseException
	 */
	boolean validateJWT(String jwt, String jwk) throws JoseException;


	String getJWK(String jwk) throws JoseException;


	String encodeBase64(String data);


	String encryptData(String data) throws JoseException;


	String decodeBase64(String data);


	String decodeData(String jwk) throws JoseException;

}
