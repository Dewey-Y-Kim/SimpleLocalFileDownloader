import FIleDownloader.ConnectListUrl;
import FIleDownloader.GetBody;
import FIleDownloader.ReadText;
import FIleDownloader.SaveText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException ,NullPointerException{
        String filePath = "/home/dewey/test1.txt";
        HttpURLConnection connect = ConnectListUrl.openConnect("Web adress");
        List<String> list = ConnectListUrl.readUrl(connect);
        connect.disconnect();
        SaveText saveText = new SaveText(filePath, list);
        saveText.save();
//        ReadText readText = new ReadText(filePath);
//        List<String> readtxt= readText.readTxt();
////        System.out.println("main");
//        GetBody getBody = new GetBody(readtxt);
//        List list = getBody.getPath();
//
//        System.out.print(list);


    }
}
