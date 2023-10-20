package com.med.voll.springbootii.repository;

import com.med.voll.springbootii.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByMedicoIdAndDataAgendamento(Long id, LocalDateTime date);

    Boolean existsByPacienteIdAndDataAgendamentoBetween(Long id, LocalDateTime firstTime, LocalDateTime lastTime);
}
