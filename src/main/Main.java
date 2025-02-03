package main;

import main.java.file_downloader.Downloader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Downloader downloader;
//        if(type.contains("http")){
//            downloader= new Downloader(type);
//            downloader.makeFullToOnefile();
//        }else{
//
//        }
        for(String str : args){
            downloader=new Downloader(str);
            downloader.makeFullToOnefile();
        }
    }
}