package main.java.file_downloader.fileprocess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public abstract class Report {
    File file;
    File errorList;
    String message;
    String additionalMessage;
    String errortype;

    public Report(String message) throws IOException {
        this.message = message;
    }

    public Report(String message, String errortype, String additionalMessage) throws IOException {
        this(message);
        this.errortype = errortype;
        this.additionalMessage = additionalMessage;
    }

    protected void reportOne() {
        FileWriter fileWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            fileWriter = new FileWriter(file, true);
            fileWriter.write("--------------\n" + message + "\n" + new Date() + "\n" +
                    "--------------\n");
            fileWriter.flush();
            } catch (IOException e) {
            }
        }
        protected void repoartLong () {
            reportOne();

            int idx = 0;

            String filename = "errorMessage";
            try {
                if (additionalMessage.length() > 1000) {

                    filename = "./LargeMessage";
                    errorList = new File(filename + "_" + idx + ".txt");
                    while (errorList.exists()) {
                        idx++;
                        errorList = new File(filename + "_" + idx + ".txt");
                    }
                    errorList.createNewFile();

                } else {
                    errorList = new File(filename + ".txt");
                    if (!errorList.exists()) errorList.createNewFile();
                    if (errortype != null || errortype != "") this.message += errortype + "\n";
                    this.message += additionalMessage;

                    FileWriter errorListWriter = null;
                    errorListWriter = new FileWriter(errorList, true);
                    errorListWriter.write(this.message + "\n-------\n");
                    errorListWriter.flush();
                }
            } catch (IOException e) {
                System.out.println("error\n".repeat(10));
            }
        }
    }
