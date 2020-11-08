package com.cg.go.greatoutdoor.exception;

public class OrderException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public OrderException(String message) {
		super(message);
	}
	
	public OrderException(){
		
	}
}
