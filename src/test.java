import FIleDownloader.ReadText;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class test {
    public static void main(String[] Args) throws IOException {
        String fullPath = "/home/dewey/Downloads/books/포식으로 레벨업하는 군주님";
        // 1. 파일 객체 생성
        File file = new File(fullPath);
        String[] path = fullPath.split("/");

        String[] tempList = file.list();
        Arrays.sort(tempList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i=0;
                if (o1.length() < o2.length()){
                    i = -1;
                } else if ( o1.length() > o2.length()){
                    i = 1;
                } else if ( o1.length() == o2.length()){
                    i= o1.compareTo(o2);
                }

                return i;
            }
        });
        System.out.println(tempList.length);

        FileWriter fileWriter = new FileWriter(fullPath+"/"+path[path.length-1]+".txt");
        int idx =0;
        for (String str : tempList){
            idx++;
            File temp = new File(file.getAbsolutePath()+"/"+str);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()+"/"+str));
            String tempString;
            while((tempString=bufferedReader.readLine())!=null){
                fileWriter.write(tempString+"\n");
            }
            fileWriter.write("\n\n");
            System.out.println(idx+" : "+str + "has been read");
        }

        fileWriter.flush();
        System.out.println("Job complete");
//        // 2. 파일 존재여부 체크 및 생성
//        try {
//            System.out.println(fullPath + " is starting");
//            if (!file.exists()) {
//                    file.createNewFile();
//            }
//        // 3. Buffer를 사용해서 File에 write할 수 있는 BufferedWriter 생성
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter writer = new BufferedWriter(fw);
//            // 4. 파일에 쓰기
//            writer.write(bodyText);
//            // 5. BufferedWriter close
//            writer.close();
//            System.out.println(fullPath + " is complete");
//        } catch (IOException e) {
//            System.out.println("Error occor  " + fullPath);
//            throw new RuntimeException(e);
//        }
    }

}
