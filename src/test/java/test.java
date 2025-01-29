package test.java;

import main.Ui.Uitest;
import main.java.file_downloader.ReadProperty;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class test {

    public void main(String[] args) throws IOException {
//        Uitest uitest = new Uitest();
        String path =  new ReadProperty("main/setting.properties").readProperties().getProperty("Download.makeNewPath");
        System.out.println(path);
    } 
}
