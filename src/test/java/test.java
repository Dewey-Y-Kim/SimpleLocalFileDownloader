package test.java;

import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.fileprocess.SaveText;
import main.java.file_downloader.responseprocess.GetBody;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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

//        ConnectListUrl connectListUrl = new ConnectListUrl("");
//        List<String> list = connectListUrl.getResult();

//        fileOutputStream.flush();
        File file = new File("/home/dewey/Downloads/books/detail.html");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> list  = new ArrayList<>();

        // get var to Array
        String keyword = "var img_list";
        String str;
        String[] result= null;
        while((str = bufferedReader.readLine())!=null){
            if (str.contains(keyword)){
                result = str.replaceFirst("(.*)\\[","").replaceFirst("]","").replaceAll("\"","").replaceAll(";","").split(",");
                break;
            }
        }
        for(int idx = 0 ; idx < result.length ; idx ++ ){
            result[idx] = "https:"+result[idx];
            System.out.println(result[idx]);
        }
        
        //        SaveText saveText = new SaveText("test",list);
//        saveText.save();
        
    }

}
