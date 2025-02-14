package test.java;

import main.java.file_downloader.fileprocess.ReadText;
import main.java.file_downloader.imageprocess.ImageMaker;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
//        String test = "\n" +
//                "<meta name=\"title\" content=\"GTO 쇼난 14days 후지사와 토오루,일일툰, 무료웹툰\" />\n" +
//                "<meta name=\"subject\" content=\"GTO 쇼난 14days 후지사와 토오루,일일툰, 무료웹툰\" />\n" +
//                "<meta name=\"publisher\" content=\"일일툰\" />\n" +
//                "<meta name=\"author\" content=\"후지사와 토오루\" />\n" +
//                "<meta name=\"robots\" content=\"index,follow\" />\n" +
//                "<meta name=\"keywords\" content=\"GTO 쇼난 14days 후지사와 토오루,웹툰,만화,코믹,무료,Free,Webtoon,Comic,Comix,Manga,ani,animation,애니,최신,최신화,무료만화,원펀맨,무료애니,무료웹툰\" />\n" +
//                "<meta name=\"description\" content=\"GTO 쇼난 14days 후지사와 토오루,\" />\n" +
//                "<link rel=\"image_src\" href=\"//11toonimg2.spotv24.com/data/toon_category/8288\" />\n" +
//                "<link rel=\"canonical\" href=\"//11toon144.com/bbs/board.php?bo_table=toons&stx=GTO%20%EC%87%BC%EB%82%9C%2014days&search=1&is=8288\" />\n" +
//                "\n" +
//                "<!-- for Facebook -->\n" +
//                "<meta property=\"og:title\" content=\"GTO ";


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