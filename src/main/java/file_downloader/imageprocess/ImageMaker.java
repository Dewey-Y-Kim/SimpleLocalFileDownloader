package main.java.file_downloader.imageprocess;

import main.java.file_downloader.ReadProperty;
import main.java.file_downloader.fileprocess.ControlFile;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.textprocess.ImageType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class ImageMaker {

    private String defaultPath;
    private String fileName;
    private String address;
    private HttpURLConnection connection; // connectionToImage
    private String title;
    private String chapter;
    private final int MAX_ATTAMPT = Integer.parseInt(new ReadProperty("main/setting.properties").readProperties().getProperty("MaxAttampt"));

    enum TYPE{
        jpeg(),

    }
    public ImageMaker(String address, String fileName) {
        this.address = address;
        this.fileName = fileName;
        this.defaultPath = new ReadProperty("main/setting.properties").readProperties().getProperty("Download.path")+ "imgDownloader/";
//        String type = address.substring(address.lastIndexOf("."));
    }

    public ImageMaker(String address, String chapter, String fileName) {
//        this.address =address;
//        this.fileName = fileName;
        this(address,fileName);
        this.chapter = chapter;
        this.defaultPath = new ReadProperty("main/setting.properties")
                .readProperties().getProperty("Download.path") + "/imgDownloader/"
                + chapter;

    }
    public ImageMaker(String address, String title, String chapter, String filename){
        this(address, chapter,filename);
        this.title = title;
        this.defaultPath = new ReadProperty("main/setting.properties")
                .readProperties().getProperty("Download.path") + "/imgDownloader/"
                + title +"/"+chapter;
    }
    public ImageMaker(String address, String title, String filename, ImageType imageType){
        this(address, title,filename);
    }
    public void make() throws URISyntaxException, NullPointerException, IOException {
        new ControlFile(defaultPath).chkpath();

        String ext = address.toLowerCase().substring(address.lastIndexOf(".")); //.jpg
        String fullfilePath = defaultPath+"/"+ fileName + ext;
        fullfilePath.replaceAll("//","/");
        try {
            URL url;
            url = new URI(address).toURL();
            int attampt =0;
            while( attampt < MAX_ATTAMPT && (connection ==null || connection.getResponseCode() != 200)) {
                attampt ++;
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setRequestProperty("content-type", "image/" + address.toLowerCase().substring(address.lastIndexOf(".") + 1));
            }
            InputStream inputStream = connection.getInputStream();

            // 파일 생성
            new ControlFile(fullfilePath, inputStream  ).createFile();


        } catch (FileNotFoundException e) {
            new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),e.getClass().getName()+"\n" + fileName,address);
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),e.getClass().getName()+"\n" + fileName,address);
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),e.getClass().getName()+"\n" + fileName,address);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.printf("[FileWriteError] Error occurs in %s ",fileName);
            new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),e.getClass().getName()+"\n" + fileName+"\n"+address+"\n",address);
            throw new RuntimeException(e);
        }
    }

}