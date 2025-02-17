package test.java;

import main.java.file_downloader.fileprocess.ReadText;
import main.java.file_downloader.imageprocess.ImageMaker;
import org.json.simple.parser.ParseException;

import java.io.*;
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
        File file = new File("src/main/multiMaker.java");
        System.out.println(file.getAbsolutePath());
        if (file.isDirectory()) {
            for(File f :file.listFiles()){
                System.out.println(f.getName());
            }
        }
        if (file.isFile()){
            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(fileInputStream.readAllBytes().length
            );
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str="";
        StringBuilder stringBuilder = new StringBuilder();
        while((str = bufferedReader.readLine()) != null){
            stringBuilder.append(str);
        }
        System.out.println(stringBuilder.toString());
    }
    public static void runTread(){
        List list = new ArrayList<>();
        for(int i=0; i<5; i++) list.add(i);
        Iterator idx = list.iterator();
        System.out.println(list.size());
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
