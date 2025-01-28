package main.UrlProperty;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class setProperty {
    public final String propertiesPath = "src/main/setting.properties";
    protected String getPropertiesPath(){
        return propertiesPath;
    }
    protected Properties defaultProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(propertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return properties;
    }

    public abstract void setDefaultPath(String path);
    public abstract String getDefaultPath();
    public abstract void setDefaultUrl(String url);
    public abstract String getDefaultUrl();

}
