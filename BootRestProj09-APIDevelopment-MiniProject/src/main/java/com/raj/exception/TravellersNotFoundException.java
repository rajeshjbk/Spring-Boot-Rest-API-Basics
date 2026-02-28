package com.raj.exception;

public class TravellersNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TravellersNotFoundException() {

		super();
	}

	public TravellersNotFoundException(String msg) {

		super(msg);
	}
}
