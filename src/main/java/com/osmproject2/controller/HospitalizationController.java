package com.osmproject2.controller;

import com.osmproject2.entity.Hospitalization;
import com.osmproject2.entity.Patient;
import com.osmproject2.entity.Procedure;
import com.osmproject2.repository.HospitalizationRepository;
import com.osmproject2.repository.PatientRepository;
import com.osmproject2.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HospitalizationController {

    @Autowired
    private HospitalizationRepository hospitalizationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient/{patientId}/hospitalization")
    public String listHospitalizations(@PathVariable(value = "patientId") Integer patientId,
                                       Model model){
        List<Hospitalization> hospitalizations = hospitalizationRepository.findByPatientId(patientId);

        model.addAttribute("hospitalizations", hospitalizations);

        return "list-hospitalizations";
    }

    @GetMapping("/patient/{patientId}/showFormforAddHospitalization")
    public String showFormForAdd(@PathVariable (value = "patientId") Integer patientId,
                                 Model theModel){
        Patient patient = patientRepository.getOne(patientId);

        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setPatient(patient);

        theModel.addAttribute("hospitalization", hospitalization);
        theModel.addAttribute("patientId", patientId);

        return "hospitalization-form";
    }

    @PostMapping("/patient/{patientId}/saveHospitalization")
    public String saveHospitalization(@PathVariable (value = "patientId") Integer patientId,
                                @ModelAttribute("hospitalization") Hospitalization hospitalization){

        hospitalization.setPatient(patientRepository.getOne(patientId));
        hospitalizationRepository.save(hospitalization);

        return "redirect:/patient/" + patientId + "/hospitalization";
    }

    @GetMapping("/patient/{patientId}/showFormForHospitalizationUpdate")
    public String showFormForUpdate(@PathVariable (value = "patientId") Integer patientId,
                                    @RequestParam("hospitalizationId") int theId,
                                    Model theModel){
        // get the patient from the database
        Hospitalization hospitalization = hospitalizationRepository.getOne(theId);

        // set patient as a model attribute to pre-populate the form
        theModel.addAttribute("hospitalization", hospitalization);

        // send over to our form
        return "hospitalization-form";
    }

    @GetMapping("/patient/{patientId}/deleteHospitalization")
    public String deleteHospitalization(@PathVariable (value = "patientId") Integer patientId,
                                @RequestParam("hospitalizationId") int theId){

        hospitalizationRepository.deleteById(theId);

        return "redirect:/patient/" + patientId + "/hospitalization";
    }

}
