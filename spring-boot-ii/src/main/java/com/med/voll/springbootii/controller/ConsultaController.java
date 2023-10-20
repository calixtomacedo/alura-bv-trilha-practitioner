package com.med.voll.springbootii.controller;

import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import com.med.voll.springbootii.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody @Valid AgendamentoConsulta agendamento){
        var response = service.createSchedule(agendamento);
        return ResponseEntity.ok(response);
    }
}
