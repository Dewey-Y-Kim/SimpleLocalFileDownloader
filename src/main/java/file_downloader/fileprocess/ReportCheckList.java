package main.java.file_downloader.fileprocess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ReportCheckList extends Report{

    public ReportCheckList(String message) throws IOException {
        super(message);
        this.file = new File("./chkList.txt");
        reportOne();
    }

    public ReportCheckList(String str, String errortype, String additionalMessage) throws IOException {
        super(str, errortype, additionalMessage);
        this.file = new File("./chkList.txt");
    }
}
