package com.mdleo.API.foroHub.exception;

import lombok.Getter;
import lombok.Setter;

@Getter

public class ErrorDetails {
    private int statusCode;
    private String message;
    private String details;

    public ErrorDetails(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }
}
