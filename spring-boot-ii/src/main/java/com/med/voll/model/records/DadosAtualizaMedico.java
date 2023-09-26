package com.med.voll.model.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
