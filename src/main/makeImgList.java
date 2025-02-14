package main;

import main.java.file_downloader.Downloader;

import java.io.IOException;
import java.net.URISyntaxException;

public class makeImgList {
    public static void main(String ...args) throws IOException, URISyntaxException {
        for( String address : args){
            new Downloader(address).makeImgList();
        }
    }
}
