package com.med.voll.springbootii.service.validations;

import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidaHorarioFuncionamentoClinica implements ValidaAgendamentoConsulta {

    public void validar(AgendamentoConsulta agendamento) {

        var dataConsulta = agendamento.dataAgendamento();
        var isSunday = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isAfterOpening = dataConsulta.getHour() < 7;
        var isBeforeOpening = dataConsulta.getHour() > 18;

        if(isSunday || isAfterOpening || isBeforeOpening){
            throw new ValidationException("Consulta fora do dia, ou horário de funcionamento da clínica");
        }

    }
}
