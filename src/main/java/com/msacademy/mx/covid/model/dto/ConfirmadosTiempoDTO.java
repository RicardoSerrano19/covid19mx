package com.msacademy.mx.covid.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmadosTiempoDTO implements Serializable {
    private LocalDate fecha;
    private Long casos;
}
