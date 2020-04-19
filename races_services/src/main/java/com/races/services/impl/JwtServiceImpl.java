package com.races.services.impl;

import java.util.Base64;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKey.OutputControlLevel;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.races.constants.Constants;
import com.races.services.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

	private static final Log LOGGER = LogFactory.getLog(JwtServiceImpl.class);

	@Value("${races.sig.jwk}")
	private String publicKey;

	@Override
	public String generateJwt(String kid, String jwk) throws JoseException {
		RsaJsonWebKey parsedJwk = (RsaJsonWebKey) JsonWebKey.Factory.newJwk(jwk);

		return generateJwt(kid, parsedJwk);
	}

	/**
	 * 
	 * @param kid
	 * @param jwk
	 * @return
	 * @throws JoseException
	 */
	private String generateJwt(final String kid, final RsaJsonWebKey jwk) throws JoseException {

		// Initialize the result
		String jwt = null;
		// Create the claims using the kid
		JwtClaims claims = this.setJwtClaims(kid);

		// Signing the JWT with JWS
		JsonWebSignature jws = new JsonWebSignature();
		// Include the claims
		jws.setPayload(claims.toJson());
		// Sets the sign
		jws.setKey(jwk.getPrivateKey());
		// Include the type in the header -> typ: "JWT"
		jws.setHeader(Constants.JWT_KEY_TYPE, Constants.JWT_PARAM_KEY_TYPE);
		// Include the algorithm in the header -> alg: "RSA256"
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

		// Serializes the signed claims
		jwt = jws.getCompactSerialization();

		return jwt;
	}

	/**
	 * 
	 * @param kid
	 * @return
	 */
	private JwtClaims setJwtClaims(final String kid) {

		// Setting up the claims
		JwtClaims claims = new JwtClaims();
		// Set the time when the JWK is created -> iat:
		claims.setIssuedAtToNow();
		// Set the expiration time -> exp:
		claims.setExpirationTimeMinutesInTheFuture(Constants.PARAM_EXPIRATION_TIME);
		// Set the JWTid to prevent replaying -> jti:
		claims.setGeneratedJwtId();
		// Includes the kid in the body -> kid:
		claims.setClaim(Constants.JWT_CLAIMS_KEY_KID, kid);

		return claims;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbva.xmas.jwt.services.JwtService#validateClaims(java.lang.String,
	 * org.jose4j.jwk.RsaJsonWebKey)
	 */
	@Override
	public boolean validateJWT(final String jwt, final String jwk) throws JoseException {

		RsaJsonWebKey parsedJwk = (RsaJsonWebKey) JsonWebKey.Factory.newJwk(jwk);

		return this.validateJWT(jwt, parsedJwk);

	}

	/**
	 * 
	 * @param jwt
	 * @param jwk
	 * @return
	 */
	private boolean validateJWT(String jwt, RsaJsonWebKey jwk) {

		try {
			// Create a consumer
			JwtConsumer jwtConsumer = this.getConsumer(jwk);

			// Process the claims. If this process is successful continues
			jwtConsumer.processToClaims(jwt);
			// The JWT is valid
			return true;
		} catch (InvalidJwtException e) {
			LOGGER.error("JWT not valid", e);
			// If the exception is thrown the JWT is not valid
			return false;
		}

	}

	/**
	 * Obtains a JWT consumer capable of processing and validating a JWT
	 * 
	 * @param jwk
	 * @return JwtConsumer
	 */
	private JwtConsumer getConsumer(final RsaJsonWebKey jwk) {

		// Initialize the consumer builder
		JwtConsumerBuilder consumerBuilder = new JwtConsumerBuilder();

		// Set Expiration Time as mandatory (Otherwise JWT will be invalid)
		consumerBuilder.setRequireExpirationTime();
		// Set the time deviance for validation
		consumerBuilder.setAllowedClockSkewInSeconds(5);
		// Set the verification key from the JWK public key
		consumerBuilder.setVerificationKey(jwk.getKey());
		// Set the algorithm used for validation
		consumerBuilder.setJwsAlgorithmConstraints(
				new AlgorithmConstraints(ConstraintType.WHITELIST, AlgorithmIdentifiers.RSA_USING_SHA256));

		// Initialize and build the consumer
		return consumerBuilder.build();
	}

	@Override
	public String getJWK(String apodo) throws JoseException {
		RsaJsonWebKey jwk = this.getRraJwk(apodo);
		OutputControlLevel outputControlLevel = null;

		outputControlLevel = JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE;
		if (null != jwk) {
			return jwk.toJson(outputControlLevel);
		} else {
			return null;
		}
	}

	private RsaJsonWebKey getRraJwk(String apodo) throws JoseException {
		RsaJsonWebKey jwk = RsaJwkGenerator.generateJwk(Constants.RSA_GENERATION_BITS);

		if (null != jwk) {

			jwk.setKeyId(apodo);
			jwk.setAlgorithm(AlgorithmIdentifiers.RSA_USING_SHA256);
			jwk.setUse(Constants.PARAM_USE_SIG);
		}
		return jwk;
	}

	@Override
	public String encodeBase64(String data) {
		return Base64.getEncoder().encodeToString(data.getBytes());
	}

	@Override
	public String decodeBase64(final String data) {
		return new String(Base64.getDecoder().decode(data));
	}

	@Override
	public String encryptData(String data) throws JoseException {

		JsonWebKey jwk = JsonWebKey.Factory.newJwk(new String(Base64.getDecoder().decode(publicKey)));

		// Create a new Json Web Encryption object
		JsonWebEncryption jwe = new JsonWebEncryption();

		// Set the message in the JWE
		jwe.setPlaintext(data);
		// Set the algorithm in the header -> "alg"
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);

		// Set the content encryption header -> "enc"
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);

		jwe.setKey(jwk.getKey());

		return jwe.getCompactSerialization();
	}

	@Override
	public String decodeData(String jwk) throws JoseException {
		return this.decodeBase64(this.decryptData(jwk));
	}

	private String decryptData(String data) throws JoseException {
		// Initialize the result
		String result = null;

		RsaJsonWebKey jwk = (RsaJsonWebKey) JsonWebKey.Factory
				.newJwk(new String(Base64.getDecoder().decode(publicKey)));
		// Create a new Json Web Encryption object
		JsonWebEncryption jwe = new JsonWebEncryption();

		// Set the algorithm constraints
		AlgorithmConstraints algConstraints = new AlgorithmConstraints(ConstraintType.WHITELIST,
				KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
		jwe.setAlgorithmConstraints(algConstraints);
		AlgorithmConstraints encConstraints = new AlgorithmConstraints(ConstraintType.WHITELIST,
				ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);

		// Include the message in the JWE
		jwe.setCompactSerialization(data);

		// Set the constraints in the JWE
		jwe.setContentEncryptionAlgorithmConstraints(encConstraints);

		// Uses the private key to decrypt
		jwe.setKey(jwk.getPrivateKey());

		// Set the plain text as the result
		result = jwe.getPlaintextString();

		return result;
	}

}
