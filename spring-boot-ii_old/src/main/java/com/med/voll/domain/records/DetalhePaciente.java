package com.med.voll.domain.records;

import com.med.voll.domain.Endereco;
import com.med.voll.domain.Paciente;

public record DetalhePaciente(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DetalhePaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }

}
