package main.java.file_downloader.fileprocess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ReportError {
    File file = new File("./ErrorOccurin.txt");
    File errorList;
    public ReportError(String error) throws IOException {
        if(!file.exists()) file.createNewFile();
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write(error+"\n"+new Date()+"\n--------------\n");
        fileWriter.flush();

    }
    public ReportError(String str, String error, String original) throws IOException {
        this(error);
        int idx = 0;
        String filename ="errorMessage";
        if(original.length() >1000){
            filename="./LargeMessage";
            errorList = new File(filename +"_" + idx +".txt");
            while(errorList.exists()){
                idx++;
                errorList = new File(filename+"_" + idx + ".txt");
            }
        } else{
            errorList = new File(filename+".txt");
        }
        String message ="";

        if (str!=null||str != "") message+=str+"\n";
                message+=errorList+"\n"+original;
        FileWriter errorListWriter = new FileWriter( message,true);
        errorListWriter.write(original+"\n-------\n");
        errorListWriter.flush();
    }
}
