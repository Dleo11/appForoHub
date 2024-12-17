package com.mdleo.API.foroHub.domain.topico;

import com.mdleo.API.foroHub.domain.curso.Curso;
import com.mdleo.API.foroHub.domain.respuesta.Respuesta;
import com.mdleo.API.foroHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fecha,
        @NotNull
        Estado status,
        @NotNull
        Long cursoId,
        @NotNull
        Long usuarioId

) {
}
