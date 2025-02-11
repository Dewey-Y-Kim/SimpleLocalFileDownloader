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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test{

    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {
        // 자바에서 사용시 vpn 켜고 사용할 것
        URI uri = new URI("");
        URL url = uri.toURL();
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setConnectTimeout(3000);
        
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
