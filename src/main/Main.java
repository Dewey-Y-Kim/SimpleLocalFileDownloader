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
        if(args.length == 0 ){
            System.out.println("null");
            downloader = new Downloader();
            downloader.makeFullToOnefile();
        } else{
            for(String str : args){
                downloader=new Downloader(str);
                downloader.makeFullToOnefile();

            }
        }
//        if(args.length != 0){
//            Iterator<String> iterator = Arrays.stream(args).iterator();
//            while(iterator.hasNext()){
//                url.add(iterator.next());
//            }
//        } else{
//            url.add(temp);
//        }
//
//        Iterator listUrl = url.iterator();
//        while ( listUrl.hasNext()){
////            Downloader downloader = new Downloader(listUrl.next().toString());
//            downloader.makeFullToOnefile();
//        }
    }
}