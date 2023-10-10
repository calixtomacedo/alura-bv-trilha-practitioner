package com.med.voll.springbootii.domain.records;


import com.med.voll.springbootii.domain.Endereco;
import com.med.voll.springbootii.domain.Medico;
import com.med.voll.springbootii.domain.enuns.Especialidade;

public record DetalheMedico(Long id, String nome, String email, String telefone, String crm, Boolean flativo, Especialidade especialidade, Endereco endereco) {

    public DetalheMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getFlativo(), medico.getEspecialidade(), medico.getEndereco());
    }
}
