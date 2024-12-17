package com.mdleo.API.foroHub.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        @NotBlank
        String mensaje
) {
    public DatosActualizarTopico(Topico topico){
        this(topico.getId(),
                topico.getMensaje());
    }
}
