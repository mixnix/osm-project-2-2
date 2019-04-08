package com.osmproject2.repository;

import com.osmproject2.entity.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalizationRepository extends JpaRepository<Hospitalization, Integer> {
    List<Hospitalization> findByPatientId(Integer patientId);
    Optional<Hospitalization> findByIdAndPatientId(Integer id, Integer patientId);
}
