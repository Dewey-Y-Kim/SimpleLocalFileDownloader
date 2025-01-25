package FIleDownloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConnectListUrl {
    private HttpURLConnection conection;
    private String address;
    private List<String> result;

    public List<String> getResult(){
        return result;
    }

    public ConnectListUrl(String address) throws IOException {
        this.address = address;
        this.result = openConnect();
    }

    public List<String> openConnect() throws IOException {

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
        this.conection = connect;

        return readUrl();
    }


    private List<String> readUrl() {
        List<String> result = null;
        try {
            if(conection.getResponseCode() == 200) {
                result = readResopnseData(conection.getInputStream());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        conection.disconnect();
        return result;
    }

    // Read responseData to StringBuilder
    private static List<String> readResopnseData(InputStream inputStream) {
        if(inputStream == null ) return null;

        List<String> result = new ArrayList<>();
        String line = "";
        int idx = -1;
        String regex = "<div\\s+class";
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
        {
            while( (line = bufferedReader.readLine()) != null) {
                idx++;
                //line 선처리 \t \s 제거
                line.replaceAll("  "," ");
                line.replaceAll("\\t\s","");
                line.replaceAll("\\t","");
                if (line.matches(regex)) line = regex +"    "+ line;
                result.add(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    private List<String> getTitle(List<String> list){
        List<String> result = new ArrayList<>();
        boolean found = false;
        for (String s : list) {
            if(s.contains("toon_index")){
                found = true;
            }
            if (found == true && s.contains("</ul>") ){
                found = false;
                break;
            }
            // 공백 제거
            if(found == true && s.equals("") || s.contains("toon_index") ||s.contains("ul")){
                continue;
            }
            if( found ) result.add(s);
        }
        return result;
    }
}
