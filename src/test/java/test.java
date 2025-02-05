package test.java;

import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.fileprocess.SaveText;
import main.java.file_downloader.responseprocess.GetBody;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;

public class test{

    public static void main(String[] args) throws IOException, URISyntaxException {
//        // Image Downloader
//        String address = "";
//        String filePath = "";
//        URL url = new URI(address).toURL();
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setConnectTimeout(1000);
//        connection.setRequestProperty("content-type","image/jpeg");
//        InputStream in = connection.getInputStream();
//        int i;
//        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
//        while((i = in.read()) != -1){
//            fileOutputStream.write(i);
//        }
//        fileOutputStream.flush();
        ConnectListUrl connectListUrl = new ConnectListUrl("http://156.239.152.81:7600/bbs/board.php?bo_table=toons&stx=%EC%9D%80%ED%98%BC&search=1&is=40");
        List<String> list = connectListUrl.getResult();
        SaveText saveText = new SaveText("test",list);
        saveText.save();
        
    }

}
