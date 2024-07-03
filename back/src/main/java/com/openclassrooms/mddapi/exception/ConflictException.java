package com.openclassrooms.mddapi.exception;


public class ConflictException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String field; 
    public ConflictException(String message, String field) {
        super(message);
        this.field = field;
    }
    public String getField() {
        return field;
    }
}