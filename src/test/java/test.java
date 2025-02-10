package test.java;

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
        //CP="ALL CURa ADMa DEVa TAIa OUR BUS IND PHY ONL UNI PUR FIN COM NAV INT DEM CNT STA POL HEA PRE LOC OTC"


//        String address = "";
//        URL url = new URI(address).toURL();
//        SSLContext context = SSLContext.getInstance("TLS");
//        context.init(null, null, null);
//
//        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//        connection.setHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });
//        connection.setSSLSocketFactory(context.getSocketFactory());
//        connection.setInstanceFollowRedirects(true);
//
//        connection.setRequestMethod("GET");
//        connection.setReadTimeout(10000);
//
//        System.out.println(connection.getResponseCode());
        ConnectListUrl connectListUrl = new ConnectListUrl(
                "https://11toon144.com/bbs/board.php?bo_table=toons&stx=%EA%B3%A0%EB%8F%84%EB%A1%9C%20%EB%B0%9C%EB%8B%AC%ED%95%9C%20%EC%9D%98%ED%95%99%EC%9D%80%20%EB%A7%88%EB%B2%95%EA%B3%BC%20%EA%B5%AC%EB%B3%84%ED%95%A0%20%EC%88%98%20%EC%97%86%EB%8B%A4&is=32938"
         );
        List list = connectListUrl.getResult();
        for(Object text : list){
            System.out.println((String) text);
        }
    }
}
