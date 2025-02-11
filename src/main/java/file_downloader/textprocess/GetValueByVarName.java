package main.java.file_downloader.textprocess;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GetValueByVarName {
    List list;
    String[] result;

    public GetValueByVarName(String keyword, String body){
        this.list = Arrays.stream(body.split("\n")).toList();
        this.result = setResult(keyword);
    }

    private String[] setResult(String keyword) {

        String[] result= null;
        Iterator iterator = list.iterator();
        //finder
        while(iterator.hasNext()){
            String str = iterator.next().toString();
            if (str.contains(keyword)){
                result = str.replaceFirst("(.*)\\[","")
                        .replaceFirst("]","")
                        .replaceAll("\"","")
                        .replaceAll(";","")
                        .split(",");
                break;
            }
        }
        return result;
    }

    public String[] getResult() {
        return result;
    }


}
