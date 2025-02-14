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
        String[] ag ={
                "https://11toon144.com/bbs/board.php?bo_table=toons&stx=%EC%99%95%EB%A6%BD%EB%A7%88%EC%88%A0%ED%95%99%EC%9B%90%EC%9D%98%20%EA%B7%80%EC%B6%95%20%EA%B0%95%EC%82%AC&search=1&is=34327"
        };
        for (String str : ag){
            new Downloader(str).makeImg();
        }
//        new Downloader(
//        ""
//        ).makeImg();;
//        if(type.contains("http")){
//            downloader= new Downloader(type);
//            downloader.makeFullToOnefile();
//        }else{
//
//        }
//        for(String str : args){
//            downloader=new Downloader(str);
//            downloader.makeFullToOnefile();
//        }
    }
}