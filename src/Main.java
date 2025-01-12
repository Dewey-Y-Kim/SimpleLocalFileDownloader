import FIleDownloader.ConnectListUrl;

import java.io.IOException;
import java.net.HttpURLConnection;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
//        HttpURLConnection connect = ConnectListUrl.openConnect("https://cjy6179.tistory.com/32");
        HttpURLConnection connect = ConnectListUrl.openConnect("https://newtoki.biz/book/20270");
        ConnectListUrl.readUrl(connect);
        connect.disconnect();
    }
}