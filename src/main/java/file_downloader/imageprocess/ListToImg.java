package main.java.file_downloader.imageprocess;

import main.java.file_downloader.domain.Img;
import main.java.file_downloader.fileprocess.ReportCheckList;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.textprocess.TextTransform;

import java.io.IOException;
import java.util.Deque;
import java.util.Queue;
import java.util.UnknownFormatConversionException;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class ListToImg extends Thread{
    Deque original;
    Integer index = 0;
    Float lastPercent = (float) -1.0;
    int complete = 0;
    int total = 0;
    int success =0;
    int paddingNumber = 0;
    public ListToImg(Queue<Img> queue, Integer index, Float lastPercent, int total, int paddingNumber){
        this.original = (Deque) queue;
        this.index = index;
        this.lastPercent = lastPercent;
        this.total = total;
        this.paddingNumber=paddingNumber;

    }
    @Override
    public void run() {
        Long startTime = System.currentTimeMillis();
        int toTry =0;
        ///  make it Tread
        Object tmp = null;
        int originalSize= original.size();
        String errorFile="";
        String errorAddress="";
        Object object =null;
        int error = 0;
        int index = 0;
        int attampt = 0;
        int sizeLength = String.valueOf(originalSize).length();
        String title ="";
        while ((tmp = original.pollLast()) != null){
            toTry ++;
            object = tmp;
            Img obj = (Img) tmp;
            obj.setAttampt(obj.getAttampt()+1);
            title = obj.getTitle();
            String chapter = obj.getChapter();
            String filename = obj.getFilename();
            String path = obj.getPath();

            errorAddress = path;
            index ++;
            Float percent = (float) ((Math.round((float) index / total * 1000))/1000)*100;
            ImageMaker imageMaker = null;

            try {
                imageMaker = new ImageMaker(path, title, new TextTransform().lPad(chapter, paddingNumber), filename);
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
                    error ++;
                    reportErr(e, errorFile, errorAddress);
            }

            if(result==-1){

                if(obj.getAttampt()<20) {
                    original.addFirst(obj);
                } else{
                    try {
                        new ReportCheckList("[Error image load attampt over 10 times]\n+error point : " + title + chapter + "/" + filename + "\n" + "address :" + imageMaker.getAddress());
                    } catch (IOException e) {
//                    throw new RuntimeException(e);
                    }
                }
                index --;
                System.out.println("-".repeat(10)+"\ncode : not maked\nerror point : "+ title + chapter+ "/"+filename+"\n"+"address :"+imageMaker.getAddress()+"\n"+"-".repeat(10));

            }
            if(result == 1) {
                System.out.printf("complete making %s\t%s\t%s (remains : %d )\n ",title, chapter, filename, original.size(), percent.toString()+"%" );
                lastPercent = percent;
                success ++;
            }

        }
        try {
            new ReportCheckList(title);
        } catch (IOException e) {
//            throw new RuntimeException(e);
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
