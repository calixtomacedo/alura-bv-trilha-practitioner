package com.med.voll.springbootii.repository;

import com.med.voll.springbootii.domain.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByFlativoTrue(Pageable pageable);
}
