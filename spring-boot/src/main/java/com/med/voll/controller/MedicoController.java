package com.med.voll.controller;

import com.med.voll.model.Medico;
import com.med.voll.model.records.DadosAtualizaMedico;
import com.med.voll.model.records.DadosCadastroMedico;
import com.med.voll.model.records.DadosListagemMedico;
import com.med.voll.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void save(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> list(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        //repository.findAll(pageable).stream().map(DadosListagemMedico::new).toList();
        //return repository.findAll(pageable).map(DadosListagemMedico::new);
        return repository.findAllByFlativoTrue(pageable).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DadosAtualizaMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.update(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        //Exclusão física
        //repository.deleteById(id);

        //Exclusão lógica
        var medico = repository.getReferenceById(id);
        medico.delete();
    }


}
