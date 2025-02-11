package main.java.file_downloader.imageprocess;

import main.java.file_downloader.ReadProperty;
import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.textprocess.ImageType;

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

    enum TYPE{
        jpeg(),

    }
    public ImageMaker(String address, String fileName) {
        this.address = address;
        this.fileName = fileName;
        this.defaultPath = new ReadProperty("main/setting.properties").readProperties().getProperty("Download.path");
//        String type = address.substring(address.lastIndexOf("."));
    }

    public ImageMaker(String address, String title, String fileName) {
        this(address,fileName);
        this.title = title;
        this.defaultPath = new ReadProperty("main/setting.properties")
                .readProperties().getProperty("Download.path") + "/"
                + title;

    }
    public ImageMaker(String address, String title, String filename, ImageType imageType){
        this(address, title,filename);
    }
    public void make() throws URISyntaxException {
        String result;
        File path = new File(defaultPath);
        if(!path.exists()){
            path.mkdirs();
        }

        String fullfilePath = defaultPath+"/"+ fileName + address.toLowerCase().substring(address.lastIndexOf("."));
        try {
            // Image Downloader
            URL url = new URI(address).toURL();


            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);
            connection.setRequestProperty("content-type", "image/"+address.toLowerCase().substring(address.lastIndexOf(".")+1));

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
            System.out.printf("Error occurs in %s ",fileName);
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



