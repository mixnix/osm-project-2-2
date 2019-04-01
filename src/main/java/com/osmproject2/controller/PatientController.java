package com.osmproject2.controller;

import com.osmproject2.entity.Patient;
import com.osmproject2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    // need to inject patient service
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/list")
    public String listPatients(Model theModel){

        // get patients from the dao
        List<Patient> thePatients = patientRepository.findAll();

        // add the patients to the model
        theModel.addAttribute("patients", thePatients);

        return "list-patients";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Patient thePatient = new Patient();

        theModel.addAttribute("patient", thePatient);

        return "patient-form";
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("patient") Patient thePatient){

        patientRepository.save(thePatient);

        return "redirect:/patient/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("patientId") int theId,
                                    Model theModel){
        // get the patient from the database
        Patient thePatient = patientRepository.getOne(theId);

        // set patient as a model attribute to pre-populate the form
        theModel.addAttribute("patient", thePatient);

        // send over to our form
        return "patient-form";
    }

    @GetMapping("/delete")
    public String deletePatient(@RequestParam("patientId") int theId){

        patientRepository.deleteById(theId);

        return "redirect:/patient/list";
    }

}
