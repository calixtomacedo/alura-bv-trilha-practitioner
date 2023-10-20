package com.med.voll.springbootii.service.validations;

import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import com.med.voll.springbootii.repository.ConsultaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteSemConsultaNoDia implements ValidaAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(AgendamentoConsulta agendamento) {
        var firstTime = agendamento.dataAgendamento().withHour(7);
        var lastTime = agendamento.dataAgendamento().withHour(18);

        var isConsultaAgendada = repository.existsByPacienteIdAndDataAgendamentoBetween(agendamento.idPaciente(), firstTime, lastTime);

        if(isConsultaAgendada){
            throw new ValidationException("O paciente já possui uma consulta agendada para essa data");
        }
    }
}
