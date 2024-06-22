package com.openclassrooms.mddapi.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.openclassrooms.mddapi.dto.responses.ErrorResponse;
import com.openclassrooms.mddapi.exception.AuthenticationException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.ConflictException;
import com.openclassrooms.mddapi.exception.CustomException;
import com.openclassrooms.mddapi.exception.JwtAuthenticationException;
import com.openclassrooms.mddapi.exception.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // Handle custom exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    // Handle custom exceptions
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    // Handle JWT Authentication exceptions
    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(JwtAuthenticationException ex, WebRequest request) {
    	logger.error("handle JwtAuthenticationException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse( "Bad JWT");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BadRequestException ex, WebRequest request) {
    	logger.error("handle BadRequestException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(  ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
    	logger.error("handle handleNotFoundException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(  ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationxception(AuthenticationException ex, HttpServletRequest request) {
    	logger.error("handle Authenticationxception: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse( "Invalid Credentials");
    	return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
