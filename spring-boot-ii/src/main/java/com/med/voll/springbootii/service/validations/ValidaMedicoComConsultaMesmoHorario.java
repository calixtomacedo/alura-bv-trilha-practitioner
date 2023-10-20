package com.med.voll.springbootii.service.validations;

import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import com.med.voll.springbootii.repository.ConsultaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoComConsultaMesmoHorario implements ValidaAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(AgendamentoConsulta agendamento) {

        var isMedicoOcupado = repository.existsByMedicoIdAndDataAgendamento(agendamento.idMedico(), agendamento.dataAgendamento());

        if (isMedicoOcupado) {
            throw new ValidationException("Esse médico já possui uma outra consulta agendada para esse mesmo horário");
        }
    }
}
