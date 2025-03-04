package test.java;

import main.java.file_downloader.connector.Connector;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;

public class test {
    public static void main(String[] ag) {
        Iterator agIter = Arrays.stream(ag).iterator();
        while (agIter.hasNext()) {
            System.out.println(agIter.next().getClass().getName());
        }
    }

    @Test
    public void removePtag() {
        String str = "<p> adga </p>";
        System.out.println(str.replaceAll("<(/?)p>", "\n").replaceAll("\n\n", "\n"));
    }

    @Test
    public void test() {
        Connector connector;
        InputStream inputStream;
        try {
            connector = new Connector("https://t60.hoduhodu.com/webtoondata/4803/img/1/image31.jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            if (connector != null) {
                inputStream = connector.getInputstream();
                byte[] bytes = new byte[1024];

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHead() throws IOException {
        String fileURL = "https://t60.hoduhodu.com/webtoondata/14543/img/18/image1.jpg";
        try {
            URL url = new URL(fileURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");

            // 우선 Content-Length 헤더 확인 (제공되지 않으면 -1 반환)
            long contentLength = connection.getContentLengthLong();
            connection.disconnect();

            if (contentLength == -1) {
                // HEAD 요청에서 Content-Length를 제공하지 않으면, Range 요청 시도
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Range", "bytes=0-0"); // 첫 번째 바이트만 요청
                connection.connect();

                String contentRange = connection.getHeaderField("Content-Range");
                System.out.println(connection);
                connection.disconnect();
                System.out.println(contentRange);
                if (contentRange != null) {
                    // Content-Range 예시: "bytes 0-0/1234567" (파일 크기: 1234567)
                    String[] parts = contentRange.split("/");
                    System.out.println(parts[0]);
                    System.out.println(parts[1]);
                    if (parts.length > 1) {
                        System.out.println(Long.parseLong(parts[1])); // 전체 파일 크기
                    }
                }
            }
            System.out.println( contentLength) ;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(-1);;
        }
    }
}
