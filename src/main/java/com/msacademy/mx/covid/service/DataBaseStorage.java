package com.msacademy.mx.covid.service;

import com.msacademy.mx.covid.model.Caso;
import com.msacademy.mx.covid.model.Enfermedad;
import com.msacademy.mx.covid.repository.CasoRepository;
import com.msacademy.mx.covid.repository.EnfermedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataBaseStorage {

    @Autowired
    CasoRepository casoRepository;

    @Autowired
    EnfermedadRepository enfermedadRepository;

    @Autowired
    DownloadFile downloadFile;


    public boolean guardarTodo(){
        boolean isSaved = false;
        ArrayList<Caso> casosList = downloadFile.getData();
        if(!casosList.isEmpty()){
            if(borrarTodo()) {
                ArrayList<Enfermedad> listaEndermedades = new ArrayList<>();
                for (Caso individual : casosList) {
                    listaEndermedades.add(individual.getEnfermedad());
                }
                try {
                    enfermedadRepository.saveAll(listaEndermedades);
                    System.out.println("Save Enfermedades");
                    casoRepository.saveAll(casosList);
                    System.out.println("Save CASOS");

                    isSaved = true;
                } catch (Exception ex) {
                    System.out.println(ex.getStackTrace());
                }
            }
        }
        return isSaved;
    }

    public boolean borrarTodo(){

        boolean isDeleted = false;
        try{
            casoRepository.deleteAll();
            enfermedadRepository.deleteAll();
            System.out.println("Deleted");
            isDeleted= true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return isDeleted;
    }
}
