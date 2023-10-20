package com.med.voll.springbootii.domain.records;

import com.med.voll.springbootii.domain.enuns.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime dataAgendamento,

        Especialidade especialidade) {
}
