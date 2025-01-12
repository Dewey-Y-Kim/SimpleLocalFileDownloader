package FIleDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class getTitleAndUrl {
    String html;
    public getTitleAndUrl(String html){
        this.html = html;
    }

    public Vector<String> getUrl(){
        Vector<String> result=null;
        if( html != null){
           ArrayList<String> list = new ArrayList<>(List.of(html.split("\s")));
        }
        return result;
    }
}
