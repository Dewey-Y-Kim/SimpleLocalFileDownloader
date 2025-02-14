package main.java.file_downloader.domain;

import java.util.ArrayList;

public class ImageArr {
    String title;
    int totalImgNum;
    int index;
    float readPercent;
    ArrayList<Img> imgList;
    public ImageArr(String title, String chapter, String[] address){
        this.title = title;
        for (String detailAddress : address)
            imgList.add(0, new Img(chapter, "http:"+detailAddress));
    }
}
