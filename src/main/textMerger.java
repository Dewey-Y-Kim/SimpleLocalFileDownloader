package main;

import main.java.file_downloader.textprocess.TextMerger;

import java.io.IOException;

public class textMerger {
    public static void main(String[] args) throws IOException {
        for(int idx =0; idx < args.length; idx++){
            new TextMerger(args[idx]);
        }
    }
}
