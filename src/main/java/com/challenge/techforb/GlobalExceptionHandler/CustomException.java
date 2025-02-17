package com.challenge.techforb.GlobalExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


public class CustomException extends RuntimeException{
    private final String message;
    private final int statusCode;

    public CustomException(String message, int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
