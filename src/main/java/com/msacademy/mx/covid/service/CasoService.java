package com.msacademy.mx.covid.service;

import com.msacademy.mx.covid.model.dto.CasoDTO;
import com.msacademy.mx.covid.model.dto.ConfirmadosTiempoDTO;
import com.msacademy.mx.covid.model.dto.TipoCasoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CasoService {

    ResponseEntity<List<CasoDTO>>  getResumen();

    ResponseEntity<CasoDTO> getResumenEstado(String codigoEstado);

    ResponseEntity<List<ConfirmadosTiempoDTO>> getLineaTiempoEstado(String codigoEstado, String tipo);

    ResponseEntity<TipoCasoDTO> getStatusEstado(String codigoEstado);

    ResponseEntity<TipoCasoDTO> getStatusEstadoEdad(String codigoEstado, int edadInicio, int edadFinal);

}
