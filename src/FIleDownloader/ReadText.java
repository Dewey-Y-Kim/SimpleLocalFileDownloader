package FIleDownloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
public class ReadText {
    String fullpath;
    String body;

    public ReadText(String path){
        this.fullpath = path;
    }

    public List<String> readTxt(boolean title) throws IOException {

        FileReader fileReader = new FileReader(fullpath);
        List<String> readLine;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = null;
            readLine = new ArrayList<>();
            while (true) {
                    if (!((line = bufferedReader.readLine()) != null)) break;
                readLine.add(line);
            }
        }
        String[] list = readLine.getFirst().split("\t");
        List<String> result = new ArrayList<>();

        if(title){
            result = getTitleList(list);
        } else {
            result = getBody(list);
        }

        return result;
    }
    private boolean chkLine(String line){
        return true;
    }

    public List<String> getTitleList(String[] list){

        List<String> result = new ArrayList<>();
        boolean found = false;
        System.out.println(list.length);
        for (String s : list) {
            if(s.contains("toon_index")){
                found = true;
            }
            if (found == true && s.contains("</ul>") ){
                found = false;
                break;
            }
            // 공백 제거
            if(found == true && s.equals("") || s.contains("toon_index") ||s.contains("ul")){
                continue;
            }
            if( found ) result.add(s);
        }
        System.out.println(result.size());
        return result;
    }
    public   List<String> getBody(String[] list){
        List<String> result = new ArrayList<>();
        String tempbody = "";
        for(String str : list){
            if(str.contains("<p>")){
               tempbody += str;
            }
        }
        String[] splitedBody = tempbody.split("</p><p>");
        for(String str : splitedBody){
            result.add(str.replaceAll("<p>","").replaceAll("</p>",""));
        }

        return result;
    }
    private boolean chkTitle(String str){

        return false;
    }
}
