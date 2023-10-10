package com.med.voll.springbootii.domain.records;

import jakarta.validation.constraints.NotNull;

public record AtualizaMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
