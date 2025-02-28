package main.java.file_downloader.fileprocess;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

public class ControlFile {

    File file = null;
    String directoryPath = null;
    String fullfilePath = null;
    InputStream in = null;

    public ControlFile(String path) {
        this.directoryPath = path;
    }

    public ControlFile(String fullfilePath, InputStream in) {
        this.file = new File(fullfilePath);
        this.fullfilePath = fullfilePath;
        this.in = in;
    }

    public int createFile() throws IOException {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return 0;
                // 최종 결과 확인을 위해. 만들계획은 없음.
            }
        }
        int i;

        FileOutputStream fileOutputStream = new FileOutputStream(fullfilePath);

        try {
            int size = 4096;
            byte[] buffer = new byte[size];
            while (((i = in.read(buffer)) != -1)) {
                fileOutputStream.write(buffer, 0, i);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            new ReportError(new Object() {
            }.getClass().getEnclosingClass().getName(),
                    e.getClass().getName() + "\n" + "[Create File]",
                    fullfilePath);
            return 0;
        } finally {
            in.close();
        }

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
        return 1;
    }

    public void chkpath() {
        // chk folder
        File path = new File(directoryPath);
        if (!path.exists()) {
            path.mkdirs();
        }
    }
}
