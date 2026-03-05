package com.example.weatherapiconsuming.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorJsonApiResponseException extends RuntimeException {
    public ErrorJsonApiResponseException(String message) {
        super(message);
    }
}
