package br.com.books.api.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -7666374778764618599L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}
}
