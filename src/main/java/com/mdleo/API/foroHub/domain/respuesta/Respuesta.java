package com.mdleo.API.foroHub.domain.respuesta;

import com.mdleo.API.foroHub.domain.topico.Topico;
import com.mdleo.API.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    private Boolean solucion;

    // methods
    public Respuesta(@Valid DatosRegistroRespuesta datosRegistroRespuesta, Usuario autor, Topico topico){
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.fecha = datosRegistroRespuesta.fecha();
        this.solucion = false;
        this.autor = autor;
        this.topico = topico;
    }
}
