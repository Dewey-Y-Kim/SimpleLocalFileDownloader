package main.java.file_downloader.fileprocess;

import java.io.*;

public class ControlFile {

    File file = null;
    String directoryPath = null;
    String fullfilePath = null;
    InputStream in = null;
    public ControlFile(String path){
        this.directoryPath = path;
    }
    public ControlFile(String fullfilePath, InputStream in){
        this.file = new File(fullfilePath);
        this.fullfilePath =fullfilePath;
        this.in=in;
    }
    public int createFile() throws IOException {
        if( ! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return 0;
                // 최종 결과 확인을 위해. 만들계획은 없음.
            }
        }
        int i;
        FileOutputStream fileOutputStream = new FileOutputStream(fullfilePath);
        while (true) {
            try {
                if (!((i = in.read()) != -1)) break;
            } catch (IOException e) {
                new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),
                        e.getClass().getName()+"\n" + "[Create File]",
                        fullfilePath);
//                throw new RuntimeException(e);
                return 0;
            }
            fileOutputStream.write(i);
        }

        fileOutputStream.flush();
        return 1;
    }
    public void chkpath(){
        // chk folder
        File path = new File(directoryPath);
        if(!path.exists()){
            path.mkdirs();
        }
    }
}
