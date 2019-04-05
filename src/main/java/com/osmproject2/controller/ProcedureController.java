package com.osmproject2.controller;


import com.osmproject2.entity.Patient;
import com.osmproject2.entity.Procedure;
import com.osmproject2.repository.PatientRepository;
import com.osmproject2.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProcedureController {

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient/{patientId}/procedure")
    public String listProcedures(@PathVariable (value = "patientId") Integer patientId,
                                                       Model model){
        List<Procedure> procedures = procedureRepository.findByPatientId(patientId);

        model.addAttribute("procedures", procedures);

        return "list-procedures";
    }


    @GetMapping("/patient/{patientId}/showFormForAddProcedure")
    public String showFormForAdd(@PathVariable (value = "patientId") Integer patientId,
                                 Model theModel){

        Patient patient = patientRepository.getOne(patientId);

        Procedure procedure = new Procedure();
        procedure.setPatient(patient);

        theModel.addAttribute("procedure", procedure);
        theModel.addAttribute("patientId", patientId);

        return "procedure-form";
    }

    @PostMapping("/patient/{patientId}/saveProcedure")
    public String saveProcedure(@PathVariable (value = "patientId") Integer patientId,
                                @ModelAttribute("procedure") Procedure procedure){

        procedure.setPatient(patientRepository.getOne(patientId));
        procedureRepository.save(procedure);

        // todo: niech redirectuje do listy procedure dla danego pacjenta ale to potem zrobie
        return "redirect:/patient/" + patientId + "/procedure";
    }

    @GetMapping("/patient/{patientId}/showFormForUpdate")
    public String showFormForUpdate(@PathVariable (value = "patientId") Integer patientId,
                                    @RequestParam("procedureId") int theId,
                                    Model theModel){
        // get the patient from the database
        Procedure procedure = procedureRepository.getOne(theId);

        // set patient as a model attribute to pre-populate the form
        theModel.addAttribute("procedure", procedure);

        // send over to our form
        return "procedure-form";
    }

    @GetMapping("/patient/{patientId}/delete")
    public String deletePatient(@PathVariable (value = "patientId") Integer patientId,
                                @RequestParam("procedureId") int theId){

        procedureRepository.deleteById(theId);

        return "redirect:/patient/" + patientId + "/procedure";
    }


}
