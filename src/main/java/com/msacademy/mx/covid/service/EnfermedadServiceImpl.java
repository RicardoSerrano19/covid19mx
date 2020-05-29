package com.msacademy.mx.covid.service;

import com.msacademy.mx.covid.model.dto.EnfermedadesDTO;
import com.msacademy.mx.covid.repository.EnfermedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfermedadServiceImpl implements EnfermedadService{

    @Autowired
    EnfermedadRepository enfermedadRepository;


    @Override
    public ResponseEntity<List<EnfermedadesDTO>> getEnfermedades() {
        try{
            return new ResponseEntity(enfermedadRepository.findByEnfermedad(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(new EnfermedadesDTO(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public ResponseEntity<EnfermedadesDTO> getEnfermedadesEstado(String codigoEstado) {
        try{
            return new ResponseEntity(enfermedadRepository.findByEnfermedadAndState(codigoEstado),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(new EnfermedadesDTO(),HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
