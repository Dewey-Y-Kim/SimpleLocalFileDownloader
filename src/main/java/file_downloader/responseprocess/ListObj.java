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
            // <div class="episode-title ellipsis">은혼709화</div>
            if(str.contains("episode-title")){
                this.title = str.replaceAll("<(.*)\">","").replaceAll("</(.*)>","");
            }
            // onclick="location.href=`./board.php?bo_table=toons&wr_id=238916&stx=은혼&is=40`"
            if(str.contains("location.href")){
                this.address = str.replaceAll("oncl(.*)=`.","").replaceAll("`\"","");
            }
        }
    }
}
