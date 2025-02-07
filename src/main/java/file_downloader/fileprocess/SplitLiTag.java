package main.java.file_downloader.fileprocess;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SplitLiTag {
    public List<String> splitLitag(String original) throws MalformedURLException, URISyntaxException {
        String[] originalText = original.split("\n");
        List list = Arrays.stream(originalText).toList();
        Iterator iterator = list.iterator();
        boolean ulfound = false;
        boolean lifound = false;
        int depth = 0;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> result = new ArrayList<>();
        while ( iterator.hasNext()){
            String text = (String) iterator.next();
            if(text.contains("comic-episode-list")){
                ulfound =true;
            }

            if(text.contains("<ul")){
                depth++;
            }
            if(  text.contains("</ul") ){
                depth--;
            }
            if ( depth <= 0 ){
                ulfound = false;
            }
            // li 기준으로 자르기
            if(ulfound && text.contains("<li")){
                depth++;
            }
            if(ulfound && text.contains("</li")){
                stringBuilder.append(text);
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                depth--;
            }
            if( depth > 1){
                lifound = true;
            }
            if( depth <= 1){
                lifound =false;
            }

            if ( lifound ){
                stringBuilder.append(text);
                stringBuilder.append("\n");
            }

        }

        return result;
    }
}
