package com.med.voll.springbootii.controller;


import com.med.voll.springbootii.domain.Paciente;
import com.med.voll.springbootii.domain.records.AtualizaPaciente;
import com.med.voll.springbootii.domain.records.CadastroPaciente;
import com.med.voll.springbootii.domain.records.DetalhePaciente;
import com.med.voll.springbootii.domain.records.ListaPaciente;
import com.med.voll.springbootii.repository.PacienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid CadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhePaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListaPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByFlativoTrue(paginacao).map(ListaPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid AtualizaPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.update(dados);

        return ResponseEntity.ok(new DetalhePaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhePaciente(paciente));
    }

}
