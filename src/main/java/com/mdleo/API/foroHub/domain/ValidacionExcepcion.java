package com.mdleo.API.foroHub.domain;

public class ValidacionExcepcion extends RuntimeException {
    public ValidacionExcepcion(String mensaje) {
        super(mensaje);
    }
}