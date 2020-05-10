package com.races.constants;

/**
 * Clase de constantes
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class Constants {

	/**
	 * Constructor
	 */
	private Constants() {
		throw new IllegalStateException("Constants class");
	}

	/**
	 * Constante OK
	 */
	public static final String STATUS_OK = "OK";

	/**
	 * Mensaje indicando que el reglamento no existe
	 */
	public static final String REGLAMENTO_NO_EXISTE = "El reglamento indicado no existe.";

	/**
	 * Mensaje indicando que el campeonato no existe
	 */
	public static final String CAMPEONATO_NO_EXISTE = "El campeonato indicado no existe.";

	public static final String FORMATO_INCORRECTO = "Formato Incorrecto";

	public static final String ENCONTRADAS = "Encontradas ";

	public static final Long ENTRENAMIENTO_ID = 1L;

	public static final String ENTRENAMIENTO = "Entrenamiento";

	public static final String CLASIFICACION = "Clasificacion";

	public static final String CARRERA = "Carrera";

	public static final Long CLASIFICACION_ID = 2L;

	public static final Long CARRERA_ID = 3L;

	public static final float PARAM_EXPIRATION_TIME = 60;

	public static final String JWT_CLAIMS_KEY_KID = "kid";

	public static final String JWT_KEY_TYPE = "typ";

	public static final String JWT_PARAM_KEY_TYPE = "JWT";

	public static final int RSA_GENERATION_BITS = 2048;

	public static final String PARAM_USE_SIG = "sig";

}
