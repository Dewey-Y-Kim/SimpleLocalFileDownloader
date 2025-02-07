package main.java.file_downloader.textprocess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetValueByVarName {
    ArrayList<String> list;
    String[] result;

    public GetValueByVarName(String keyword, List<String> list){
        this.list = (ArrayList<String>) list;
        setResult(keyword);
    }

    private void setResult(String keyword) {

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
        this.result = result;
    }

    public String[] getResult() {
        return result;
    }


}
