package main.java.file_downloader.imageprocess;

import main.java.file_downloader.domain.Img;
import main.java.file_downloader.fileprocess.ReportError;

import java.io.IOException;
import java.util.Deque;
import java.util.Queue;
import java.util.UnknownFormatConversionException;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class ListToImg extends Thread{
    Deque original;
    Integer index = 0;
    Float lastPercent = (float) -1.0;
    int total = 0;
    public ListToImg(Queue<Img> queue,Integer index, Float lastPercent, int total){
        this.original = (Deque) queue;
        this.index = index;
        this.lastPercent = lastPercent;
        this.total = total;
    }
    @Override
    public void run() {
        int toTry =0;
        ///  make it Tread
        Object tmp = null;
        int originalSize= original.size();
        String errorFile="";
        String errorAddress="";
        Object object =null;
        int error = 0;
        int success = 0;
        int index = 0;
        int attampt = 0;
        while ((tmp = original.pollLast()) != null){
            object = tmp;
            Img obj = (Img) tmp;
            String title = obj.getTitle();
            String chapter = obj.getChapter();
            String filename = obj.getFilename();
            String path = obj.getPath();
            errorAddress = path;
            index ++;
            Float percent = (float) ((Math.round((float) index / total * 1000))/1000)*100;
            ImageMaker imageMaker = null;

            try {
                imageMaker = new ImageMaker(path, title, chapter, filename);
            }catch (UnknownFormatConversionException e){
                try {
                    new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),e.getClass().getName()+"\n" + errorFile,"[new ImageMaker(path, title, chapter, filename)]\n"+e.getClass().getName()+"\n" + errorFile +"\n\n"+errorAddress);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            int result = 0;
            try{
                result=imageMaker.make();
            }  catch (Exception e){
//                    System.out.println( object);
                    error ++;
                    reportErr(e, errorFile, errorAddress);
            }

            if(result==-1){
//                imageMaker.delete();
                original.addFirst(tmp);
                total = original.size();
                index --;
            }
            if(percent > lastPercent) {
                System.out.printf("code : %d\tcomplete making %s,%s (%d / %d) %s\n ",result,title, chapter, index, total, percent.toString()+"%" );
                lastPercent = percent;
            } else{
                System.out.printf("code : %d\tcomplete making %s,%s (%d / %d) \n ",result, title, chapter, index, total);
            }
            if(result==-1) System.out.println("\n\ncode : -1\nattampt : "+ chapter+ "/"+filename);
            if(result==1) success ++;
        }
        super.interrupt();
        ///
    }

    private void reportErr(Exception e, String errorFile, String errorAddress){
        try{
        new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),e.getClass().getName(),"[ imageMaker.make()]\n" + errorFile +"\n\n"+errorAddress);

        } catch(IOException err){

        }
    }
}
