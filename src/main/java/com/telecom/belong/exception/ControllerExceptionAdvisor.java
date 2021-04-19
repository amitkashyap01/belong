package com.telecom.belong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(final Exception exception, final HttpServletRequest httpServletRequest){
        final String message = "An exception occurred while serving your request. Please contact Belong support team.";
        final BelongAPIErrorResponse belongAPIErrorResponse = new BelongAPIErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, httpServletRequest.getRequestURI());

        return prepareResponseEntity(belongAPIErrorResponse);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(final NoDataFoundException exception, final HttpServletRequest httpServletRequest){
        return prepareResponseEntity(new BelongAPIErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), httpServletRequest.getRequestURI()));
    }


    private ResponseEntity<Object> prepareResponseEntity(BelongAPIErrorResponse errorResponse){
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
