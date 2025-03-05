package main.java.file_downloader.imageprocess;

import main.java.file_downloader.ReadProperty;
import main.java.file_downloader.fileprocess.ControlFile;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.textprocess.ImageType;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class ImageMaker {

    private final int MAX_ATTAMPT = Integer.parseInt(new ReadProperty("main/setting.properties").readProperties().getProperty("MaxAttampt"));
    private String defaultPath;
    private String fileName;
    private String address;
    private HttpURLConnection connection; // connectionToImage
    private String title;
    private String chapter;
    private boolean retry = false;
    private int attempt = 0;
    public ImageMaker(String address, String fileName) {
        this.address = address;
        this.fileName = fileName;
        this.defaultPath = new ReadProperty("main/setting.properties").readProperties().getProperty("Download.path") + "imgDownloader/";
//        String type = address.substring(address.lastIndexOf("."));
    }

    public ImageMaker(String address, String chapter, String fileName) {
//        this.address =address;
//        this.fileName = fileName;
        this(address, fileName);
        this.chapter = chapter;
        this.defaultPath = new ReadProperty("main/setting.properties")
                .readProperties().getProperty("Download.path") + "/imgDownloader/"
                + chapter;

    }

    public ImageMaker(String address, String title, String chapter, String filename) {
        this(address, chapter, filename);
        this.title = title;
        this.defaultPath = new ReadProperty("main/setting.properties")
                .readProperties().getProperty("Download.path") + "/imgDownloader/"
                + title + "/" + chapter;
    }

    public ImageMaker(String address, String title, String filename, ImageType imageType) {
        this(address, title, filename);
    }

    public int make() throws IOException, InterruptedException {

        attempt++;
        new ControlFile(defaultPath).chkpath();
            String ext = address.toLowerCase().substring(address.lastIndexOf(".")); //.jpg
            String fullfilePath = defaultPath + "/" + fileName + ext;
            fullfilePath.replaceAll("//", "/");
            try {
                URL url;
                url = new URI(address).toURL();
                int attampt = 0;
                while (attampt < MAX_ATTAMPT && (connection == null || connection.getResponseCode() != 200)) {
                    attampt++;
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);

                    connection.setRequestProperty("content-type", "image/" + address.toLowerCase().substring(address.lastIndexOf(".") + 1));
                }
                InputStream inputStream = connection.getInputStream();

                // 파일 생성
//                ControlFile controlFile = new ControlFile(fullfilePath, inputStream, retry);
                ControlFile controlFile = new ControlFile(defaultPath,fileName,ext, inputStream, retry);

                int attempt = 0;

                controlFile.createFile();
            } catch (NullPointerException e){
//                Thread.sleep(2000);
                return reportThis(e, fileName, address, fullfilePath);
            } catch (FileNotFoundException e) {
                return reportThis(e, fileName, address, fullfilePath);
            } catch (ProtocolException e) {
                return reportThis(e, fileName, address, fullfilePath);
            } catch (MalformedURLException e) {
                return reportThis(e, fileName, address, fullfilePath);
            } catch (URISyntaxException e){
                return reportThis(e, fileName, address, fullfilePath);
            } catch (IOException e) {
                return reportThis(e, fileName, address, fullfilePath);
//                System.out.printf("[FileWriteError] Error occurs in %s ", fileName);
            }
            setDefault();
            return 1;
    }
    private int reportThis(Exception e, String fileName, String address,String fullfilePath) {
        try {
            if (attempt > 100) {
                new ReportError(new Object() {
                }.getClass().getEnclosingClass().getName(), e.getClass().getName()  , "\n[FileWriteError] \n" + fileName + "\n"+address);
                setDefault();
                return -1;
            }
            this.retry = true;

            // recursive
            make();
        } catch (IOException | InterruptedException err){

        }
        setDefault();
        return -1;
    }
    private void setDefault(){
        attempt =0;
        retry = false;
    }

    enum TYPE {
        jpeg(),

    }

}