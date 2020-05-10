package com.races.exception;

/**
 * Clase de Excepcion personalizada para el proyecto.
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public class RacesException extends Exception {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto de Exception
	 */
	public RacesException() {
		super();
	}

	/**
	 * Constructor con mensaje personalizado
	 * 
	 * @param arg0
	 */
	public RacesException(String msg) {
		super(msg);
	}

	/**
	 * Constructor con causa personalizada
	 * 
	 * @param cause
	 */
	public RacesException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor con mensaje y causa personalizados
	 * 
	 * @param message
	 * @param cause
	 */
	public RacesException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor completo de Exception
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RacesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
