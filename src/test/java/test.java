package test.java;

<<<<<<< HEAD
import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.connector.Connector;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //p3p:


//        File file = new File("/home/dewey/Downloads/image.txt");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        String str;
//        Integer idx = -1;
//
//        String title = "화무십일홍";
//        while((str = bufferedReader.readLine())!=null){
//            if(str.length() >1)  {
//                idx++;
//                String filename = title+"-"+ new TextTransform().lPad((idx.toString()), 3) +".jpeg";
//                new ImageMaker( str,"화무십일홍", filename).make();
//            }
//
//            System.out.printf("%d   %s is downloaded\n",idx ,idx+".png");
//        }
//        new Downloader("https://11toon144.com/bbs/board.php?bo_table=toons&stx=%EC%97%B4%ED%98%88%EA%B0%95%ED%98%B8&is=1").makeImageList();
//        new Downloader("https://newtoki.biz/webtoon/14080").makeImageList();
    }
    public String patternMaker(String str){
        Pattern pattern = Pattern.compile("src=\"(.*)\" ");
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text.replaceFirst("\\S*=\"","").replaceFirst("\"(.*)","");

    }

}
