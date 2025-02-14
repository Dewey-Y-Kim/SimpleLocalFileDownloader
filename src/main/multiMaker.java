package main;

import main.java.file_downloader.Downloader;

import java.io.IOException;
import java.net.URISyntaxException;

public class multiMaker {
    public static void main(String ...args) throws IOException, URISyntaxException {
        for(String str : args){
            new Downloader(str).makeFulltoMultifile();
        }
    }
}
