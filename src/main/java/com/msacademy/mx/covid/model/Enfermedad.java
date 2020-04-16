package com.msacademy.mx.covid.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enfermedad")
public class Enfermedad {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "neumonia")
    private Especificacion neumonia;

    @Column(name = "diabetes")
    private Especificacion diabetes;

    @Column(name = "epoc")
    private Especificacion epoc;

    @Column(name = "hipertension")
    private Especificacion hipertension;

    @Column(name = "asma")
    private Especificacion asma;

    @Column(name = "inmunusuprimido")
    private Especificacion inmunusuprimido;

    @Column(name = "cardiovascular")
    private Especificacion cardiovascular;

    @Column(name = "obesidad")
    private Especificacion obesidad;

    @Column(name = "renal_cronica")
    private Especificacion renal_cronica;
}
