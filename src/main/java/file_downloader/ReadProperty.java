package main.java.file_downloader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {
    String propertyPath;
    public ReadProperty(String propertyPath){
        this.propertyPath = propertyPath;
    }
    public Properties readProperties(){
        Properties downloadProperty = new Properties();
        InputStream downloadInputStream = getClass().getClassLoader().getResourceAsStream(propertyPath);
        try {
            if(downloadProperty != null){
                downloadProperty.load(downloadInputStream);
                return downloadProperty;
            } else{
                throw new FileNotFoundException("There is no " + propertyPath + ".");
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
