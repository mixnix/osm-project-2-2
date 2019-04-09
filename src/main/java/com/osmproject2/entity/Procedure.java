package com.osmproject2.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="patient_procedure")
@Getter
@Setter
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name="procedure_type")
    private String procedure_type;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;
}
