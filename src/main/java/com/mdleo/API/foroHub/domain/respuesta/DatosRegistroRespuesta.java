package com.mdleo.API.foroHub.domain.respuesta;

import com.mdleo.API.foroHub.domain.topico.Topico;
import com.mdleo.API.foroHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fecha,
        @NotNull
        Long topicoId,
        @NotNull
        Long usuarioId
) {
}