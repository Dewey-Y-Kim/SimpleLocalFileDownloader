package main.java.file_downloader.domain;

public class ImgList {
//    String path;
    String title;
    String webtoonId;
    String chapter;
    String list_episode;
//    String[] list;
public ImgList(String title, String chapter, String listId, String list_episode){
        this.title = title;
        this.chapter = chapter;
        this.webtoonId = listId;
        this.list_episode =list_episode;
    }

    public String getTitle() {
        return title;
    }

    public String getWebtoonId() {
        return webtoonId;
    }

    @Override
    public String toString() {
        return "ImgList{" +
                "title='" + title + '\'' +
                ", webtoonId='" + webtoonId + '\'' +
                ", chapter='" + chapter + '\'' +
                ", list_episode='" + list_episode + '\'' +
                '}';
    }
}
