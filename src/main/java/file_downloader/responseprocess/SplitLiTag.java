package main.java.file_downloader.responseprocess;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SplitLiTag {
    private String original;
    private List<String> result;

    public SplitLiTag(String original){
        this.original = original;
        try {
            result = splitLitag(original);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public SplitLiTag(String original, String keyword){
        this.original =original;
        try {
            result = splitLitag(original, keyword);
        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getResult() {
        return result;
    }
    private List<String> splitLitag(String original) throws MalformedURLException, URISyntaxException {
        return splitLitag(original,"comic-episode-list");
    }
    private List<String> splitLitag(String original, String id) throws MalformedURLException, URISyntaxException {
        String[] originalText = original.split("\n");
        List list = Arrays.stream(originalText).toList();
        Iterator iterator = list.iterator();
        boolean ulFound = false;
        boolean liFound = false;
        int depth = 0;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> result = new ArrayList<>();
        while ( iterator.hasNext()){
            String text = (String) iterator.next();
            if(text.contains(id)){
                ulFound =true;
            }

            if(text.contains("<ul")){
                depth++;
            }
            if(  text.contains("</ul") ){
                depth--;
            }
            if ( depth <= 0 ){
                ulFound = false;
            }
            // li 기준으로 자르기
            if(ulFound && text.contains("<li")){
                depth++;
            }
            if(ulFound && text.contains("</li")){
                stringBuilder.append(text);
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                depth--;
            }
            if( depth > 1){
                liFound = true;
            }
            if( depth <= 1){
                liFound =false;
            }

            if (liFound){
                stringBuilder.append(text);
                stringBuilder.append("\n");
            }

        }

        return result;
    }
}
