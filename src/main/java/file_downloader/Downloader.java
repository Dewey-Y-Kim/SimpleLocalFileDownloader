package main.java.file_downloader;

import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.fileprocess.ListObj;
import main.java.file_downloader.fileprocess.SplitLiTag;
import main.java.file_downloader.responseprocess.GetBody;
import main.java.file_downloader.fileprocess.SaveText;
import main.java.file_downloader.textprocess.GetValueByVarName;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Downloader {
    private final String address;
    public Downloader(){
        this.address  = new ReadProperty("main/setting.properties").readProperties().getProperty("Download.url");
    }
    public Downloader(String address) {
        this.address = address;
    }
    public void makeFullToOnefile() throws IOException, URISyntaxException {
        // 목록 연결
        Connector connector = new Connector(address) ;

        GetBody titleList = new GetBody(connector.getList());

        List list = titleList.getResult();

        SaveText saveOneTextFile = new SaveText(titleList.getTitle());
        for (int idx = (list.size()+1) / 2 ; idx>0 ; idx--) {
            String smallUrl = (String) list.get(idx * 2 - 1);
            String smallTitle = (String) list.get(idx * 2 -2);
            ConnectListUrl tempConnect = new ConnectListUrl(smallUrl);
            GetBody temp = new GetBody(tempConnect.getResult(), smallTitle);
            System.out.println(temp.getTitle() + " is reading.");

            saveOneTextFile.appendText(temp.getResult());
        }
            saveOneTextFile.save();
    }
    public void makeFulltoMultifile() throws IOException, URISyntaxException {
        Connector connector = new Connector(address);
        GetBody titleList = new GetBody(connector.getList());
        System.out.println("titleList.getTitle() : "+ titleList.getTitle());
        List list = titleList.getResult();
        for (int idx = (list.size()+1) / 2 ; idx>0 ; idx--) {
            String smallUrl = (String) list.get(idx * 2 - 1);
            String smallTitle = (String) list.get(idx * 2 -2);
            ConnectListUrl tempConnect = new ConnectListUrl(smallUrl);
            GetBody temp = new GetBody(tempConnect.getResult(), smallTitle);
            SaveText saveText = new SaveText(titleList.getTitle(), smallTitle, temp.getResult());
            saveText.save();
            System.out.println(temp.getTitle() + " has saved.");
        }
    }
    public void makeImage() throws IOException, URISyntaxException {
        List original = makeImageList();
        // in List, //naver.com
        // "https:" + path -> address
        // title + 1234(index) -> filename

    }
    public void makeList() throws IOException, URISyntaxException {
        List original = makeImageList();
        
    }
    public List makeImageList() throws IOException, URISyntaxException {
        String original ="";
//        read Data
        try {
            Connector connector = new Connector(address);
            original = connector.getResult();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        // get Collumn
        List<String> splittedLiTag = new SplitLiTag(original).getResult();

        // make List contains title, address
        List<HashMap> pathList = new ArrayList<>();
        String keyword = "img_list";

        for(String str : splittedLiTag){
            ListObj obj = new ListObj(str);
            HashMap hashMap = new HashMap<>();
            hashMap.put("title", obj.getTitle());
            pathList.add(hashMap);
            String[] imgPath = new GetValueByVarName(keyword,new Connector(obj.getAddress()).getResult()).getResult();
            hashMap.put("imgPath",imgPath);
        }

        return pathList;
//        new SaveText("makeImage.txt");
    }
}
