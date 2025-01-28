package main.UrlProperty;

import javax.swing.*;
import java.util.Properties;

public enum Property {
    PATH("Download.path"),
    URL("Download.url");
    private final String property;

    Property(String property) {
        this.property = property;
    }
    public String getPropertyKey(){
        return property;
    }
}

