package main;

import main.java.file_downloader.Downloader;

import java.io.IOException;
import java.net.URISyntaxException;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class OneMaker {
    public static void main(String[] args) throws IOException, URISyntaxException {
        for(String str : args){
            new Downloader(str).makeFullToOnefile();
        }
    }


}