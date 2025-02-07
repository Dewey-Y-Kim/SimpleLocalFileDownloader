package test.java;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class test{

    public static void main(String[] args) throws IOException, URISyntaxException {
//
//        File file = new File("/home/dewey/Downloads/books/detail.html");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        List<String> list  = new ArrayList<>();
//
//        // get var to Array
//        String keyword = "var img_list";
//        String str;
//        String[] result= null;
//        //finder
//        while((str = bufferedReader.readLine())!=null){
//            if (str.contains(keyword)){
//                result = str.replaceFirst("(.*)\\[","").replaceFirst("]","").replaceAll("\"","").replaceAll(";","").split(",");
//                break;
//            }
//        }
//        for(int idx = 0 ; idx < result.length ; idx ++ ){
//            result[idx] = "https:"+result[idx];
//            System.out.println(result[idx]);
//        }
//
        //        SaveText saveText = new SaveText("test",list);
//        saveText.save();
        // if you want to get png or jpg ... you can do it
//        String address = "";
//        String path = "main/setting.properties";
//        String defaultPath = new ReadProperty(path).readProperties().getProperty("Download.path");
//        String fileName = "test";
//        String result;
//        URL url = new URI(address).toURL();
//        String extension = address.substring(address.indexOf('.') + 1);
//
//        BufferedImage image = ImageIO.read(url);
//        File file = new File(defaultPath + fileName);
//
//        ImageIO.write(image, extension, file);
//        result = fileName + " is complete.";

//        File file = new File("/home/dewey/Downloads/books/list.html");
        String path = "";
        URL url = new URI(path).toURL();
        System.out.println(url.getHost()+url.getPath());
        
    }

}
