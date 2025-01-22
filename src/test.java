import FIleDownloader.SaveText;
import FIleDownloader.TagGetter.TagGetter;
import FIleDownloader.TagGetter.TagType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
//      `  ConnectListUrl connectListUrl = new ConnectListUrl("");
//        List<String> urlResult = connectListUrl.openConnect();

//        SaveText saveText = new SaveText("saka", urlResult);
//        saveText.save();

//        ConnectListUrl connectListUrl = new ConnectListUrl("");
//        List<String> urlResult = connectListUrl.openConnect();

        String targetId = "comic-episode-list";
        String originalDoc = "/home/dewey/Downloads/books/saka.html";
        File file = new File(originalDoc);
        BufferedReader bufferedReader = new BufferedReader( new FileReader( file ));
        String str;
        List<String> urlResult = new ArrayList<>();
        while ((str= bufferedReader.readLine()) != null){
            urlResult.add(str);
        }
        TagGetter tagGetter = new TagGetter(urlResult);

        List<String> tagGet =
                tagGetter.tagGetter(TagType.ul,targetId).tagGetter(TagType.button).getResult();
//                        .UlGetter(targetId ).getResult();
        SaveText saveText = new SaveText("test", tagGet);
        saveText.save();
    }
}
