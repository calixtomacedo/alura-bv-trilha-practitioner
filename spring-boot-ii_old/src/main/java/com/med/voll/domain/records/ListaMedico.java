package com.med.voll.domain.records;

import com.med.voll.domain.Medico;
import com.med.voll.domain.enuns.Especialidade;

public record ListaMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public ListaMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
