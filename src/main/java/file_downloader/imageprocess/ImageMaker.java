package main.java.file_downloader.imageprocess;

import main.java.file_downloader.ReadProperty;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.textprocess.ImageType;

import java.io.*;
import java.net.*;

public class ImageMaker {

    private String defaultPath;
    private String fileName;
    private String address;
    private HttpURLConnection connection; // connectionToImage
    private String title;
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

    public ImageMaker(String address, String title, String fileName) {
//        this.address =address;
//        this.fileName = fileName;
        this(address,fileName);
        this.title = title;
        this.defaultPath = new ReadProperty("main/setting.properties")
                .readProperties().getProperty("Download.path") + "/imgDownloader/"
                + title;

    }
    public ImageMaker(String address, String title, String filename, ImageType imageType){
        this(address, title,filename);
    }
    public void make() throws URISyntaxException, NullPointerException, IOException {
        String result;
        File path = new File(defaultPath);
        if(!path.exists()){
            path.mkdirs();
        }
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
                connection.setConnectTimeout(2000);
                connection.setRequestProperty("content-type", "image/" + address.toLowerCase().substring(address.lastIndexOf(".") + 1));
            }
            InputStream in = connection.getInputStream();
            File file = new File(fullfilePath);
            if( ! file.exists()) file.createNewFile();
            int i;
            FileOutputStream fileOutputStream = new FileOutputStream(fullfilePath);
            while ((i = in.read()) != -1) {
                fileOutputStream.write(i);
            }

            fileOutputStream.flush();
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