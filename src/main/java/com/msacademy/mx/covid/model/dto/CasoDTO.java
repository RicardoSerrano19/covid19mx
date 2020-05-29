package com.msacademy.mx.covid.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CasoDTO implements Serializable{

    private String estado;
    private Long confirmados;
    private Long sospechosos;
    private Long negativos;
    private Long muertes;
    private Map<String,Long> sexoConfirmados = new HashMap<>();


    public CasoDTO(String estado, Long confirmados, Long sospechosos, Long negativos, Long muertes, Long mujeres,Long hombres) {
        this.estado = estado;
        this.confirmados = confirmados;
        this.sospechosos = sospechosos;
        this.negativos = negativos;
        this.muertes = muertes;
        this.sexoConfirmados.put("mujeres",mujeres);
        this.sexoConfirmados.put("hombres",hombres);
    }
}
