package com.msacademy.mx.covid.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Data
@NoArgsConstructor
public class TipoCasoDTO implements Serializable {

    private String estado;
    private Long ambulatorio;
    private Map<String,Long> hospitalizados = new HashMap<>();

    public TipoCasoDTO(String estado,Long ambulatorios, Long estables,Long intubados){
        this.estado = estado;
        this.ambulatorio = ambulatorios;
        this.hospitalizados.put("estable",estables);
        this.hospitalizados.put("intubado",intubados);

    }

}
