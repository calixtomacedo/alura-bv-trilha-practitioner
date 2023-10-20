package com.med.voll.springbootii.controller;


import com.med.voll.springbootii.domain.Medico;
import com.med.voll.springbootii.domain.records.AtualizaMedico;
import com.med.voll.springbootii.domain.records.CadastroMedico;
import com.med.voll.springbootii.domain.records.DetalheMedico;
import com.med.voll.springbootii.domain.records.ListaMedico;
import com.med.voll.springbootii.repository.MedicoRepository;
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
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid CadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalheMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ListaMedico>> list(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        var response = repository.findAllByFlativoTrue(pageable).map(ListaMedico::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> find(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalheMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid AtualizaMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.update(dados);
        return ResponseEntity.ok(new DetalheMedico(medico));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.delete();
        return ResponseEntity.noContent().build();
    }


}
