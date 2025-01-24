package file_downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetBody {
    private List<String> originalTxt= null;
    private String title;
    private List<String> resultText;

    public GetBody(List<String> originalTxt){
        this.originalTxt = originalTxt;
        setTitle();
        setList();
    }

    public GetBody(List<String> originalTxt, String title){
        this.originalTxt = originalTxt;
        this.title = title;
        this.resultText = setPage();
    }


    public String getTitle(){
        return title;
    }

    public List<String> getResult(){
        return resultText;
    }

    // 페이지에서 txt 리턴 <p> 태그만 가져오기
    public List<String> setPage(){
        List<String> removedPtag = new ArrayList<>();
            int i=0;
        for( String str : originalTxt){
            i++;
            if ( str.indexOf("<p>")>-1){
                removedPtag.add(str.replaceAll("</p>","\n").replaceAll("<p>",""));
            }
        }
        return removedPtag;
    }
    // 페이지에서 img 리턴.


    // 목록에서 제목과 그 url 가져오기
    private void setList(){
        List<String> result = new ArrayList<>();
        List temp = new ArrayList();
        Pattern urlPattern = Pattern.compile("href=\"(.*?)\"");
        Pattern titlePattern = Pattern.compile("</i>(.*?)<span");
        boolean found = false;

        for(String str : originalTxt){

            str.replaceAll("\t","").replaceAll("\\t","");
            if(str.contains("toon_index")){
                found = true;
                continue;
            }
            if (found && str.contains("</ul>") ){
                found = false;
                break;
            }
            // 공백 제거
            if(found == true && str.length() < 3 &&(str.equals("") || str.contains("toon_index") ||str.contains("ul"))){
                continue;
            }
            if( found && str.contains("<li>")) {
                temp.add(str);
                String smallTitle = PatternMatcher(titlePattern, str).replaceAll("</i>", "").replaceAll("<span", "");
                String smallUrl = PatternMatcher(urlPattern, str).replaceAll("\"","").replaceAll("href=","");
                result.add(smallTitle);
                result.add(smallUrl);
            }
        }
//        System.out.println("temp = " + temp);
//        System.out.println("result = " + result);
        this.resultText =  result;
    }
    private String PatternMatcher(Pattern pattern, String str){
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text;
    }
    // 문서의 타이틀만 가져오기
    private void setTitle(){
        String str = "";
        for(String line : originalTxt){
            if(line.contains("<strong>")){
                str = line.replaceAll("</strong>","").replaceAll("<strong>","").replaceAll("\t","");
                break;
            }
        }
        this.title = removeDoubleSpace(str);

    }
    //2칸 이상 공백 제거
    public String removeDoubleSpace(String str){
        while(str.indexOf("  ")>-1){
            str = str.replaceAll("  "," ");
        }
        return str;
    }
}
