package test.java;

import main.java.file_downloader.TagType;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {

        String regex = "<br\\s*/?>";
        String test= "<br> <br/> s<br /> <sbr /> <br s>";
        System.out.print(test.replaceAll(regex,"\n"));

    }
}
