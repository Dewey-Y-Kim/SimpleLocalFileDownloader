package main.java.file_downloader.imageprocess;

import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.responseprocess.ListObj;
import main.java.file_downloader.responseprocess.SplitLiTag;
import main.java.file_downloader.textprocess.GetValueByVarName;
import main.java.file_downloader.textprocess.TextTransform;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.*;

public interface ImageProcesser {
    String address = "";

    public List makeImageList() throws IOException ;
    private String readFulllist(){
        return readFullList(null);
    }

    private String readFullList(String splitCode) {
        return null;
    }

    public void makeImg(List source);

}
