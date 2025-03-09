package main.java.file_downloader.connector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcutorSet {
    ExecutorService service;
    public ExcutorSet(int excutorNumber){
        service = Executors.newFixedThreadPool(excutorNumber);
    }
    public ExecutorService getService(){
        return service;
    }
}
