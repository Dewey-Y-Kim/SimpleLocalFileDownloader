import FIleDownloader.ConnectListUrl;

import java.io.IOException;
import java.net.HttpURLConnection;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
//        HttpURLConnection connect = ConnectListUrl.openConnect("***");
        HttpURLConnection connect = ConnectListUrl.openConnect("****");
        ConnectListUrl.readUrl(connect);
        connect.disconnect();
    }
}