package com.msacademy.mx.covid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enfermedad")
public class Enfermedad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "neumonia", referencedColumnName = "id")
    private Especificacion neumonia;

    @OneToOne
    @JoinColumn(name = "diabetes", referencedColumnName = "id")
    private Especificacion diabetes;

    @OneToOne
    @JoinColumn(name = "epoc", referencedColumnName = "id")
    private Especificacion epoc;

    @OneToOne
    @JoinColumn(name = "hipertension", referencedColumnName = "id")
    private Especificacion hipertension;

    @OneToOne
    @JoinColumn(name = "asma", referencedColumnName = "id")
    private Especificacion asma;

    @OneToOne
    @JoinColumn(name = "inmunusuprimido", referencedColumnName = "id")
    private Especificacion inmunusuprimido;

    @OneToOne
    @JoinColumn(name = "cardiovascular", referencedColumnName = "id")
    private Especificacion cardiovascular;

    @OneToOne
    @JoinColumn(name = "obesidad", referencedColumnName = "id")
    private Especificacion obesidad;

    @OneToOne
    @JoinColumn(name = "renal_cronica", referencedColumnName = "id")
    private Especificacion renal_cronica;


    public Enfermedad(Especificacion neumonia, Especificacion diabetes, Especificacion epoc, Especificacion hipertension, Especificacion asma, Especificacion inmunusuprimido, Especificacion cardiovascular, Especificacion obesidad, Especificacion renal_cronica) {
        this.neumonia = neumonia;
        this.diabetes = diabetes;
        this.epoc = epoc;
        this.hipertension = hipertension;
        this.asma = asma;
        this.inmunusuprimido = inmunusuprimido;
        this.cardiovascular = cardiovascular;
        this.obesidad = obesidad;
        this.renal_cronica = renal_cronica;
    }
}
