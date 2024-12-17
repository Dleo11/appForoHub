package com.mdleo.API.foroHub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaRsta(
        Long id,
        String autor,
        String titulo,
        LocalDateTime fecha,
        String mensaje

) {
    public DatosRespuestaRsta(Respuesta respuesta){
        this(respuesta.getId(),
                respuesta.getAutor().getNombre(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFecha(),
                respuesta.getMensaje());
    }
}
