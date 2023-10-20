package com.med.voll.springbootii.service.validations;

import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import com.med.voll.springbootii.repository.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoAtivo implements ValidaAgendamentoConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(AgendamentoConsulta agendamento) {

        if(agendamento.idMedico() == null){
            return;
        }

        var isMedicoAtivo = repository.findFlativoById(agendamento.idMedico());
        if(!isMedicoAtivo){
            throw new ValidationException("A consulta não pode ser agendada com um médico inativo");
        }

    }
}
