package test.java;

import main.java.file_downloader.Downloader;
import main.java.file_downloader.imageprocess.ImageMaker;
import main.java.file_downloader.textprocess.TextTransform;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test{

    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {
        // 자바에서 사용시 vpn 켜고 사용할 것
        URI uri = new URI("");
        URL url = uri.toURL();
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setConnectTimeout(3000);
        //GET
        //Host: spotv142.com
        //User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:134.0) Gecko/20100101 Firefox/134.0
        //Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
        //Accept-Language: en-US,en;q=0.5
        //Accept-Encoding: gzip, deflate, br, zstd
        //Connection: keep-alive
        //Upgrade-Insecure-Requests: 1
        //Sec-Fetch-Dest: document
        //Sec-Fetch-Mode: navigate
        //Sec-Fetch-Site: none
        //Sec-Fetch-User: ?1
        //Priority: u=0, i
        connection.setRequestMethod("GET");
        System.out.println(connection.getResponseCode());
//
//        InputStream inputStream = connection.getInputStream();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader( inputStream));
//        String str = "";
//        StringBuilder stringBuilder = new StringBuilder();
//        if(connection.getResponseCode() == 200){
//            while ((str = bufferedReader.readLine()) != null){
//                stringBuilder.append(str);
//                stringBuilder.append("\n");
//            }
//        }
//        System.out.println(stringBuilder.toString());
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
