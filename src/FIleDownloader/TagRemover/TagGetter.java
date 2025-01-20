package FIleDownloader.TagRemover;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagGetter {
    List<String> originalList;

    public TagGetter(){

    }
    public TagGetter(List<String> list){
        originalList = list;
    }
    private void SetOriginal (List<String> list){
        this.originalList = list;
    }
    public List<String> getResult(){
        return originalList;
    }
    public TagGetter removeTag(String tag){
        List<String> result = new ArrayList<>();
        boolean adder = false;
        System.out.println("TagRemover");
        String regex = "<" + tag + "*"+ "/>";
        Pattern pattern = Pattern.compile(regex);
        System.out.println("regex : " + regex);
        for( String str : originalList){
            if(str.contains("<"+tag)) adder = false;
            if(str.contains("</"+tag)) {
                adder = true;
                continue;
            }

            if(adder) result.add(str);
        }
        SetOriginal(result);
        return this;
    }
    private String PatternMatcher(Pattern pattern, String str){
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text;
    }
    public TagGetter UlGetter(){
        UlGetter("");
        return this;
    }
    public TagGetter UlGetter(String id){
        List<String> result = new ArrayList<>();
        boolean found = false;
        int depth = 0;
        for ( String str : originalList){
            if(str.contains("<ul") && str.contains(id)) {
                found = true;
                depth ++;
            }
            if(str.contains("</ul") && depth > 0) {
                found = false;
                depth --;
            }
            if(found) result.add(str);
        }
        this.originalList = result;
        return this;
    }
    // 문서의 타이틀만 가져오기
//    private void setTitle(){
//        String str = "";
//        for(String line : originalTxt){
//            if(line.contains("<strong>")){
//                str = line.replaceAll("</strong>","").replaceAll("<strong>","").replaceAll("\t","");
//                break;
//            }
//        }
//        this.title = removeDoubleSpace(str);
//
//    }
}
