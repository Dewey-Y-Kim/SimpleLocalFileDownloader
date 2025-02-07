package test.java;

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
}
