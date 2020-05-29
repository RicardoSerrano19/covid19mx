package com.msacademy.mx.covid.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class UnzipUtility {

    private static final int BUFFER_SIZE = 4096;
    private final Logger logger= LoggerFactory.getLogger(UnzipUtility.class);

    public void unzip(String zipFilePath, String destDirectory) throws IOException {
            File destDir = new File(destDirectory);
            if (!destDir.exists()) {
                destDir.mkdir();
            }

            ZipInputStream zipIn = null;
            try {
                zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
                ZipEntry entry = zipIn.getNextEntry();
                // iterates over entries in the zip file
                while (entry != null) {
                    String filePath = destDirectory + File.separator + entry.getName();
                    if (!entry.isDirectory()) {
                        // if the entry is a file, extracts it
                        extractFile(zipIn, filePath);
                    } else {
                        // if the entry is a directory, make the directory
                        File dir = new File(filePath);
                        dir.mkdir();
                    }
                    zipIn.closeEntry();
                    entry = zipIn.getNextEntry();
                }
                zipIn.close();
            }catch (IOException ex){
                logger.error(ex.toString());
            }finally {
                if(zipIn != null){
                    zipIn.close();
                }
            }
    }
    
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = null;
        try{
            bos = new BufferedOutputStream(new FileOutputStream(filePath));
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }catch (IOException ex){
            logger.error(ex.toString());
        }finally {
            if (bos != null){
                bos.close();
            }
        }
    }
}