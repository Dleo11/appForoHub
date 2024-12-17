package com.mdleo.API.foroHub.domain.topico;

import com.mdleo.API.foroHub.domain.respuesta.DatosRespuestasTopico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String curso,
        String autor,
        LocalDateTime fecha,
        String mensaje,
        List<DatosRespuestasTopico> respuestas,
        Estado status
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getCurso().getNombre(),
                topico.getAutor().getNombre(),
                topico.getFecha(),
                topico.getMensaje(),
                topico.getRespuestas().stream().map(DatosRespuestasTopico::new).collect(Collectors.toList()),
                Estado.valueOf(topico.getStatus().toString())
        );
    }
}
