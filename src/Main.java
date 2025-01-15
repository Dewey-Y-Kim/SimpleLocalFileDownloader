import FIleDownloader.ConnectListUrl;
import FIleDownloader.Downloader;

import java.io.IOException;
import java.net.HttpURLConnection;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        // 목록 화면에서 Url 입력
        String url = "";
        // 다운로드 받을 장소는 FileDownloader.Downloader.downLoadPath

        Downloader downloader = new Downloader(url);
        downloader.mainController();
    }
}