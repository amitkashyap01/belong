package com.telecom.belong.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(final String message){
        super(message);
    }
}
