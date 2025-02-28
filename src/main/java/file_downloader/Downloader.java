package main.java.file_downloader;

import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.fileprocess.SaveText;
import main.java.file_downloader.imageprocess.ImgProcess;
import main.java.file_downloader.responseprocess.GetBody;

import java.io.IOException;
import java.net.URISyntaxException;
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
        connector.readUrl();
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
        connector.readUrl();
        GetBody titleList = new GetBody(connector.getList());
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
    public void makeImg() throws IOException, URISyntaxException {
        ImgProcess imgProcess = new ImgProcess(address);

        List source = imgProcess.makeImageList();

        imgProcess.makeImg(source);
    }

    public void makeImgList() throws IOException, URISyntaxException {
        ImgProcess imgProcess = new ImgProcess(address);
        List original = imgProcess.makeImageList();
        for (Object map : original) {
            HashMap obj = (HashMap) map;
            String title = (String) obj.get("title");
            String[] array = (String[]) obj.get("imgPath");
            for (int idx = 0; idx < array.length; idx++) {
                array[idx] = "https:" + array[idx];
            }
            new SaveText( title, array).save();
        }
    }
}
