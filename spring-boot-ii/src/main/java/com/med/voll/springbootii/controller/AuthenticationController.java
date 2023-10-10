package com.med.voll.springbootii.controller;

import com.med.voll.springbootii.domain.records.DadosAutenticacao;
import com.med.voll.springbootii.domain.records.TokenJwtResponse;
import com.med.voll.springbootii.domain.security.User;
import com.med.voll.springbootii.infra.security.TokenJwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenJwtService service;

    @PostMapping
    public ResponseEntity<?> doLogin(@RequestBody @Valid DadosAutenticacao user){
        var usernamePasswordAuth = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        var authentication = manager.authenticate(usernamePasswordAuth);

        var tokenJwt = service.createTokenJwt((User) authentication.getPrincipal());
        var response = new TokenJwtResponse(tokenJwt);
        return ResponseEntity.ok(response);
    }

}
