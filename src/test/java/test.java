package test.java;

import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.textprocess.TextTransform;
import org.junit.Test;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] ag){
        Iterator agIter = Arrays.stream(ag).iterator();
        while (agIter.hasNext()){
            System.out.println(agIter.next().getClass().getName());
        }
    }
    @Test
    public void removePtag(){
        String str = "<p> adga </p>";
        System.out.println(str.replaceAll("<(/?)p>","\n").replaceAll("\n\n","\n"));
    }
    @Test
    public void test(){
        Connector connector ;
        InputStream inputStream;
            try {
                connector = new Connector("https://t60.hoduhodu.com/webtoondata/4803/img/1/image31.jpg");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            try {
        if(connector != null ){
                inputStream = connector.getInputstream();
                byte[] bytes = new byte[1024];

        }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    @Test
    public void test2() throws IOException {

    }
}
