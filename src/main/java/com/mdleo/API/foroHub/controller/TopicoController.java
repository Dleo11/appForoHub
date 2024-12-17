package com.mdleo.API.foroHub.controller;

import com.mdleo.API.foroHub.domain.curso.Curso;
import com.mdleo.API.foroHub.domain.curso.CursoRepository;
import com.mdleo.API.foroHub.domain.topico.*;
import com.mdleo.API.foroHub.domain.usuario.Usuario;
import com.mdleo.API.foroHub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico dRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        if (topicoRepository.existsByTituloAndMensaje(dRegistroTopico.titulo(), dRegistroTopico.mensaje())) {
            return ResponseEntity.badRequest().body(null);
        }
        Usuario autor = usuarioRepository.findById(dRegistroTopico.usuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(dRegistroTopico.cursoId()).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Topico topico = topicoRepository.save(new Topico(dRegistroTopico, autor, curso));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoTopicos>> listarTopico(@PageableDefault(size = 10,sort="fecha", direction = Sort.Direction.ASC
    ) Pageable pageable){
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(pageable).map(DatosListadoTopicos::new));
    }

    @GetMapping("/listar/cursos")
    public ResponseEntity<Page<DatosListadoTopicos>> listadoCursoTopico(@PageableDefault(size = 10) Pageable pageable) {
        Page<Topico> topico = topicoRepository.findByCursoAsc(pageable);
        Page<DatosListadoTopicos> datosListadoTopicos = topico.map(DatosListadoTopicos::new);
        return ResponseEntity.ok(datosListadoTopicos);
    }

    @GetMapping("/listar/fecha")
    public ResponseEntity<Page<DatosListadoTopicos>> listadoFechaTopico(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicoRepository.findAllOrderByFecha(pageable).map(DatosListadoTopicos::new));
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
            return ResponseEntity.ok(datosRespuestaTopico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        if (topico.getId() != null) {
            topico.desactivarTopico();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico dActualizarTopico) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.actualizarTopico(dActualizarTopico);
            return ResponseEntity.ok(topico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
