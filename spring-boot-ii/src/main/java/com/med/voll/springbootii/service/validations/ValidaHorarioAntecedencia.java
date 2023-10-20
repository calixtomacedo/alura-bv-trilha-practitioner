package com.med.voll.springbootii.service.validations;

import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidaHorarioAntecedencia implements ValidaAgendamentoConsulta {

    public void validar(AgendamentoConsulta agendamento) {

        var dataConsulta = agendamento.dataAgendamento();
        var dataNow = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataNow, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidationException("A consulta deve ser agendada com antecedência minima de 30 minutos");
        }

    }
}
