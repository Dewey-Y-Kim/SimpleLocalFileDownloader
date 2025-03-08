package main.java.file_downloader.domain;

import main.java.file_downloader.textprocess.TextTransform;

public class ResultObj {
    String type;
    String id;
    String episode;
    String title;
    String chapterTitle;
    String path;
    String paddingChaptor;
    public ResultObj(String title, String type,String id, String episode,  String chapter, String endpoint, int size){
        this.title = title;
        this.type = type;
        this.id=id;
        this.episode = episode;
        this.chapterTitle =chapter;
        this.paddingChaptor = new TextTransform().lPad(chapter,size);
        switch (type){
            case "novel": this.path = endpoint + "novelviewlist?list_id="+id+"&episode_id="+episode; break;
            case "webtoon" : this.path = endpoint + "getViewData?webtoonID="+id+"&episodeID="+episode+"&sort=asc";break;
        }
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getEpisode() {
        return episode;
    }

    public String getTitle() {
        return title;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getPath() {
        return path;
    }

    public String getPaddingChaptor() {
        return paddingChaptor;
    }

    @Override
    public String toString() {
        return "ResultObj{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", episode='" + episode + '\'' +
                ", title='" + title + '\'' +
                ", chapterTitle='" + chapterTitle + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
