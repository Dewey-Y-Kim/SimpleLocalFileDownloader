package test.java;

import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.fileprocess.SaveText;
import main.java.file_downloader.textprocess.TextTransformInFolder;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class test{

    public void main(String[] args) throws IOException, URISyntaxException, NullPointerException{
        ConnectListUrl connectListUrl = new ConnectListUrl("https://sukebei.nyaa.si/?f=2&c=0_0&q=%2B%2B+FC2-PPV");
        connectListUrl.openConnect();
        List<String > result = connectListUrl.getResult();
        for(String str : result){
            System.out.println(str);
        }
    }
}
