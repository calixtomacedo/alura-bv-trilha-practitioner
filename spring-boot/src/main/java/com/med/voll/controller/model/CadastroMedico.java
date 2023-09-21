package com.med.voll.controller.model;

import com.med.voll.controller.model.enuns.Especialidade;

public record CadastroMedico(String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
}
