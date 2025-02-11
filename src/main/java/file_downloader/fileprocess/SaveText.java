package main.java.file_downloader.fileprocess;

import main.java.file_downloader.ReadProperty;
import main.java.file_downloader.textprocess.TextTransform;

import java.io.*;
import java.util.List;

public class SaveText {
    String bodyText ="";
    String fullPath;
    String defaultPath = setDefaultPath();
    String title;
    boolean makeNewPath = setMakeNewPath();
//    public SaveText(){
//    }
    public SaveText(String title){
        this.title = title;
        defaultPath = "./";
        setFullPath();
    }
//    public SaveText(String title, String bodyText){
//        this.fullPath = defaultPath + title +"/" +title +".txt";
//    }
    public SaveText(String title, String[] list){
        this.title = title;
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : list){
            stringBuilder.append(str);
            stringBuilder.append("\n");
        }
        this.bodyText = stringBuilder.toString();
        this.fullPath = defaultPath + title + ".txt";

    }
    public SaveText(String path, String title, String[] list){
        this(title,list);
        this.defaultPath =path;
        this.fullPath = chkPath(defaultPath+title)+ title +".txt";
    }
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

    public SaveText() {

    }

    private String setDefaultPath(){
        String propertyPath =  new ReadProperty("main/setting.properties").readProperties().getProperty("Download.path");
        if (propertyPath.lastIndexOf("/") != propertyPath.length()-1 ) propertyPath += "/";
        return propertyPath;
    }
    private void setFullPath(){
        if(makeNewPath){
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
            
            str = new TextTransform().tagRemover(str);
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
        if(makeNewPath){
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

    public String divTop (String tag) {
        return tag.replaceAll("<div>", "<p>")
                .replaceAll("</div>", "</p>");
    }
    public String divToLineChange(String tag){
        return tag.replaceAll("<div>", "")
                .replaceAll("</div>", "\n");
    }
}
