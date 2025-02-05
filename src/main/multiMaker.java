package main;

import main.java.file_downloader.Downloader;

import java.io.IOException;
import java.net.URISyntaxException;

public class multiMaker {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Downloader downloader;
        for(String str : args){
            downloader=new Downloader(str);
            downloader.makeFulltoMultifile();
        }
    }
}
