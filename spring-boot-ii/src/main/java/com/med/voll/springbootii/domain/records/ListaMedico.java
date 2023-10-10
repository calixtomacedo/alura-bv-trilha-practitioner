package com.med.voll.springbootii.domain.records;


import com.med.voll.springbootii.domain.Medico;
import com.med.voll.springbootii.domain.enuns.Especialidade;

public record ListaMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public ListaMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
