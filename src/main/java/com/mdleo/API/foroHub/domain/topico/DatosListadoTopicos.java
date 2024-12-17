package com.mdleo.API.foroHub.domain.topico;

import com.mdleo.API.foroHub.domain.usuario.DatosRespuestaUsuario;

import java.time.LocalDateTime;

public record DatosListadoTopicos(
        Long id,
        String titulo,
        String curso,
        String autor,
        LocalDateTime fecha,
        Estado statu,
        Integer numRespuestas
) {
    public DatosListadoTopicos(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getCurso().getNombre(),
                topico.getAutor().getNombre(),
                topico.getFecha(),
                topico.getStatus(),
                topico.getRespuestas().size());
    }
}
