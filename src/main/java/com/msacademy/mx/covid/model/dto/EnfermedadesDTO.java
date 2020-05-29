package com.msacademy.mx.covid.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class EnfermedadesDTO implements Serializable {
    private String estado;
    private Long total;
    private Map<String,Long> tipo = new HashMap<>();


    public EnfermedadesDTO(String estado, Long totalMuertes, Long neumonia, Long diabetes, Long epoc, Long hipertension, Long asma, Long inmunusuprimido, Long cardiovascular, Long obesidad, Long renalCronica) {
        this.estado = estado;
        this.total = totalMuertes;
        this.tipo.put("neumonia",neumonia);
        this.tipo.put("diabetes",diabetes);
        this.tipo.put("epoc",epoc);
        this.tipo.put("hipertension",hipertension);
        this.tipo.put("inmunusuprimido",inmunusuprimido);
        this.tipo.put("asma",asma);
        this.tipo.put("cardiovascular",cardiovascular);
        this.tipo.put("obesidad",obesidad);
        this.tipo.put("renalCronica",renalCronica);
    }
}
