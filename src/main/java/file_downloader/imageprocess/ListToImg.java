package main.java.file_downloader.imageprocess;

import main.java.file_downloader.domain.Img;
import main.java.file_downloader.fileprocess.ReportError;

import java.io.IOException;
import java.util.Deque;
import java.util.Queue;

public class ListToImg extends Thread{
    Deque original;
    Integer index = 0;
    Float lastPercent = (float) -1.0;
    int total = 0;
    public ListToImg(Queue queue,Integer index, Float lastPercent, int total){
        this.original = (Deque) queue;
        this.index = index;
        this.lastPercent = lastPercent;
        this.total = total;
    }
    @Override
    public void run() {
        ///  make it Tread
        Object tmp = null;
        int originalSize= original.size();
        String errorFile="";
        String errorAddress="";
        while ((tmp = original.pollLast()) != null){
            index = total - original.size();
            Img obj = (Img) tmp;
            String title = obj.getTitle();
            String chapter = obj.getChapter();
            String filename =  obj.getFilename();
            String path = obj.getPath();

            errorAddress = path;
            Float percent = (float) ((Math.round((float) index / total * 1000))/1000)*100;
            try{
                new ImageMaker(path, title, chapter, filename).make();
            } catch (Exception e){
                try {
                    new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),e.getClass().getName()+"\n" + errorFile,errorAddress);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(percent > lastPercent) {
                System.out.printf("complete making %s (%d / %d) %s\n ",title, total, total, percent.toString()+"%" );
                lastPercent = percent;
            } else{
                System.out.printf("complete making %s (%d / %d) \n ",title, index, total);
            }
        }

        super.interrupt();
        ///
    }
}
