package main.java.file_downloader.fileprocess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.*;

public class ControlFile {
    boolean retry = false;
    File file = null;
    String directoryPath = null;
    String fullfilePath = null;
    BufferedInputStream in = null;
    String fileName;
    String ext;
    String defaultPath;

    public ControlFile(String path) {
        this.directoryPath = path;
    }
    public ControlFile(String path, boolean retry){
        this(path);
        this.retry = retry;
    }

    public ControlFile(String fullfilePath, InputStream in) {
        this.file = new File(fullfilePath);
        this.fullfilePath = fullfilePath;
        this.in = new BufferedInputStream(in);
    }
    public ControlFile(String fullfilePath, InputStream in,boolean retry){
        this(fullfilePath, in);
        this.retry = retry;
    }
    ControlFile(String fullfilePath, String address) {
        this.file = new File(fullfilePath);
        this.fullfilePath = fullfilePath;

    }

    public ControlFile(String defaultPath, String fileName, String ext, InputStream inputStream, boolean retry) {
        String fullfilePath = defaultPath + "/" + fileName + ext;
        this.fileName = fileName;
        this.ext = ext;
        this.defaultPath = defaultPath;
        this(fullfilePath,inputStream,retry);

    }

    public void setIn(InputStream in) {
        this.in = new BufferedInputStream(in);
    }

    public boolean createFile() throws IOException {

        int attempt =0;
        while (attempt<10 && isCorrptedImg() ) {
            if( retry){
                file.delete();
//                System.out.println(file.getPath());
            }

//            File chk = file;
            int i = 0;
            while(file.exists()){
                file = new File(defaultPath+'/'+ fileName+"("+i+")."+ext);
                fullfilePath = file.getAbsolutePath();
                System.out.println(file.getName());
                i++;
            }
//            if (file.exists()) {
//
//            }
            try{
                file.createNewFile();
            } catch (IOException e) {
//            return false;
            // 최종 결과 확인을 위해. 만들계획은 없음.
            }
            int idx;
            attempt++;
            FileOutputStream fileOutputStream = new FileOutputStream(fullfilePath);
//            in.reset();
            try {
                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];
                while (((idx = in.read(buffer)) != -1)) {
                    fileOutputStream.write(buffer, 0, idx);
                }
                fileOutputStream.flush();
            } catch (IOException e) {
                new ReportError(new Object() {
                }.getClass().getEnclosingClass().getName(),
                        e.getClass().getName() + "\n" + "[Create File]",
                        fullfilePath);
                file.delete();
                return false;
            } finally {
                in.close();
                file.getAbsolutePath();
            }
//            if(file.length() > chk.length()){
//                chk.delete();
//            } else{
//                file.delete();
//                chk.renameTo(file);
//            }
        }
        if(attempt==10) new ReportError(new Object() {
        }.getClass().getEnclosingClass().getName(), "\n" + file.getName() + "\n" + file.getAbsolutePath() + "\n", "err");


//        BufferedImage image=null;
//        try {
//
//            InputStream inputStream = in;
//            image = ImageIO.read(in);
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix(fullfilePath.substring(fullfilePath.lastIndexOf('.')));
//        ImageWriter jpegImageWriter;
//        jpegImageWriter = (ImageWriter) writers.next();
//        JPEGImageWriteParam writeParam = (JPEGImageWriteParam) jpegImageWriter.getDefaultWriteParam();
//        writeParam.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
//
//
//        ImageOutputStream imageOutputStream;
//        imageOutputStream = ImageIO.createImageOutputStream(new FileOutputStream(fullfilePath));
//        jpegImageWriter.setOutput(imageOutputStream);
//        jpegImageWriter.write(null,
//                new IIOImage(
//                        image,
//                        null,
//                        null), writeParam);
//        jpegImageWriter.dispose();
//        imageOutputStream.close();
        return true;
    }

    public boolean isCorrptedImg() {
        try {
            BufferedImage img = ImageIO.read(file);
            if (img.getWidth() < 1 || img.getHeight() < 1) {
                return true;
            }
            Raster raster = img.getRaster();
            if (raster == null) return true; // 픽셀 데이터가 없으면 손상된 이미지

            return img == null;     // 이미지가 null이면 손상됨
        } catch (IOException e) {
            return true; // 예외 발생 시 손상된 이미지로 간주
        }
    }

    public boolean isCorrupted() {
        byte[] header = new byte[8];
        try (FileInputStream fis = new FileInputStream(file)) {
            if (fis.read(header) < 8) return true; // 파일 크기가 너무 작으면 손상됨
            return !isImageHeader(header);
        } catch (IOException e) {
            return true;
        }
    }

    public boolean isImageHeader(byte[] header) {
        if (header.length < 8) return false;
        // PNG 헤더 검사 (0x89 50 4E 47 0D 0A 1A 0A)
        if (header[0] == (byte) 0x89 && header[1] == 0x50 && header[2] == 0x4E && header[3] == 0x47)
            return true;
        // JPG 헤더 검사 (0xFF 0xD8)
        if (header[0] == (byte) 0xFF && header[1] == (byte) 0xD8)
            return true;
        // GIF 헤더 검사 ("GIF89a" 또는 "GIF87a")
        if ((header[0] == 'G' && header[1] == 'I' && header[2] == 'F'))
            return true;
        // Webp 헤더 검사 ("RIFF" + "WEBP")
        if (header[0] == 'R' && header[1] == 'I' && header[2] == 'F' && header[3] == 'F'
                && header[8] == 'W' && header[9] == 'E' && header[10] == 'B' && header[11] == 'P')
            return true;

        return false;
    }

    public void chkpath() {
        // chk folder
        File path = new File(directoryPath);
        if (!path.exists()) {
            path.mkdirs();
        }
    }

    public boolean chkimg() {
        boolean result = false;
        if (file.isFile()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                if (bufferedImage == null) {
                    return false;
                } else {
                    return true;
                }
            } catch (FileNotFoundException e) {
//                e.printStackTrace();
                return false;
            } catch (IOException e) {
                return false;
//                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public void compareImg(String temp, String path) {
        File tempFile = new File(temp);
        File pathFile = new File(path);
        String defaultname = tempFile.getName();
        if (tempFile.length() < pathFile.length()) {
            tempFile.delete();
            pathFile.renameTo(tempFile);
        } else {
            pathFile.delete();
        }
    }
    public int convertToWebp(){
        // convert webp
        String destination = defaultPath+"/"+fileName+".webp";
        String convertToWebp = "convert "+ fullfilePath + " "+ destination;

        String processResult = processBuilder(convertToWebp);
        if( processResult.equals("")){
            new File(fullfilePath).delete();
            return 1;
        }
        if( processResult.contains("premature")){
            new File(fullfilePath).delete();
            new File(destination).delete();
        }
        return -1;
    }
    public String processBuilder(String operation){
        String line = "";
        try {
            ProcessBuilder builder = new ProcessBuilder("sh", "-c",operation);

            builder.redirectErrorStream(true); // 오류 출력도 함께 읽기
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String tempString = "";
//            while ((tempString = reader.readLine()) != null) {
//                line += tempString+"\n";
//            }
            line = reader.readLine();
            reader.close();

            int exitCode = process.waitFor(); // 프로세스 종료 대기
//            System.out.println("Exit Code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return line;
    }
}
