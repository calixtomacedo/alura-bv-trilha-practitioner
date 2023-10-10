package com.med.voll.domain.records;

import jakarta.validation.constraints.NotNull;

public record AtualizaPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
