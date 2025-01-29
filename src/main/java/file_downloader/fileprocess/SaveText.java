package main.java.file_downloader.fileprocess;

import main.java.file_downloader.ReadProperty;

import java.io.*;
import java.util.List;

public class SaveText {
    String bodyText ="";
    String fullPath;
    String defaultPath = setDefaultPath();
    String title;
    boolean MakeNewPath = setMakeNewPath();
//    public SaveText(){
//    }
    public SaveText(String title){
        this.title = title;
        setFullPath();
    }
//    public SaveText(String title, String bodyText){
//        this.fullPath = defaultPath + title +"/" +title +".txt";
//    }

    public SaveText(String title, List<String> bodyString){
        this.title = title;
        this.bodyText = buildString(bodyString);
        setFullPath();
    }
    public SaveText(String title, String chaptor, String bodyText){
        this.fullPath = chkPath(defaultPath + title + "/") + chaptor + ".txt";
        this.bodyText = bodyText;
    }
    public SaveText(String title, String filename,List<String> bodyText){
        title = chkTitle(title);
        filename = chkTitle(filename);
        this.fullPath = chkPath(defaultPath + title+ "/")+filename + ".txt";
        this.bodyText = buildString(bodyText);
    }
    private String setDefaultPath(){
        String title =  new ReadProperty("main/setting.properties").readProperties().getProperty("Download.path");
        if (title.lastIndexOf("/") != title.length()-1 ) title += "/";
        return title;
    }
    private void setFullPath(){
        if(MakeNewPath){
            this.fullPath = chkPath(defaultPath + title+ "/") + title +"/"+ title +".txt";

        } else {
            this.fullPath = defaultPath + title +".txt";
        }

    }
    private Boolean setMakeNewPath(){
        String property = new ReadProperty("main/setting.properties").readProperties().getProperty("Download.makeNewPath").toLowerCase();
        if( property == "true" || property == "yes") return true;
        return false;
    }
    private String chkTitle(String title){
        return  title.replace("/",",");
    }
    private String chkPath(String path){
        if( path.lastIndexOf("/") != path.length() - 1){
            path += "/";
        }
        File foler = new File(path);
        if(!foler.exists()){
            foler.mkdirs();
        }
        return path;
    }
    private String buildString(List<String> bodyText){
        StringBuilder result = new StringBuilder();
        boolean isBody = true;
        for(String str :bodyText){
//            if(str.contains("<br>") || str.contains("<span>")) str = tagRemover(str);
            
            str = tagRemover(str);
            while (str.contains("  ")){
                str = str.replaceAll(" {2}"," ");
            }
            while (str.contains("\t")){
                str = str.replaceAll("\t"," ");
            }
            result.append(str).append("\n");
            
        }
        return result.toString();
    }

    public void appendText( List<String> text){
            this.bodyText += buildString(text);
    }
    public void save(){

        // 1. 파일 객체 생성
        File directory;
        if(MakeNewPath){
            directory = new File(defaultPath+title);

        } else{
            directory = new File(defaultPath);
        }

        if (!directory.exists()) directory.mkdirs();
        File file = new File(fullPath);
        // 2. 파일 존재여부 체크 및 생성
        try {
            System.out.println(fullPath + " is starting");
            if (!file.exists()) {
                    file.createNewFile();
            }

            // 3. Buffer를 사용해서 File에 write할 수 있는 BufferedWriter 생성
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            // 4. 파일에 쓰기
            writer.write(bodyText);
            // 5. BufferedWriter close
            writer.close();
            System.out.println(fullPath + " is complete");
        } catch (IOException e) {
            System.out.println("Error occor  " + fullPath);
            throw new RuntimeException(e);
        }
    }
    public String tagRemover(String tag) {
        String str = tag
//                .replaceAll("<br>", "\n")
//                .replaceAll("<br />","\n")
//                .replaceAll("<br/>","\n")
                .replaceAll("<br\\s*/?>","\n")
                .replaceAll("<span>","")
                .replaceAll("&gt;",">")
                .replaceAll("&lt;","<")
                .replaceAll("</span>","");
//                .replaceAll("<(.*?)>", "");
        return str;
    }
}
