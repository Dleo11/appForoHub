package com.mdleo.API.foroHub.controller;

import com.mdleo.API.foroHub.domain.respuesta.DatosListadoRespuesta;
import com.mdleo.API.foroHub.domain.respuesta.DatosRegistroRespuesta;
import com.mdleo.API.foroHub.domain.respuesta.DatosRespuestaRsta;
import com.mdleo.API.foroHub.domain.respuesta.Respuesta;
import com.mdleo.API.foroHub.domain.respuesta.RespuestaRepository;
import com.mdleo.API.foroHub.domain.topico.Topico;
import com.mdleo.API.foroHub.domain.topico.TopicoRepository;
import com.mdleo.API.foroHub.domain.usuario.Usuario;
import com.mdleo.API.foroHub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaRsta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta dRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        Usuario autor = usuarioRepository.findById(dRegistroRespuesta.usuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Topico topico = topicoRepository.findById(dRegistroRespuesta.topicoId()).orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        Respuesta respuesta = respuestaRepository.save(new Respuesta(dRegistroRespuesta, autor, topico));
        DatosRespuestaRsta datosRespuestaRsta = new DatosRespuestaRsta(respuesta);
        URI url = uriComponentsBuilder.path("respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRsta);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestas(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(respuestaRepository.findAll(pageable).map(DatosListadoRespuesta::new));
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<DatosRespuestaRsta> retornarRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        DatosRespuestaRsta datosRespuestaRsta = new DatosRespuestaRsta(respuesta);
        return ResponseEntity.ok(datosRespuestaRsta);
    }
}
