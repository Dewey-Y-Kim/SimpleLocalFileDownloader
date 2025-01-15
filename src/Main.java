import FIleDownloader.ConnectListUrl;
import FIleDownloader.Downloader;

import java.io.IOException;
import java.net.HttpURLConnection;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        // 목록 화면에서 Url 입력
        String url = "";

        Downloader downloader = new Downloader(url);
        downloader.mainController();
    }
}