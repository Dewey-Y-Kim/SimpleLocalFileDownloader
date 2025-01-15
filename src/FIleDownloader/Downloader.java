package FIleDownloader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Downloader {
    private String address;
    public Downloader(String address) {
        this.address = address;
    }

    public void mainController() throws IOException {
        ConnectListUrl connectListUrl = new ConnectListUrl(address);
        GetBody titleList = new GetBody(connectListUrl.getResult());
        List list =titleList.getResult();
        for (int idx = 0; idx< list.size() / 2 ; idx++) {
            String smallUrl = (String) list.get(idx * 2 + 1);
            String smallTitle = (String) list.get(idx * 2);
            ConnectListUrl tempConnect = new ConnectListUrl(smallUrl);
//            try{
//                Thread.sleep((long) Math.round((Math.random()* 1000)));
            GetBody temp = new GetBody(tempConnect.getResult(), smallTitle);
//        System.out.println(temp == titleList);
            SaveText saveText = new SaveText(titleList.getTitle(), temp.getTitle(), temp.getResult());
            saveText.save();
        }
    }
}
