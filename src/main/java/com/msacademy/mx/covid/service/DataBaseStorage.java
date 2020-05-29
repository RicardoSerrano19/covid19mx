package com.msacademy.mx.covid.service;

import com.msacademy.mx.covid.model.Caso;
import com.msacademy.mx.covid.model.Enfermedad;
import com.msacademy.mx.covid.repository.CasoRepository;
import com.msacademy.mx.covid.repository.EnfermedadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseStorage {
    private final Logger logger= LoggerFactory.getLogger(DataBaseStorage.class);

    @Autowired
    CasoRepository casoRepository;

    @Autowired
    EnfermedadRepository enfermedadRepository;

    @Autowired
    DownloadFile downloadFile;


    public boolean guardarTodo(){
        boolean isSaved = false;
        List<Caso> casosList = downloadFile.getData();
        if(!casosList.isEmpty() && borrarTodo()) {
                ArrayList<Enfermedad> listaEndermedades = new ArrayList<>();
                for (Caso individual : casosList) {
                    listaEndermedades.add(individual.getEnfermedad());
                }
                try {
                    enfermedadRepository.saveAll(listaEndermedades);
                    logger.info("Enfermedades Guardadas");
                    casoRepository.saveAll(casosList);
                    logger.info("Casos Guardados");
                    isSaved = true;
                } catch (Exception ex) {
                    logger.error(ex.toString());
                }
        }
        return isSaved;
    }

    public boolean borrarTodo(){

        boolean isDeleted = false;
        try{
            casoRepository.deleteAll();
            logger.info("Casos borrados");
            enfermedadRepository.deleteAll();
            logger.info("Enfermedades borradas");
            isDeleted= true;
        }catch(Exception ex){
            logger.error(ex.toString());
        }
        return isDeleted;
    }
}
