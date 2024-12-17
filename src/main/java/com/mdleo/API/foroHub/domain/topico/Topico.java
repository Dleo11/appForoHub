package com.mdleo.API.foroHub.domain.topico;

import com.mdleo.API.foroHub.domain.curso.Curso;
import com.mdleo.API.foroHub.domain.respuesta.Respuesta;
import com.mdleo.API.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private Estado status;
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    // methods
    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario autor, Curso curso){
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = datosRegistroTopico.fecha();
        this.status = datosRegistroTopico.status();
        this.activo = true;
        this.autor = autor;
        this.curso = curso;
    }

    public void desactivarTopico(){
        this.activo = false;
    }

    public void actualizarTopico(DatosActualizarTopico dActualizarTopico) {
        if (dActualizarTopico.mensaje() != null){
            this.mensaje = dActualizarTopico.mensaje();
        }
        if (dActualizarTopico.fecha() != null) {
            this.fecha = dActualizarTopico.fecha();
        }
        if (dActualizarTopico.status() != null) {
            this.status = dActualizarTopico.status();
        }
    }

}
