package main.java.file_downloader;

import java.io.IOException;
import java.util.List;

public class Downloader {
    private String address;
    String downLoadPath = "/home/dewey/Downloads/books/";
    public Downloader(String address) {
        this.address = address;
    }

    public Downloader() {

    }

    public void makeOnefile() throws IOException {
        ConnectListUrl connectListUrl = new ConnectListUrl(address);
        GetBody titleList = new GetBody(connectListUrl.getResult());
        List list =titleList.getResult();
            SaveText saveText = new SaveText(titleList.getTitle());
        for (int idx = (list.size()+1) / 2 ; idx>0 ; idx--) {
            String smallUrl = (String) list.get(idx * 2 - 1);
            String smallTitle = (String) list.get(idx * 2 -2);
//            System.out.println(idx *2 -1);
            ConnectListUrl tempConnect = new ConnectListUrl(smallUrl);
//            try{
//                Thread.sleep((long) Math.round((Math.random()* 1000)));
            GetBody temp = new GetBody(tempConnect.getResult(), smallTitle);
            System.out.println(temp.getTitle() + " is reading.");

            saveText.appendText(temp.getResult());
        }
            saveText.save();
    }
}
