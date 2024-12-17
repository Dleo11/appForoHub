package com.mdleo.API.foroHub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotNull
        Perfil perfil,
        @NotNull
        @NotBlank
        String clave
) {
}