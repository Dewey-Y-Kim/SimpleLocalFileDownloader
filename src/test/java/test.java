package test.java;

<<<<<<< HEAD
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class test{

    public static void main(String[] args) throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
//
//        File file = new File("/home/dewey/Downloads/books/detail.html");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        List<String> list  = new ArrayList<>();
//
//        // get var to Array
//        String keyword = "var img_list";
//        String str;
//        String[] result= null;
//        //finder
//        while((str = bufferedReader.readLine())!=null){
//            if (str.contains(keyword)){
//                result = str.replaceFirst("(.*)\\[","").replaceFirst("]","").replaceAll("\"","").replaceAll(";","").split(",");
//                break;
//            }
//        }
//        for(int idx = 0 ; idx < result.length ; idx ++ ){
//            result[idx] = "https:"+result[idx];
//            System.out.println(result[idx]);
//        }
//
        //        SaveText saveText = new SaveText("test",list);
//        saveText.save();
        // if you want to get png or jpg ... you can do it
//        String address = "";
//        String path = "main/setting.properties";
//        String defaultPath = new ReadProperty(path).readProperties().getProperty("Download.path");
//        String fileName = "test";
//        String result;
//        URL url = new URI(address).toURL();
//        String extension = address.substring(address.indexOf('.') + 1);
//
//        BufferedImage image = ImageIO.read(url);
//        File file = new File(defaultPath + fileName);
//
//        ImageIO.write(image, extension, file);
//        result = fileName + " is complete.";
        //p3p:
        //CP="ALL CURa ADMa DEVa TAIa OUR BUS IND PHY ONL UNI PUR FIN COM NAV INT DEM CNT STA POL HEA PRE LOC OTC"
        }

=======
import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;

public class test{

    public static void main(String[] args) throws IOException, URISyntaxException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        String address = "";
        URL url = new URI(address).toURL();
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, null, null);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        connection.setSSLSocketFactory(context.getSocketFactory());
        connection.setInstanceFollowRedirects(true);

        connection.setRequestMethod("GET");
        connection.setReadTimeout(10000);

        System.out.println(connection.getResponseCode());

    }
>>>>>>> c951825fbe76a6ab515912b64c654f6c96adfc46
}
