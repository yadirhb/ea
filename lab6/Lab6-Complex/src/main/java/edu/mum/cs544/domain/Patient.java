package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@SecondaryTables(
        @SecondaryTable(name = "ADDRESS", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "PATIENT_ID", referencedColumnName = "id")
        }))
@Table(name = "PATIENT")
public class Patient {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(table = "ADDRESS", name = "STREET", nullable = false)
    private String street;
    @Column(table = "ADDRESS", name = "ZIP", nullable = false)
    private String zip;
    @Column(table = "ADDRESS", name = "CITY", nullable = false)
    private String city;
}
