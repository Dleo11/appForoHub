package com.mdleo.API.foroHub.domain.usuario;

public record DatosAutenticacionUser(
        String email,
        String clave
) {
    public DatosAutenticacionUser(Usuario usuario){
        this(usuario.getEmail(), usuario.getClave());
    }
}
