package com.example.aerospikeexample.exception;

//this is a generic Exception
public class ApiRequestException extends RuntimeException{
    public ApiRequestException(String message) {
        super(message);
    }
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
