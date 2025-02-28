package main.java.file_downloader.responseprocess;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

// To-do get title and onclick href using id
public class ListObj {
    String title;
    String address;

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public ListObj(String original) throws URISyntaxException, MalformedURLException {
        String[] originalText = original.split("\n");
        for( String str : originalText){
            if(str.contains("episode-title")){
                this.title = str.replaceAll("<(.*)\">","").replaceAll("</(.*)>","");
            }
            if(str.contains("location.href")){
                this.address = str.replaceAll("oncl(.*)=`.","").replaceAll("`\"","");
            }
        }
    }
}
