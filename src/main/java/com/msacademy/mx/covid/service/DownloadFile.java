package com.msacademy.mx.covid.service;


import com.msacademy.mx.covid.model.*;
import com.msacademy.mx.covid.utils.HttpDownloadUtility;
import com.msacademy.mx.covid.utils.UnzipUtility;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DownloadFile {

    private final Logger logger= LoggerFactory.getLogger(DownloadFile.class);

    @Autowired
    HttpDownloadUtility httpDownloadUtility;

    @Autowired
    UnzipUtility unzipUtility;

    public static final String FILE_URL = "http://187.191.75.115//gobmx//salud//datos_abiertos//datos_abiertos_covid19.zip";
    public static final String SAVE_DIR = ".//dataBase";
    public static final String ZIP_FILE_PATH = ".//dataBase//datos_abiertos_covid19.zip";



    public List<Caso> getData(){
        ArrayList<Caso> casoArrayList = new ArrayList<>();

        if(isUpdated()){
            ArrayList<String> lastFile = getCSV(SAVE_DIR);
            if(lastFile.size() == 1){
            }else{
                logger.info(lastFile.get(0));
                logger.info(lastFile.get(1));
                deleteFiles(SAVE_DIR,lastFile.get(0));
                casoArrayList = readCSV(SAVE_DIR + "//" +lastFile.get(1));
            }
        }else{
            logger.info("No se pudo descargar y/o descomprimir el archivo");
        }

        return casoArrayList;
    }

    public boolean isUpdated(){
        boolean allProccesDone = false;
        if(downloadFile(FILE_URL,SAVE_DIR) && unZipFile(ZIP_FILE_PATH,SAVE_DIR))
                allProccesDone = true;

        return allProccesDone;
    }

    private void deleteFiles(String dir,String file){
        try{
            Files.delete(Paths.get(dir + "//" + file));
        }catch (IOException e){
            logger.error(e.toString());
        }
    }

    private ArrayList<String> getCSV(String dir){
        String[] fileNames;
        File f = new File(dir);
        fileNames = f.list();
        ArrayList<String> fileCSV = new ArrayList<>();
        for (String file : fileNames){
            try{
                if(file.contains(".csv")){
                    fileCSV.add(file);
                }
            }catch (Exception e){
                logger.error(e.toString());
            }
        }
        Collections.sort(fileCSV);
        return fileCSV;
    }

    private boolean downloadFile(String fileURL, String saveDir){
        boolean downloaded = false;
        try {
            httpDownloadUtility.downloadFile(fileURL, saveDir);
            downloaded = true;
        } catch (Exception ex) {
            logger.error(ex.toString());
        }

        return downloaded;
    }

    private boolean unZipFile(String zipFilePath, String destDirectory){
        boolean unzipped = false;
        UnzipUtility unzipper = new UnzipUtility();
        try {
            unzipper.unzip(zipFilePath, destDirectory);
            unzipped = true;
        } catch (Exception ex) {
            logger.error(ex.toString());
        }

        return unzipped;
    }


    private ArrayList<Caso> readCSV(String file){
        ArrayList<Caso> casos = new ArrayList<>();

        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();
            // print Data

            for (String[] row : allData) {

                if(row[12].compareTo("9999-99-99") == 0){
                    row[12] = "2000-01-01";
                }


                Enfermedad enfermedad = new Enfermedad(new Especificacion(Integer.parseInt(row[14]),"")
                        ,new Especificacion(Integer.parseInt(row[19]),"")
                        ,new Especificacion(Integer.parseInt(row[20]),"")
                        ,new Especificacion(Integer.parseInt(row[23]),"")
                        ,new Especificacion(Integer.parseInt(row[21]),"")
                        ,new Especificacion(Integer.parseInt(row[22]),"")
                        ,new Especificacion(Integer.parseInt(row[25]),"")
                        ,new Especificacion(Integer.parseInt(row[26]),"")
                        ,new Especificacion(Integer.parseInt(row[27]),"")
                );

                Caso caso = new Caso(new Sector(Integer.parseInt(row[3]),""),new EntidadFederativa(Integer.parseInt(row[4]),"","")
                        ,new Sexo(Integer.parseInt(row[5]),""),
                        new EntidadFederativa(Integer.parseInt(row[6]),"",""),
                        new EntidadFederativa(Integer.parseInt(row[7]),"","")
                        ,new TipoPaciente(Integer.parseInt(row[9]),""),
                        LocalDate.parse(row[10]),
                        LocalDate.parse(row[11]),
                        LocalDate.parse(row[12]),
                        new Especificacion(Integer.parseInt(row[13]),""),
                        Integer.parseInt(row[15]),
                        new Nacionalidad(Integer.parseInt(row[16]),"")
                        ,new Especificacion(Integer.parseInt(row[18]),""),
                        enfermedad,
                        new Especificacion(Integer.parseInt(row[17]),""),
                        new Resultado(Integer.parseInt(row[30]),""),
                        new Especificacion(Integer.parseInt(row[34]),""));

                casos.add(caso);
            }
        }
        catch (Exception e) {
            logger.error(e.toString());
        }

        return casos;
    }

}
