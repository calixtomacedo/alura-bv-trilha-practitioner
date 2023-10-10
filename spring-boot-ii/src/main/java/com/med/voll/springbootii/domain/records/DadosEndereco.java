package com.med.voll.springbootii.domain.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,

        String numero,

        @NotBlank
        String bairro,

        @NotBlank @Pattern(regexp = "\\d{5}-\\d{3}")
        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        String uf,

        String complemento) {
}
