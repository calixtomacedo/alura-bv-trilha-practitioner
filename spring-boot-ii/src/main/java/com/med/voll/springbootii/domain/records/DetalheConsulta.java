package com.med.voll.springbootii.domain.records;

import com.med.voll.springbootii.domain.Consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DetalheConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime dataAgendamento) {

    public DetalheConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getDataAgendamento());
    }
}
