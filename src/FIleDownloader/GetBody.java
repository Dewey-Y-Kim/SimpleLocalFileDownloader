package FIleDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetBody {
    List<String> originalTxt;
    List<String> removedPtag;

    public GetBody(List<String> originalTxt){
        this.originalTxt = originalTxt;
    }
    // 페이지에서 txt 리턴 <p> 태그만 가져오기
    public List<String> removePtag(List<String> sourceList){

        return null;
    }
    // 페이지에서 img 리턴.
    // 목록에서 제목과 그 url 가져오기
    public List<String> getPath(){
        List<String> result = new ArrayList<>();
        result.add(GetTitle());

        Pattern urlPattern = Pattern.compile("href=\"(.*?)\"");
        Pattern titlePattern = Pattern.compile("</i>(.*?)<span");

        for(String str : originalTxt){
            if(str.contains("<li>")){
                // title 추가
                result.add(PatternMatcher(titlePattern, str).replaceAll("</i>","").replaceAll("<span",""));
                // url 추
                result.add(PatternMatcher(urlPattern, str).replaceAll("\"","").replaceAll("href=",""));
            }
        }
        return result;
    }
    public String PatternMatcher(Pattern pattern, String str){
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text;
    }
    // 문서의 타이틀만 가져오기
    public String GetTitle(){
        String str = "";
        for(String line : originalTxt){
            if(line.contains("총")){
                str = line.substring(line.indexOf(">")+1,line.indexOf("|")-1);
                break;
            }
        }
        return str;
    }
    // 제목과 주소순으로 가져오기

    public String getbody(String line){
        String title;
        line.indexOf("/li>");
        if(line.contains("<span"))  {
            line = line.substring(line.lastIndexOf("<span"));
        }
        System.out.println(line);
        return line;
    }
}
