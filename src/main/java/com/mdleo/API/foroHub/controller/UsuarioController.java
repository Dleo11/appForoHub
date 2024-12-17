package com.mdleo.API.foroHub.controller;

import com.mdleo.API.foroHub.domain.curso.DatosRegistroCurso;
import com.mdleo.API.foroHub.domain.usuario.*;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsario dRegistroUsario, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario= usuarioRepository.save(new Usuario(dRegistroUsario));
        DatosRespuestaUsuario datosRespuestaUsuario= new DatosRespuestaUsuario(usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfil().toString());
        URI url = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(@PageableDefault(size = 5)Pageable pageable) {
        return ResponseEntity.ok(usuarioRepository.findAll(pageable).map(DatosListadoUsuario::new));
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity retornarUsuarios(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario(usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfil().toString());
        return ResponseEntity.ok(datosUsuario);
    }
}