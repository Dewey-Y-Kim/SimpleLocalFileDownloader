package main.java.file_downloader.textprocess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class TagGetter {
    List<String> originalList;
    public TagGetter(List<String> list){
        originalList = list;
    }

    private void setOriginal (List<String> list){
        this.originalList = list;
    }

    // 최종결과 출력용
    public List<String> getResult(){
        return originalList;
    }

    public TagGetter removeTag(String tag){
        List<String> result = new ArrayList<>();
        boolean adder = false;
        String regex = "<" + tag + "*"+ "/>";
        Pattern pattern = Pattern.compile(regex);
        for( String str : originalList){
            if(str.contains("<"+tag)) adder = false;
            if(str.contains("</"+tag)) {
                adder = true;
                continue;
            }

            if(adder) result.add(str);
        }
        setOriginal(result);
        return this;
    }
//    private String PatternMatcher(Pattern pattern, String str){
//        Matcher matcher = pattern.matcher(str);
//        String text = "";
//        while ( matcher.find()){
//            text= matcher.group();
//        }
//        return text;
//    }
    // 테그만
//    public TagGetter tagGetter(TagType tag){
//        return tagGetter(tag, "");
//    }

    // 특정단어가 포함된 테그
    public TagGetter tagGetter(TagType tag, String contains){
        List<String> result = new ArrayList<>();
        boolean found = false;
        int depth = 0;
        for ( String str : originalList ){
            if(str.contains("<"+tag.getTagName()) && str.contains(contains)) {
                found = true;
            }
            if(found && str.contains("<"+tag.getTagName())) depth ++;
            if(found && str.contains("</"+tag.getTagName()) && depth >= 0) {
                depth --;
            }
            // 여백처리 이부분 조금 더 수정 필요
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

        int depth = 0;
        boolean found = false;
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        // iterator 사용으로 변경
        Iterator<String> iterator = originalList.iterator();
        while (iterator.hasNext()) {
            String tmp = iterator.next();
            if (tmp.contains("<"+tag.getTagName())) {
                found = true;
                depth++;
            }
            if (tmp.contains("</"+ tag.getTagName())) {
                depth--;
            }
            if(found){
                stringBuilder.append(tmp);
                if(depth != 0 ) stringBuilder.append("\n");
            }
            if (tmp.contains("</"+tag.getTagName()) && depth == 0) {
                found = false;
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }
        return result;
    }

}
