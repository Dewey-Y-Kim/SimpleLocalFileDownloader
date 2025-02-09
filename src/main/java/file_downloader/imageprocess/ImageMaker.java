package main.java.file_downloader.imageprocess;

import main.java.file_downloader.ReadProperty;
import main.java.file_downloader.connector.ConnectListUrl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.util.List;

public class ImageMaker {

    private String defaultPath;
    private String fileName;
    private String address;
    private HttpURLConnection connection; // connectionToImage
    private String title;

    public ImageMaker(String address, String fileName) {
        this.address = address;
        this.fileName = fileName;
        this.defaultPath = new ReadProperty("main/setting.properties").readProperties().getProperty("Download.path");
        ;
    }

    public ImageMaker(String address, String title, String fileName) {
        this.title = title;
        this.fileName = fileName;
        this.address = address;
        this.defaultPath = new ReadProperty("main/setting.properties")
                .readProperties().getProperty("Download.path") + "/"
                + title;

    }

    public void make() throws URISyntaxException {
        String result;
        File path = new File(defaultPath);
        if(!path.exists()){
            path.mkdirs();
        }
        String fullfilePath = defaultPath+"/"+ fileName;
        try {
            // Image Downloader
            URL url = new URI(address).toURL();


            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);
            connection.setRequestProperty("content-type", "image/png");
            System.out.println("content");
            System.out.println(connection.getContent());
            System.out.println("Message");
            System.out.println(connection.getResponseMessage());
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
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(fileName);
            throw new RuntimeException(e);
        }
    }
}




//            // if you want to get png or jpg ... you can do it
//            String extension = address.substring(address.indexOf('.') + 1);
//
//            BufferedImage image = ImageIO.read(url);
//            File file = new File(defaultPath + fileName);
//
//            ImageIO.write(image, extension, file);
//            result = fileName + " is complete.";
//        } catch (IOException e) {
//            result = fileName + " is failed.";
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//        return result;



