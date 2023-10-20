package com.med.voll.springbootii.repository;

import com.med.voll.springbootii.domain.Medico;
import com.med.voll.springbootii.domain.enuns.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByFlativoTrue(Pageable pageable);

    @Query("""
            SELECT m FROM Medico m 
            WHERE m.flativo = true
            AND m.especialidade = :especialidade
            AND m.id NOT IN(
                SELECT c.medico.id FROM Consulta c WHERE c.dataAgendamento = :dataAgendamento
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Medico chooseFreeRandomDoctorOnDate(Especialidade especialidade, LocalDateTime dataAgendamento);

    @Query("SELECT m.flativo FROM Medico m WHERE m.id = :id")
    Boolean findFlativoById(Long id);

}
