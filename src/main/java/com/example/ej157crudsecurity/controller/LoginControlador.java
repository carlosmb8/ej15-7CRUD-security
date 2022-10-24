package com.example.ej157crudsecurity.controller;

import com.example.ej157crudsecurity.application.PersonaService;
import com.example.ej157crudsecurity.entity.Persona;
import com.example.ej157crudsecurity.security.entity.AuthenticationReq;
import com.example.ej157crudsecurity.security.entity.TokenInfo;
import com.example.ej157crudsecurity.security.service.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @RequestMapping("/login")
    public ResponseEntity<TokenInfo> usuarioLogueado(@RequestBody AuthenticationReq authenticationReq){

        String usuario = authenticationReq.getUsuario();
        String password = authenticationReq.getClave();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario, password));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(usuario);
        
        final String jwt = jwtUtilService.generateToken(userDetails);

        TokenInfo tokenInfo = new TokenInfo(jwt);

        return ResponseEntity.ok(tokenInfo);

    }

}
