package com.devs.locadora.carros.controllers;

import com.devs.locadora.carros.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken dadosLogin =
                new UsernamePasswordAuthenticationToken(loginDTO.getCpf(), loginDTO.getSenha());

        authenticationManager.authenticate(dadosLogin);

        return ResponseEntity.ok("Login realizado com sucesso");
    }
}
