package FIleDownloader;

import java.io.*;
import java.util.List;

import static java.io.IO.println;

public class SaveText {
    String bodyText;
    String fullPath;
    String defaultPath = new Downloader().downLoadPath;
//    public SaveText(){
//    }
    public SaveText(String sb){
        this.bodyText = sb;
        this.fullPath = "/home/dewey/connectUrl.html";
    }
    public SaveText(String title, String bodyText){
        this.fullPath = defaultPath + title;
        this.bodyText = bodyText;
    }
    public SaveText(String title, List<String> bodyString){
        this.fullPath = defaultPath + title;
        this.bodyText = buildString(bodyString);

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
        for(String str :bodyText){
            if(str.contains("<br>") || str.contains("<span>")) str = TagRemover(str);
            result.append(str).append("\n");
        }
        return result.toString();
    }
    public void save(){

//        //Text 파일 저장
        try {
             System.out.println(fullPath + " is starting");

            OutputStream outputStream = new FileOutputStream(fullPath);
            byte[] bytes = bodyText.getBytes();
            outputStream.write(bytes);
            outputStream.close();
            System.out.println(fullPath + " is complete");
        } catch (IOException e) {
            System.out.println("Error occor  " + fullPath);

            e.printStackTrace();
        }
//        // 1. 파일 객체 생성
//        File file = new File(fullPath);
//        // 2. 파일 존재여부 체크 및 생성
//        try {
//            System.out.println(fullPath + " is starting");
//            if (!file.exists()) {
//                    file.createNewFile();
//            }
//            // 3. Buffer를 사용해서 File에 write할 수 있는 BufferedWriter 생성
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter writer = new BufferedWriter(fw);
//            // 4. 파일에 쓰기
//            writer.write(bodyText);
//            // 5. BufferedWriter close
//            writer.close();
//            System.out.println(fullPath + " is complete");
//        } catch (IOException e) {
//            System.out.println("Error occor  " + fullPath);
//            throw new RuntimeException(e);
//        }


    }
    public String TagRemover(String tag) {
        String str = tag
                .replaceAll("<br>", "\n")
                .replaceAll("<br />","\n")
                .replaceAll("<br/>","\n")
                .replaceAll("<span>","")
                .replaceAll("</span>","");
//                .replaceAll("<(.*?)>", "");
        return str;
    }
}
