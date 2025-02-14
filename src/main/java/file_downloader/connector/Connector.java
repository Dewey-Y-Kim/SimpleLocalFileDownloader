package main.java.file_downloader.connector;

import main.java.file_downloader.ReadProperty;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Connector {
    private HttpsURLConnection connection;
    private String address;
    private String result;
    private int response;
    int attampt = Integer.parseInt(new ReadProperty("main/setting.properties").readProperties().getProperty("MaxAttampt"));
    public String getResult(){
        return result;
    }

    public int getResponse() {
        return response;
    }

    public List getList(){
        return Arrays.stream(result.split("\n")).toList();
    }

    public Connector(String address) throws IOException, URISyntaxException {

        this.address = address;

        this.result = readUrl();
    }

    private void openConnect() throws IOException, URISyntaxException {
        URL url = new URL(address);
//        URL url = new URI(address).toURL();
        HttpsURLConnection connect = null;
        // max connect restiction = property
        int thisAttampt=0;
        while (thisAttampt<attampt && (connect == null || connect.getResponseCode() != 200)) {
            thisAttampt ++;
            try {
                connect = (HttpsURLConnection) url.openConnection();
                connect.setReadTimeout(5000);
                connect.setRequestMethod("GET");
                connect.setRequestProperty("Content-Type", "application/json");
                this.response = connect.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.connection = connect;

    }
    public HttpsURLConnection setReadTimeout(int time){
        this.connection.setReadTimeout(time);
        return this.connection;
    }
    public HttpsURLConnection setRequestProp(String propety, String value){
        this.connection.setRequestProperty(propety,value);
        return connection;
    }
    public InputStream getInputstream() throws IOException {
        return this.connection.getInputStream();
    }
    private void disConnect(){
        if(connection !=null) connection.disconnect();
    }
    public JSONObject getJsonObj() throws URISyntaxException, IOException, ParseException {
        openConnect();
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        int i;
        while ( (i = inputStreamReader.read())!=-1){
            stringBuilder.append((char) i );
        }
        disConnect();
        return  (JSONObject) new JSONParser().parse(stringBuilder.toString());
    }

    public String readUrl() throws IOException, URISyntaxException {
        openConnect();
        String result = null;
        try {
            if(connection.getResponseCode() == 200) {
                result = readResopnseData(connection.getInputStream());
            }
        } catch (IOException e) {
             e.printStackTrace();
         }
        this.result =result;
        connection.disconnect();
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
