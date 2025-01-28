package main.UrlProperty;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SettingProperty extends setProperty{

    public String getPropertyPath(){
        return propertiesPath;
    }
    @Override
    public void setDefaultPath(String path) {
        changeProperty(Property.PATH.getPropertyKey(), path);

    }

    @Override
    public String getDefaultPath() {
        return defaultProperties().getProperty(Property.PATH.getPropertyKey());
    }

    @Override
    public void setDefaultUrl(String url) {
        changeProperty(Property.URL.getPropertyKey(), url);
    }

    @Override
    public String getDefaultUrl() {
        return defaultProperties().getProperty(Property.URL.getPropertyKey());
    }
    public void changeProperty(String key, String value){

        Properties properties = defaultProperties();
        properties.setProperty(key,value);
        try {
            properties.store(new FileWriter(getPropertiesPath()),"Change Default "+key+" at " + new Date().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
