import FIleDownloader.ConnectListUrl;
import FIleDownloader.GetBody;
import FIleDownloader.ReadText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException ,NullPointerException{
        String filePath = "/home/dewey/test.txt";
//        HttpURLConnection connect = ConnectListUrl.openConnect("****");
//        List<String> list = ConnectListUrl.readUrl(connect);
//        connect.disconnect();

        ReadText readText = new ReadText(filePath);
        List<String> readtxt= readText.readTxt();
//        System.out.println("main");
        GetBody getBody = new GetBody(readtxt);
        List list = getBody.getPath();

        System.out.print("end");


    }
}
