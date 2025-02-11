package test.java;

import com.sun.javafx.geom.AreaOp;
import main.java.file_downloader.Downloader;
import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.connector.Connector;
import org.w3c.dom.html.HTMLUListElement;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test{

    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {
        // 자바에서 사용시 mtu 513 정상가동
        String address ="";
        new Downloader(address).makeImg();
    }
    public static String patternMaker(String originalPattern, String str){
        Pattern pattern = Pattern.compile(originalPattern);
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text.replaceFirst("\\S*=\"","").replaceFirst("\"(.*)","");

    }

}
