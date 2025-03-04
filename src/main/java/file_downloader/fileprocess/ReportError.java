package main.java.file_downloader.fileprocess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ReportError {
    File file = new File("./errorMessage.txt");
    File errorList;
    public ReportError(String message) throws IOException {
        if(!file.exists()) file.createNewFile();
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write(message+"\n"+new Date()+"\n--------------\n");
        fileWriter.flush();

    }
    public ReportError(String str, String errortype, String additionalMessage) throws IOException {
        this(errortype);
        int idx = 0;
        String filename ="errorMessage";
        if(additionalMessage.length() >1000){
            filename="./LargeMessage";
            errorList = new File(filename +"_" + idx +".txt");
            while(errorList.exists()){
                idx++;
                errorList = new File(filename+"_" + idx + ".txt");
            }
            errorList.createNewFile();
        } else{
            errorList = new File(filename+".txt");
            errorList.createNewFile();
        }
        String message ="";

        if (str!=null||str != "") message+=str+"\n";
                message+=additionalMessage;

        FileWriter errorListWriter = new FileWriter( errorList,true);
        errorListWriter.write(message + "\n-------\n");
        errorListWriter.flush();
    }
}
