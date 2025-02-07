package main.java.file_downloader.imageprocess;

import main.java.file_downloader.ReadProperty;
import main.java.file_downloader.connector.ConnectListUrl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;

public class ImageMaker{

    private  String defaultPath;
    private  String fileName;
    private String address;
    private HttpURLConnection connection; // connectionToImage

    ImageMaker(String address, String fileName){
        this.address = address;
        this.fileName = fileName;
        this.defaultPath = new ReadProperty("main/setting.properties").readProperties().getProperty("Download.path");;
    }

    ImageMaker(String address, String title, String fileName){
        this.fileName = fileName;
        this.address = address;
        this.defaultPath = new ReadProperty("main/setting.properties")
                .readProperties().getProperty("Download.path")
                + title;

    }

    public String make(){
        String result;
        try {
            // Image Downloader
            String address = "";
            String filePath = "";
            URL url = new URI(address).toURL();


            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);
            connection.setRequestProperty("content-type","image/jpeg");

            InputStream in = connection.getInputStream();
            int i;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            while((i = in.read()) != -1){
                fileOutputStream.write(i);
            }

            fileOutputStream.flush();

            // if you want to get png or jpg ... you can do it
            String extension = address.substring(address.indexOf('.') + 1);

            BufferedImage image = ImageIO.read(url);
            File file = new File(defaultPath + fileName);

            ImageIO.write(image, extension, file);
            result = fileName + " is complete.";
        } catch (IOException e) {
            result = fileName + " is failed.";
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
