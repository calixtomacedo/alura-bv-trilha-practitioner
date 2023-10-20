package com.med.voll.springbootii.service.validations;

import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import com.med.voll.springbootii.repository.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteAtivo implements ValidaAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(AgendamentoConsulta agendamento) {
        var isPacienteAtivo = repository.findFlativoById(agendamento.idPaciente());
        if(!isPacienteAtivo){
            throw new ValidationException("A consulta não pode ser agendada com paciênte inativo");
        }
    }

}
