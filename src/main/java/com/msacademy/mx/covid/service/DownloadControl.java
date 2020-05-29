package com.msacademy.mx.covid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.SECONDS;


@Service
public class DownloadControl {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> downloadHandle;
    private Runnable download;

    @Autowired
    DataBaseStorage dataBaseStorage;

    public void startDownload() {
        download = () ->
                dataBaseStorage.guardarTodo();

        downloadHandle =
                scheduler.scheduleAtFixedRate(download, 10000, 2, SECONDS);


        scheduler.schedule(() -> {
            downloadHandle.cancel(true);
            scheduler.shutdown();
        }, 1 * 10L, DAYS);

    }

    public void changeDownloadInterval(long time)
    {
        if(time > 0)
        {
            if (downloadHandle != null)
            {
                downloadHandle.cancel(true);
            }

            downloadHandle = scheduler.scheduleAtFixedRate(download, 0, time, TimeUnit.SECONDS);
        }
    }
}