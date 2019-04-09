package com.osmproject2.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;




@Entity
@Table(name="patient_hospitalization")
@Getter
@Setter
public class Hospitalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="start_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name="finish_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @Column(name="hospitalization_cause")
    private String hospitalizationCause;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;
}