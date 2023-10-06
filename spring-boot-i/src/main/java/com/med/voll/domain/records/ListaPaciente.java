package com.med.voll.domain.records;

import com.med.voll.domain.Paciente;

public record ListaPaciente(Long id, String nome, String email, String cpf) {
    public ListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
