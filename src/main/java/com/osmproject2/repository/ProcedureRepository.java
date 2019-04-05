package com.osmproject2.repository;


import com.osmproject2.entity.Procedure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
    List<Procedure> findByPatientId(Integer patientId);
    Optional<Procedure> findByIdAndPatientId(Integer id, Integer patientId);
}
