package com.telecom.belong.exception;

import org.springframework.http.HttpStatus;

public class BelongAPIErrorResponse {
    private HttpStatus httpStatus;
    private String message;
    private String callerUri;

    public BelongAPIErrorResponse() {
    }

    public BelongAPIErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BelongAPIErrorResponse(HttpStatus httpStatus, String message, String callerUri) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.callerUri = callerUri;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCallerUri() {
        return callerUri;
    }

    public void setCallerUri(String callerUri) {
        this.callerUri = callerUri;
    }
}
