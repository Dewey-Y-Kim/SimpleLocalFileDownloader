package test.java;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test{

    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException, ParseException {
//        // 자바에서 사용시 mtu 513 정상가동
//        System.out.println(Runtime.getRuntime().availableProcessors());
//
//        String address ="";
//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObject = new Connector(address).getJsonObj();
//        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
//
//        Object obj = jsonArray.get(0);
//        JSONObject object = (JSONObject)obj;
//        String detailAddress = "https://1004job.com/api/adult/webtoon/"+ object.get("id")+"/episode";
//        JSONObject jsonObj = new Connector(detailAddress).getJsonObj();
//        String contents = ((JSONObject) jsonObj.get("data")).get("contents").toString().replaceAll("<(.*)=","").replaceAll(">","");
//        String[] contentArr = contents.split("\n");
//        System.out.printf("length : %d \n ------\n contents \n",contentArr.length, Arrays.toString(contentArr));
        test.runTread();

    }
    public static void runTread(){
        List list = new ArrayList<>();
        for(int i=0; i<5; i++) list.add(i);
        Iterator idx = list.iterator();
        System.out.println(list.size());
        Thread tread1 = new shop(idx, "tread1") {};
        Thread tread2 = new shop(idx, "tread2") {};
        Thread tread3 = new shop(idx, "tread3") {};
        tread3.start();;
        tread1.start();
        tread2.start();
    }

    public static String patternMaker(String originalPattern, String str){
        Pattern pattern = Pattern.compile(originalPattern);
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text.replaceFirst("\\S*=\"","").replaceFirst("\"(.*)","");

    }


}
class shop extends Thread{
    Iterator i;
    String name;
    shop(Iterator i, String name){
        this.i = i;
        this.name = name;
    }
    @Override
    public void run(){
        while(i.hasNext()){
            System.out.printf("%s\t%d\n",name, i.next());
        }
    }

}