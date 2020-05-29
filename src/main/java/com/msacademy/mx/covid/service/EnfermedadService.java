package com.msacademy.mx.covid.service;

import com.msacademy.mx.covid.model.dto.EnfermedadesDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnfermedadService {

    ResponseEntity<List<EnfermedadesDTO>> getEnfermedades();

    ResponseEntity<EnfermedadesDTO> getEnfermedadesEstado(String codigoEstado);
}
