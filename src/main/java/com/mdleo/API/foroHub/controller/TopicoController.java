package com.mdleo.API.foroHub.controller;

import com.mdleo.API.foroHub.domain.curso.Curso;
import com.mdleo.API.foroHub.domain.curso.CursoRepository;
import com.mdleo.API.foroHub.domain.respuesta.Respuesta;
import com.mdleo.API.foroHub.domain.respuesta.RespuestaRepository;
import com.mdleo.API.foroHub.domain.topico.*;
import com.mdleo.API.foroHub.domain.usuario.Usuario;
import com.mdleo.API.foroHub.domain.usuario.UsuarioRepository;
import com.mdleo.API.foroHub.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico dRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        if (topicoRepository.existsByTituloAndMensaje(dRegistroTopico.titulo(), dRegistroTopico.mensaje())) {
            return ResponseEntity.badRequest().body(null);
        }
        Usuario autor = usuarioRepository.findById(dRegistroTopico.usuarioId()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(dRegistroTopico.cursoId()).orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));
        Topico topico = new Topico(dRegistroTopico, autor, curso);
        topico = topicoRepository.save(topico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoTopicos>> listarTopico(@PageableDefault(size = 10) Pageable pageable){
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
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado"));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado"));
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico dActualizarTopico) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado"));
        topico.actualizarTopico(dActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @PutMapping("/respuesta-solucionar/{id}/{respuestaId}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> marcarSolucionado(@PathVariable Long id, @PathVariable Long respuestaId) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado"));
        Respuesta respuesta = respuestaRepository.findById(respuestaId).orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada"));
        if (topico.getStatus() == Estado.SOLUCIONADO) {
            return ResponseEntity.badRequest().body(null);
        }
        respuesta.setSolucion(true);
        topico.setStatus(Estado.SOLUCIONADO);
        topicoRepository.save(topico);
        respuestaRepository.save(respuesta);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

}