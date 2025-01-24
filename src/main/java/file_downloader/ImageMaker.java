package main.java.file_downloader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageMaker{
    private  String defaultPath;
    private  String fileName;
    private String address;

    ImageMaker(String address, String fileName){
        this.address = address;
        this.fileName = fileName;
        this.defaultPath = "/";
    }

    ImageMaker(String address, String defaultPath, String fileName){
        this.defaultPath = defaultPath;
        this.fileName = fileName;
        this.address = address;
    }

    public String make(){
        String result;
        try {
            // if you want to get png or jpg ... you can do it
            URL url = new URL(address);
            String extension = address.substring(address.indexOf('.') + 1);

            BufferedImage image = ImageIO.read(url);
            File file = new File(defaultPath + fileName);

            ImageIO.write(image, extension, file);
            result = fileName + " is complete.";
        } catch (IOException e) {
            result = fileName + " is failed.";
            e.printStackTrace();
        }
        return result;
    }

}
