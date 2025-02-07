package main.java.file_downloader.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Connector {
    private HttpURLConnection conection;
    private String address;
    private String result;

    public String getResult(){
        return result;
    }
    public List getList(){
        return Arrays.stream(result.split("\n")).toList();
    }

    public Connector(String address) throws IOException, URISyntaxException {

        this.address = address;

        this.result = openConnect();
    }

    public String openConnect() throws IOException, URISyntaxException {
        URL url = new URI(address).toURL();
        HttpURLConnection connect = null;

        try {
            connect = (HttpURLConnection) url.openConnection();
            connect.setReadTimeout(5000);
            connect.setRequestMethod("GET");
            connect.setRequestProperty("Content-Type", "application/json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.conection = connect;

        return readUrl();
    }


    private String readUrl() {
        String result = null;
        try {
            String code;
            code = String.valueOf(conection.getResponseCode());
            if(conection.getResponseCode() == 200) {
                result = readResopnseData(conection.getInputStream());
            }
        } catch (IOException e) {
             e.printStackTrace();
         }

            conection.disconnect();
        return result;
    }

    // Read responseData to StringBuilder
    private static String readResopnseData(InputStream inputStream) {
        if(inputStream == null ) return null;

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        int idx = -1;
        String regex = "<div\\s+class";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while( (line = bufferedReader.readLine()) != null) {
                //line 선처리 \t \s 제거
                line.replaceAll("\\s\\s"," ");
                line.replaceAll("\\t\s","");
                line.replaceAll("\\t","");
                if (line.matches(regex)){
                    line = regex +"    "+ line;
                }
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
