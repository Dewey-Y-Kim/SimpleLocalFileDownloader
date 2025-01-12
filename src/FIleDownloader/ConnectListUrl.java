package FIleDownloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConnectListUrl {
    public static HttpURLConnection openConnect(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection connect = null;

        try {
            connect = (HttpURLConnection) url.openConnection();
            connect.setReadTimeout(3000);
            connect.setRequestMethod("GET");
            connect.setRequestProperty("Content-Type", "application/json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(connect.getResponseCode());
        System.out.println(connect.getResponseMessage());
        return connect;
    }

    public static void readUrl(HttpURLConnection httpURLConnection) {
        try {
            if(httpURLConnection.getResponseCode() == 200) {
                List<String> result = readResopnseData(httpURLConnection.getInputStream());
                System.out.print(result);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // Read responseData to StringBuilder
    public static List<String> readResopnseData(InputStream inputStream) {
        if(inputStream == null ) return null;

//        StringBuilder stringBuilder = new StringBuilder();
        List<String> stringBuilder = new ArrayList<>();
        String line = "";
        int idx = 1;
        String regex = "<div\\s+class";

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
        {
            while( (line = bufferedReader.readLine()) != null) {
                System.out.print(line.matches(regex));
                //line 선처리 \t \s 제거
                line.replaceAll("\\t\s","");
                line.replaceAll("\\t","");
                if (line.matches(regex)) line = regex +"    "+ line;
                stringBuilder.add(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
