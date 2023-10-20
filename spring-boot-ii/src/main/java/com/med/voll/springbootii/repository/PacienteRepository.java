package com.med.voll.springbootii.repository;

import com.med.voll.springbootii.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByFlativoTrue(Pageable paginacao);

    @Query("SELECT p.flativo FROM Paciente p WHERE p.id = :id")
    Boolean findFlativoById(Long id);
}
