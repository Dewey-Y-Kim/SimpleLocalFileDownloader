package FIleDownloader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadText {
    String fullpath;
    String body;
    String Result;
    public ReadText(String path){
        this.fullpath = path;
    }
    public List<String> readTxt() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fullpath));
        String line = null;
        List<String> readLine = new ArrayList<>();
        while( (line = bufferedReader.readLine())!= null ){
            readLine.add(line);
        }
        String[] list = readLine.getFirst().split("\t");
        int i = 0;
        List<String> result = new ArrayList<>();
        boolean found = false;
        for (String s : list) {
            if(found || s.contains("toon_index")){
                found = true;
            } else{
                continue;
            }
            if (s.contains("</ul>") ){
                found = false;
                break;
            }
            if(s.contains("<")){
                s.replaceAll(",","");
                result.add(s);
            }
        }

        return result;
    }
    private boolean chkLine(String line){
        return true;
    }
}
