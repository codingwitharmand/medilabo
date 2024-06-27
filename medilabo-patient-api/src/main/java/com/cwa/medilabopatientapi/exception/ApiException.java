package com.cwa.medilabopatientapi.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
