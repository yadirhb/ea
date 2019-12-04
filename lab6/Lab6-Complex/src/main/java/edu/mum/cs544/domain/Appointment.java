package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "APPOINTMENT")
public class Appointment {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "APPDATE", nullable = false)
    private LocalDate date;

    @Embedded
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "DOCTOR")
    private Doctor doctor;
}
