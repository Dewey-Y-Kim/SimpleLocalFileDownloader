package main.java.file_downloader.textprocess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GetValueByVarName {
    ArrayList<String> list;
    String[] result;

    public GetValueByVarName(String keyword, String body){
        this.list = (ArrayList<String>) Arrays.stream(body.split("\n")).toList();
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
