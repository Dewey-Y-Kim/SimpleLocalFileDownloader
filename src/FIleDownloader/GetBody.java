package FIleDownloader;

import java.util.ArrayList;
import java.util.List;

public class GetBody {
    List<String> originalTxt;
    public GetBody(List<String> originalTxt){
        this.originalTxt = originalTxt;
    }
    public List<String> getPath(){
        List<String> result = new ArrayList<>();
        result.add(GetTitle());

        for(String str : originalTxt){
            if(str.contains("<li>")){
                str = getbody(str);
                result.add(str);
            }
        }
        return result;
    }
    public String GetTitle(){
        String str = "";
        for(String line : originalTxt){
            if(line.contains("총")){
                str = line;
                break;
            }
        }
        return str;
    }
    public String getbody(String line){
        String title;
        line.indexOf("/li>");
        if(line.contains("<span"))  {
            System.out.print(true);
            line.substring(line.lastIndexOf("<span"));
        }
        System.out.println(line);
        return line;
    }
}
