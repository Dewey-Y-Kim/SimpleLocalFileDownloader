package main.java.file_downloader.domain;

public class TextData {
    String title;
    String epsode;
    String chapter;
    String data;
    public TextData(String title, String epsode, String chapter, String data){
        this.title = title;
        this.epsode = epsode;
        this.chapter = chapter;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getEpsode() {
        return epsode;
    }

    public String getChapter() {
        return chapter;
    }

    public String getData() {
        return data;
    }
}
