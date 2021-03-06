package com.msacademy.mx.covid.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "caso")
public class Caso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "sector", referencedColumnName = "id")
    private Sector sector;

    @OneToOne
    @JoinColumn(name = "entidad_unidad_medica", referencedColumnName = "id")
    private EntidadFederativa entidadUnidadMedica;

    @OneToOne
    @JoinColumn(name = "sexo", referencedColumnName = "id")
    private Sexo sexo;

    @OneToOne
    @JoinColumn(name = "entidad_nacimiento", referencedColumnName = "id")
    private EntidadFederativa entidadNacimiento;

    @OneToOne
    @JoinColumn(name = "entidad_residencia", referencedColumnName = "id")
    private EntidadFederativa entidadResidencia;

    @OneToOne
    @JoinColumn(name = "tipo_paciente", referencedColumnName = "id")
    private TipoPaciente tipoPaciente;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_inicio_sintomas")
    private LocalDate fechaInicioSintomas;

    @Column(name = "fecha_defuncion")
    private LocalDate fechaDefuncion;

    @OneToOne
    @JoinColumn(name = "intubado", referencedColumnName = "id")
    private Especificacion intubado;

    @Column(name = "edad")
    private Integer edad;

    @OneToOne
    @JoinColumn(name = "nacionalidad", referencedColumnName = "id")
    private Nacionalidad nacionalidad;

    @OneToOne
    @JoinColumn(name = "lengua_indigena", referencedColumnName = "id")
    private Especificacion lenguaIndigena;

    @OneToOne
    @JoinColumn(name = "enfermedad", referencedColumnName = "id")
    private Enfermedad enfermedad;

    @OneToOne
    @JoinColumn(name = "embarazo", referencedColumnName = "id")
    private Especificacion embarazo;

    @OneToOne
    @JoinColumn(name = "resultado", referencedColumnName = "id")
    private Resultado resultado;

    @OneToOne
    @JoinColumn(name = "cuidados_intensivos", referencedColumnName = "id")
    private Especificacion cuidadosIntensivos;

    public Caso(Sector sector, EntidadFederativa entidadUnidadMedica, Sexo sexo, EntidadFederativa entidadNacimiento, EntidadFederativa entidadResidencia, TipoPaciente tipoPaciente, LocalDate fechaIngreso, LocalDate fechaInicioSintomas, LocalDate fechaDefuncion, Especificacion intubado, Integer edad, Nacionalidad nacionalidad, Especificacion lenguaIndigena, Enfermedad enfermedad, Especificacion embarazo, Resultado resultado, Especificacion cuidadosIntensivos) {
        this.sector = sector;
        this.entidadUnidadMedica = entidadUnidadMedica;
        this.sexo = sexo;
        this.entidadNacimiento = entidadNacimiento;
        this.entidadResidencia = entidadResidencia;
        this.tipoPaciente = tipoPaciente;
        this.fechaIngreso = fechaIngreso;
        this.fechaInicioSintomas = fechaInicioSintomas;
        this.fechaDefuncion = fechaDefuncion;
        this.intubado = intubado;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.lenguaIndigena = lenguaIndigena;
        this.enfermedad = enfermedad;
        this.embarazo = embarazo;
        this.resultado = resultado;
        this.cuidadosIntensivos = cuidadosIntensivos;
    }


}
