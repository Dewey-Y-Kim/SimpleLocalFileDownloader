package FIleDownloader;

import java.io.*;
import java.util.List;

public class SaveText {
    String bodyText;
    String fullPath;

//    public SaveText(){
//    }
    public SaveText(String path, String bodyText){
        this.fullPath = path;
        this.bodyText = bodyText;
    }
    public SaveText(String path, List<String> bodyText){
        this.fullPath = path;
        this.bodyText = bodyText.toString();

    }
    public SaveText(String path, String filename, String bodyText){

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
        try {
            OutputStream outputStream = new FileOutputStream(fullPath);
            byte[] bytes = bodyText.getBytes();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
