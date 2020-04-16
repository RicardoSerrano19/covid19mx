package com.msacademy.mx.covid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enfermedad")
public class Enfermedad {

    @Id
    @Column(name = "id")
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
}
