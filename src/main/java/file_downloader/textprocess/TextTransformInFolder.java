package main.java.file_downloader.textprocess;

import main.java.file_downloader.fileprocess.SaveText;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TextTransformInFolder {
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
            SaveText saveText = new SaveText();
            text = saveText.tagRemover(text).trim();
            // save file as same name
            writeFileWithText(file, text);
        }
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
}
