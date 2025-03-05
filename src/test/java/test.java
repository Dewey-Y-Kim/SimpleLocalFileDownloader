package test.java;

import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.responseprocess.ApiAccess;
import org.json.simple.parser.ParseException;
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
import java.util.Properties;

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
            connector = new Connector("");
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
    public void testTread() throws URISyntaxException, IOException, ParseException {
        String[] list ={};
        String test ="";
        String[] splited = test.split("/");
        String chapter = splited[6];
        System.out.println(splited[7].split("\\."));
        String[] fullname = String.valueOf(splited[7]).split("\\.");
        System.out.println(fullname.length);
        System.out.println(fullname.length);
        String filename = fullname[0];
        
//        filename = filename.replaceAll("image","");
        String ext = fullname[1];
        Connector connector = new Connector(test);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(connector.getInputstream());
        int buffer = 8192;
        int idx =0;
        byte[] bytes = new byte[buffer];

        FileWriter fileWriter = new FileWriter(new File("/home/dewey/"+chapter+"-"+filename+"."+ext));
        while ( (idx = connector.getInputstream().read() ) !=-1){
            fileWriter.write(idx);
        }
    }
    @Test
    public void testHead() throws IOException {
        String fileURL = "";
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
    @Test
    public void deleteTest(){
        long time = System.currentTimeMillis();
        processBuilder("ls");

        System.out.println(System.currentTimeMillis() - time);
//        File file = new File("/home/dewey/Projects/FileDownloader/31.webp");
//            file.delete();
//        String result = processBuilder("cd ~ && ls");
//        System.out.println(result);
//        processBuilder("ls");
    }
    public String processBuilder(String operation){
        String line = "";
        try {
            ProcessBuilder builder = new ProcessBuilder("sh", "-c",operation);

            builder.redirectErrorStream(true); // 오류 출력도 함께 읽기
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String tempString = "";
            while ((tempString = reader.readLine()) != null) {
                line += tempString+"\n";
            }
            reader.close();

            int exitCode = process.waitFor(); // 프로세스 종료 대기
            System.out.println("Exit Code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return line;
    }
}
