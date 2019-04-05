package com.osmproject2.controller;


import com.osmproject2.entity.Procedure;
import com.osmproject2.repository.PatientRepository;
import com.osmproject2.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcedureController {

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient/{patientId}/procedure")
    public List<Procedure> getAllProceduresByPatientId(@PathVariable (value = "patientId") Integer patientId){
        return procedureRepository.findByPatientId(patientId);
    }

//    @PostMapping("/patients/{patientId}/procedures")
//    public Procedure createProcedure(@PathVariable (value = "patientId") Integer patientId,
//                                 @Valid @RequestBody Procedure procedure) {
//        return patientRepository.findById(patientId).map(patient -> {
//            procedure.setPatient(patient);
//            return procedureRepository.save(procedure);
//        }).orElseThrow(() -> new ResourceNotFoundException("PatientId " + patientId + " not found"));
//    }
}
