package com.osmproject2.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="patient_procedure")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date")
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name="procedure_type")
    private String procedure_type;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProcedure_type() {
        return procedure_type;
    }

    public void setProcedure_type(String procedure_type) {
        this.procedure_type = procedure_type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
