package main;

import main.java.file_downloader.Downloader;
import main.java.file_downloader.responseprocess.ApiAccess;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        String[] arg = {

        };
        for (String str : arg){
            URI uri = new URI(str);
            String host= uri.getHost();
            Pattern pattern = Pattern.compile("^[^.]+\\.(.*?)\\.(.*)$");
            Matcher matcher = pattern.matcher(host);
            String key="";
            if(matcher.find()){
                key =matcher.group(1).substring(0,4);

            } else {
                key = host.substring(0,4);
            }

            switch (key){
                case "hodu":
                    new ApiAccess(str).start();

                    break;
                case "11to":
                    new Downloader(str).makeImg();
                    break;
                case "newt" :
                    new Downloader(str).makeFulltoMultifile();
                    break;
            }

        }
    }
}