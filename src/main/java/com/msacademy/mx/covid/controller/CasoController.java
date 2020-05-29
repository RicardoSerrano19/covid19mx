package com.msacademy.mx.covid.controller;

import com.msacademy.mx.covid.model.dto.EnfermedadesDTO;
import com.msacademy.mx.covid.service.CasoServiceImpl;
import com.msacademy.mx.covid.service.DataBaseStorage;
import com.msacademy.mx.covid.service.DownloadControl;
import com.msacademy.mx.covid.service.EnfermedadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class CasoController {


    @Autowired
    DataBaseStorage dataBaseStorage;

    @Autowired
    DownloadControl downloadControl;

    @Autowired
    CasoServiceImpl casoService;

    @Autowired
    EnfermedadServiceImpl enfermedadService;


    @GetMapping(value = "/casos",produces = {"application/json"})
    public ResponseEntity  getAll(){
        return casoService.getResumen();
    }

    @GetMapping(value = "/casos/{state}",produces = {"application/json"})
    public ResponseEntity getByState(@PathVariable(name = "state") String state){
        return casoService.getResumenEstado(state);
    }

    @GetMapping(value = "/casos/{state}/timeline",produces = {"application/json"})
    public ResponseEntity getTimeline(@PathVariable(name = "state") String codigo, @RequestParam(name = "type") String tipo){
        return casoService.getLineaTiempoEstado(codigo,tipo);
    }

    @GetMapping(value = "/casos/{state}/status",produces = {"application/json"})
    public ResponseEntity getStatus(@PathVariable(name = "state") String codigo,@RequestParam(name = "start", required = false) Integer start, @RequestParam(name = "end",required = false) Integer end){
        if(start == null || end == null)
            return casoService.getStatusEstado(codigo);
        else
            return casoService.getStatusEstadoEdad(codigo,start,end);
    }

    @GetMapping(value = "/casos/{state}/disease",produces = {"application/json"})
    public ResponseEntity getDiseaseByState(@PathVariable(name = "state") String codigo){
        return enfermedadService.getEnfermedadesEstado(codigo);
    }

    @GetMapping(value = "/casos/disease",produces = {"application/json"})
    public ResponseEntity getAllDisease(){
        return enfermedadService.getEnfermedades();
    }

    @GetMapping(value = "/save",produces = {"application/json"})
    public ResponseEntity<String> save(){
        boolean update = dataBaseStorage.guardarTodo();
        if(update){
            downloadControl.startDownload();
            downloadControl.changeDownloadInterval(86400);
            return new ResponseEntity("Base de datos actualizada correctamente", HttpStatus.OK);
        }else{
            downloadControl.startDownload();
            downloadControl.changeDownloadInterval(600);
            return new ResponseEntity("Por ahora no esta disponible una nueva version, se intentara mas tarde", HttpStatus.NO_CONTENT);
        }
    }
}
