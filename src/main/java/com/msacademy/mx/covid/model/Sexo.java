package com.msacademy.mx.covid.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sexo")
public class Sexo {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;
}
