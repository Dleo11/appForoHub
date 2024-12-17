package com.mdleo.API.foroHub.controller;

import com.mdleo.API.foroHub.domain.usuario.DatosAutenticacionUser;
import com.mdleo.API.foroHub.domain.usuario.Usuario;
import com.mdleo.API.foroHub.infra.security.DatosJWTtoken;
import com.mdleo.API.foroHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUser(@RequestBody @Valid DatosAutenticacionUser dAutenticacionUser) {
        try {
            System.out.println(dAutenticacionUser.email());
            System.out.println(dAutenticacionUser.clave());
            Authentication authToken = new UsernamePasswordAuthenticationToken(dAutenticacionUser.email(), dAutenticacionUser.clave());
            System.out.println(authToken);
            var userAutenticado = authenticationManager.authenticate(authToken);
            System.out.println(userAutenticado);

            var JWTtoken = tokenService.makeToken((Usuario) userAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

}


