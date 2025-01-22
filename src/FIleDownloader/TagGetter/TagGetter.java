package FIleDownloader.TagGetter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    // 테그만
    public TagGetter tagGetter(TagType tag){
        return tagGetter(tag, "");
    }

    // 특정단어가 포함된 테그
    public TagGetter tagGetter(TagType tag, String contains){
        List<String> result = new ArrayList<>();
        boolean found = false;
        int depth = 0;
        int idx = -1;
        for ( String str : originalList ){
            idx ++;
            if(str.contains("<"+tag) && str.contains(contains)) {
                found = true;
            }
            if(found && str.contains("<"+tag)) depth ++;
            if(found && str.contains("</"+tag) && depth >= 0) {
                depth --;
            }
            if(found && str.length()>=0) {
                result.add(str.replaceAll("    ","  ").replaceAll("\t",""));
            }
            if(depth == 0){
                found =false;
            }
        }
        this.originalList = result;
        return this;
    }

    public List listGetter(TagType tag){
        tmpList list = new tmpList(originalList);

        int depth = 0;
        boolean found = false;
        String tmp;
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        while ((tmp = list.getNext())!= null) {
            if (tmp.contains("<"+tag.toString())) {
                found = true;
                depth++;
            }
            if (tmp.contains("</"+ tag.toString())) {
                depth--;
            }
            if(found){
                stringBuilder.append(tmp);
                if(depth != 0 ) stringBuilder.append("\n");
            }
            if (tmp.contains("</"+tag.toString()) && depth == 0) {
                found = false;
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }
        for( int idx = 0 ; idx < result.size(); idx ++){
            System.out.println(idx+"    "+result.get(idx));
        }
        return result;
    }

}
