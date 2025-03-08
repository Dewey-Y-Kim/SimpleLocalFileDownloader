package main.java.file_downloader.domain;

public class Img {
    String path;
    String title;
    String webtoonId;
    String chapter;
    int idx;
    String filename;
    int attampt = 0;
    public Img(String title, String chapter, String path,int idx, String filename){
        this.title = title;
        this.chapter = chapter;
        this.path = path;
        this.idx = idx;
        this.filename = filename;

    }
    Img(String title, String chapter, String webtoonId, String list_episode,String path, int idx, String filename){
        this(title, chapter, path, idx, filename);
        this.chapter=list_episode;
        this.webtoonId = webtoonId;
    }
    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public String getChapter() {
        return chapter;
    }
    public String getWebtoonId() {
        return webtoonId;
    }

    public String getFilename() {
        return filename;
    }

    public int getAttampt() {
        return attampt;
    }

    public void setAttampt(int attampt) {
        this.attampt = attampt;
    }

    @Override
    public String toString() {
        return "Img{" +
                "path='" + path + '\'' + "\n" +
                ", title='" + title + '\'' + "\n" +
                ", webtoonId='" + webtoonId + '\'' + "\n" +
                ", chapter='" + chapter + '\'' + "\n" +
                '}';
    }
}
