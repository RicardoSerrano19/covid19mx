package com.msacademy.mx.covid.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpDownloadUtility {
    private static final int BUFFER_SIZE = 4096;
    private final Logger logger= LoggerFactory.getLogger(HttpDownloadUtility.class);


    public void downloadFile(String fileURL, String saveDir) {
        try{
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();

                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10,
                                disposition.length() - 1);
                    }
                } else {
                    // extracts file name from URL
                    fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1,
                            fileURL.length());
                }


                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;

                // opens an output stream to save into file
                FileOutputStream outputStream = null;
                try{
                    outputStream = new FileOutputStream(saveFilePath);
                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    outputStream.close();
                    inputStream.close();

                }catch (IOException ex){
                    logger.error(ex.toString());
                }finally {
                    if(outputStream != null){
                        outputStream.close();
                    }
                }

            } else {
                logger.error("No se pudo descargar el archivo. Servidor respondio con el codigo HTTP: ");
            }
            httpConn.disconnect();
        }catch (IOException ex){
            logger.error(ex.toString());
        }
    }
}