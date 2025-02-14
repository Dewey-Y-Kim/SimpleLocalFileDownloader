package main.java.file_downloader.textprocess;

import main.java.file_downloader.fileprocess.SaveText;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTransform {
    public void textTransformer(String path) throws IOException {
        // To-do
        // folder read
        String[] filelist = readDirectory(path);
        List<File> files = new ArrayList<>();

        // get txtFile
        for ( String filename : filelist){
            getTxtFile(path, filename).ifPresent(success->files.add(success));
        }
        for(File file : files){
            // edit txtFile
            String text = editTxtFile(file);
            // remove Tag
            text = tagRemover(text).trim();
            // save file as same name
            writeFileWithText(file, text);
        }
    }
    public String tagRemover(String tag) {
        return tag
//                .replaceAll("<br>", "\n")
//                .replaceAll("<br />","\n")
//                .replaceAll("<br/>","\n")
                .replaceAll("<br\\s*/?>","\n")
                .replaceAll("<span>","")
                .replaceAll("&gt;",">")
                .replaceAll("&lt;","<")
                .replaceAll("</span>","");
//                .replaceAll("<(.*?)>", "");
    }
    // folder read
    public String[] readDirectory(String path){
        File file = new File(path);
        // path 만 추출
        if ( !file.isDirectory()){
            path = path.substring(path.lastIndexOf('/'));
            file = new File(path);
        }
        String[] fileList = file.list();
        return fileList;
    }
    // -> get txt files
    public Optional<File> getTxtFile(String path, String filename){
        if(filename.contains(".txt") && filename.substring(filename.lastIndexOf(".txt")).equals(".txt")){
            return Optional.of( new File(path+"/"+filename));
        }
        return Optional.empty();
    }
    // edit txt file
    public String editTxtFile(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;
        while ((str = bufferedReader.readLine()) != null){
            str.trim();
            str.replaceAll("\s\s","");
            if(str.length()>0 && chkStart(str)) {
                stringBuilder.append("\s");
            }
            stringBuilder.append(str);
//            if(str.length()>0 && chkEnd(str)){
//                stringBuilder.append("\n");
//            }
//            if(!chkEnd(str)) stringBuilder.append("\s");
            if(str.length()>0) stringBuilder.append("\n"); // 줄 추가 않해주니... 한줄짜리로 되버림.
        }
        return stringBuilder.toString();
    }

    protected boolean chkStart(String str){
        return str.substring(0,1).equals("\"") ||
                str.substring(0,1).equals("'")||
                str.substring(0,1).equals("<")||
                str.substring(0,1).equals("―")||
                str.substring(0,1).equals("-")||
                str.substring(0,1).equals("“")||
                str.substring(0,1).equals("`")||
                str.substring(0,1).equals("[");
    }
    protected boolean chkEnd(String str){
        int length = str.length();
        return str.substring(length -1,length).equals(".") ||
                str.substring(length -1,length).equals("\"") ||
                str.substring(length -1,length).equals("\'") ||
                str.substring(length -1,length).equals("]");
    }
    protected void writeFileWithText(File file, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(file,false);
        fileWriter.write(text);
        fileWriter.flush();
        System.out.println(file.getAbsoluteFile() +" is changed");
    }
    public String[] splitTitle(String str){
        // ..ㅁㅇㅁㅁ124214화 - > [124214,..ㅁㅇㅁㅁ,화]
        String regex = "(\\d+)(?=\\D*$)";  // \d는 숫자를 의미하며, +는 1개 이상의 숫자

        // 정규식 컴파일
        Pattern pattern = Pattern.compile(regex);

        // 매칭을 위한 Matcher 객체 생성
        Matcher matcher = pattern.matcher(str);
        String[] result = new String[3];
        // 숫자가 있는지 확인하고 첫 번째 매칭된 값을 리턴
        if (matcher.find()) {
            result[0] = str.substring(0,str.indexOf(matcher.group()));
            result[1] = matcher.group();
            result[2] = str.substring(str.indexOf(matcher.group()) + matcher.group().length() );
        } else {
            result[0] = "";
            result[1] = "There is no Number";
            result[2] = "";
        }
        return result;
    }
    public String lPad(String str,int size){
        if( str.length() <= size){
            return  "0".repeat(size - str.length())+str;
        }
        return "#".repeat(size);
    }
    public String getPercent(int i, int j){
        float head = (float) i;
        float body = (float) j;
        Float result =   Float.valueOf(Math.round( (head/body) *100000)/ 1000);

        return result.toString();
    }
    public String patternMaker(String originalPattern, String str){
        Pattern pattern = Pattern.compile(originalPattern);
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text.replaceFirst("\\S*=\"","").replaceFirst("\"(.*)","");

    }
}
