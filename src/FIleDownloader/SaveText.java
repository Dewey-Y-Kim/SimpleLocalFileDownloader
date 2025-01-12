package FIleDownloader;

import java.io.FileWriter;
import java.util.List;

public class SaveText {
    String bodyText;
    String fullPath;

    public SaveText(){
    }
    public SaveText(String path, String bodyText){
        this.fullPath = path;
        this.bodyText = bodyText;
    }
    public SaveText(String path, String filename,List<String> bodyText){
        if(path.lastIndexOf("/")==path.length()){
            this.fullPath = path + filename;
        } else{
            this.fullPath = path + "/" + filename;
        }
        this.bodyText = bodyText.toString();
    }
    public void save(){
        //Text 파일 저장

    }
}
