package com.med.voll.springbootii.service;

import com.med.voll.springbootii.domain.Consulta;
import com.med.voll.springbootii.domain.Medico;
import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import com.med.voll.springbootii.domain.records.DetalheConsulta;
import com.med.voll.springbootii.repository.ConsultaRepository;
import com.med.voll.springbootii.repository.MedicoRepository;
import com.med.voll.springbootii.repository.PacienteRepository;
import com.med.voll.springbootii.service.validations.ValidaAgendamentoConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidaAgendamentoConsulta> validaAgendamentoList;

    public DetalheConsulta createSchedule(AgendamentoConsulta agendamento){
        if(agendamento.idMedico() != null && !medicoRepository.existsById(agendamento.idMedico())){
            throw new ValidationException("Id do médico informado não existe!");
        }
        if(!pacienteRepository.existsById(agendamento.idPaciente())){
            throw new ValidationException("Id do paciente informado não existe!");
        }

        validaAgendamentoList.forEach(v -> v.validar(agendamento));

        var medico = chooseDoctor(agendamento);
        if(medico == null){
            throw new ValidationException("Não existe nenhum médico disponível nessa data!");
        }
        var paciente = pacienteRepository.getReferenceById(agendamento.idPaciente());
        var consulta = new Consulta(null, medico, paciente, agendamento.dataAgendamento());
        consultaRepository.save(consulta);
        return new DetalheConsulta(consulta);
    }

    private Medico chooseDoctor(AgendamentoConsulta agendamento) {
        if(agendamento.idMedico() != null){
            var medico = medicoRepository.getReferenceById(agendamento.idMedico());
            System.out.println(medico.getNome());
            return medico;
        }

        if(agendamento.especialidade() == null){
            throw new ValidationException("A especialidade deve ser informada quando o médico não for escolhido!");
        }

        return medicoRepository.chooseFreeRandomDoctorOnDate(agendamento.especialidade(), agendamento.dataAgendamento());
    }

}
