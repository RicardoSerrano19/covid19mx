package com.msacademy.mx.covid.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "caso")
public class Caso {

    @Id
    private Integer id;

    @Column(name = "sector")
    private Sector sector;

    @Column(name = "entidad_unidad_medica")
    private EntidadFederativa entidadUnidadMedica;

    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "entidad_nacimiento")
    private EntidadFederativa entidadNacimiento;

    @Column(name = "entidad_residencia")
    private EntidadFederativa entidadResidencia;

    @Column(name = "tipo_paciente")
    private TipoPaciente tipoPaciente;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_inicio_sintomas")
    private LocalDate fechaInicioSintomas;

    @Column(name = "fecha_defuncion")
    private LocalDate fechaDefuncion;

    @Column(name = "intubado")
    private Especificacion intubado;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "nacionalidad")
    private Nacionalidad nacionalidad;

    @Column(name = "lengua_indigena")
    private Especificacion lenguaIndigena;

    @Column(name = "enfermedad")
    private Enfermedad enfermedad;

    @Column(name = "embarazo")
    private Especificacion embarazo;

    @Column(name = "resultado")
    private Resultado resultado;

    @Column(name = "cuidados_intensivos")
    private Especificacion embarazo;

}
