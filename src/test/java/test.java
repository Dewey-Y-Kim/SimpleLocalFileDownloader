package test.java;

import main.java.file_downloader.Downloader;
import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.fileprocess.SaveText;
import main.java.file_downloader.textprocess.TextTransformInFolder;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test{

    public static void main(String[] args) throws IOException, URISyntaxException {

//        ConnectListUrl connectListUrl = new ConnectListUrl("https://sukebei.nyaa.si/?f=2&c=0_0&q=%2B%2B+FC2-PPV");
//        connectListUrl.openConnect();
//        List<String > result = connectListUrl.getResult();
//        for(String str : result){
//            System.out.println(str);
//        }
//        Integer i = 1;
//        System.out.println(i.getClass().getName().getClass().getName());
//        // Download multifiles
//        Downloader downloader;
//        for(String str : args){
//            downloader=new Downloader(str);
//            downloader.makeFulltoMultifile();
//        }

        String str1 = "벌 신아 (202)화.txt";
        String str2 = "벌  12  신아 (202)화.txt";
        String str3 = "화123ks492화";
//        for (String s : new TextTransformInFolder().splitTitle(str1)){
//            System.out.println(s);
//        };
        System.out.println(Arrays.stream(Arrays.stream(new TextTransformInFolder().splitTitle(str1)).toArray()).toList());
    }

}
