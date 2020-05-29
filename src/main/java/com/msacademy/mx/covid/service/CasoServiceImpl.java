package com.msacademy.mx.covid.service;

import com.msacademy.mx.covid.model.Caso;
import com.msacademy.mx.covid.model.dto.CasoDTO;
import com.msacademy.mx.covid.model.dto.ConfirmadosTiempoDTO;
import com.msacademy.mx.covid.model.dto.TipoCasoDTO;
import com.msacademy.mx.covid.repository.CasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CasoServiceImpl implements CasoService{


    @Autowired
    CasoRepository casoRepository;


    @Override
    public ResponseEntity<List<CasoDTO>> getResumen() {
        try{
            return new ResponseEntity(casoRepository.findResumenTodo(),HttpStatus.OK);
        }catch (Exception ex){
            HashMap<String, Long> hashMap  = new HashMap<>();
            hashMap.put("mujeres",0L);
            hashMap.put("hombres",0L);
            return new ResponseEntity(new CasoDTO("N/A",0L,0L,0L,0L,hashMap),HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public ResponseEntity<CasoDTO> getResumenEstado(String codigoEstado) {

        try{
            List<CasoDTO> casosPorEstado = casoRepository.findResumenTodo();
            List<CasoDTO> filtrado = casosPorEstado.stream()
                    .filter(caso ->  caso.getEstado().compareTo(codigoEstado) ==0)
                    .collect(Collectors.toList());

            if(filtrado.size() == 0){
                HashMap<String, Long> hashMap  = new HashMap<>();
                hashMap.put("mujeres",0L);
                hashMap.put("hombres",0L);
                return new ResponseEntity( new CasoDTO("N/A",0L,0L,0L,0L,hashMap),HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity( filtrado.get(0),HttpStatus.OK);
            }
        }catch (Exception ex){
            HashMap<String, Long> hashMap  = new HashMap<>();
            hashMap.put("mujeres",0L);
            hashMap.put("hombres",0L);
            return new ResponseEntity(new CasoDTO("N/A",0L,0L,0L,0L,hashMap),HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


    @Override
    public ResponseEntity<List<ConfirmadosTiempoDTO>> getLineaTiempoEstado(String codigoEstado, String tipo) {
        List<ConfirmadosTiempoDTO> lineaTiempoConfirmados;

        try{
            if(tipo.compareTo("confirmed") == 0){
                lineaTiempoConfirmados = casoRepository.findLineaTiempoConfirmados(codigoEstado);
                return new ResponseEntity(lineaTiempoConfirmados,HttpStatus.OK);
            }else if(tipo.compareTo("deaths") == 0){
                lineaTiempoConfirmados = casoRepository.findLineaTiempoMuertes(codigoEstado);
                return new ResponseEntity(lineaTiempoConfirmados,HttpStatus.OK);
            }else if(tipo.compareTo("suspects") == 0){
                lineaTiempoConfirmados = casoRepository.findLineaTiempoSospechosos(codigoEstado);
                return new ResponseEntity(lineaTiempoConfirmados,HttpStatus.OK);
            }else{
                lineaTiempoConfirmados = new ArrayList<>();
                return new ResponseEntity(lineaTiempoConfirmados,HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex) {
            lineaTiempoConfirmados = new ArrayList<>();
            lineaTiempoConfirmados.add(new ConfirmadosTiempoDTO(LocalDate.now(), 0L));
            return new ResponseEntity(lineaTiempoConfirmados, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public ResponseEntity<TipoCasoDTO> getStatusEstadoEdad(String codigoEstado, int edadInicio, int edadFinal) {
        try{
            List<TipoCasoDTO> tipoCaso = casoRepository.findByTipoPacienteAndAge(edadInicio,edadFinal);
            List<TipoCasoDTO> statusEstado =  tipoCaso.stream()
                    .filter(caso ->  caso.getEstado().compareTo(codigoEstado) ==0)
                    .collect(Collectors.toList());

            if(statusEstado.size() == 0){
                return new ResponseEntity(new TipoCasoDTO("N/A",0L,0L,0L),HttpStatus.OK);
            }else{
                return new ResponseEntity(statusEstado.get(0),HttpStatus.OK);
            }
        }catch (Exception ex){
            return new ResponseEntity(new TipoCasoDTO("N/A",0L,0L,0L),HttpStatus.SERVICE_UNAVAILABLE);
        }


    }

    @Override
    public ResponseEntity<TipoCasoDTO> getStatusEstado(String codigoEstado){

        try{
            List<TipoCasoDTO> tipoCaso = casoRepository.findByTipoPaciente();

            List<TipoCasoDTO> statusEstado = tipoCaso.stream()
                    .filter(caso ->  caso.getEstado().compareTo(codigoEstado) ==0)
                    .collect(Collectors.toList());

            if(statusEstado.size() == 0){
                return new ResponseEntity(new TipoCasoDTO("N/A",0L,0L,0L),HttpStatus.OK);
            }else{
                return new ResponseEntity(statusEstado.get(0),HttpStatus.OK);
            }
        }catch (Exception ex){
            return new ResponseEntity(new TipoCasoDTO("N/A",0L,0L,0L),HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
