package test.java;

import main.java.file_downloader.Downloader;
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
        new Downloader("https://11toon144.com/bbs/board.php?bo_table=toons&stx=%EC%97%B4%ED%98%88%EA%B0%95%ED%98%B8&is=1").makeImageList();
//new Downloader("https://newtoki.biz/webtoon/14080").makeImageList();
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
