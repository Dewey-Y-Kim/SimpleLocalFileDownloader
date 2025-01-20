import FIleDownloader.SaveText;
import FIleDownloader.TagGetter.TagGetter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
//        ConnectListUrl connectListUrl = new ConnectListUrl("");
//        List<String> urlResult = connectListUrl.openConnect();
//        SaveText saveText = new SaveText("sakamotoDays", urlResult);
//        saveText.save();

//        ConnectListUrl connectListUrl = new ConnectListUrl("");
//        List<String> urlResult = connectListUrl.openConnect();
        File file = new File("/home/dewey/Downloads/books/sakamotoDays");
        BufferedReader bufferedReader = new BufferedReader( new FileReader( file ));
        String str;
        List<String> result = new ArrayList<>();
        while ((str= bufferedReader.readLine()) != null){
            result.add(str);
            System.out.println(str);
        }
        System.out.println(result.toString());
        TagGetter tagGetter = new TagGetter(result);

        List<String> tagGet = tagGetter.UlGetter("tagId").getResult();
        SaveText saveText = new SaveText("test", tagGet);
        saveText.save();
    }
}
