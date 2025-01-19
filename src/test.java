import FIleDownloader.ConnectListUrl;
import FIleDownloader.SaveText;

import java.io.IOException;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
        ConnectListUrl connectListUrl = new ConnectListUrl("");
        List<String> urlResult = connectListUrl.openConnect();
        SaveText saveText = new SaveText("sakamotoDays", urlResult);
        saveText.save();

    }
}
