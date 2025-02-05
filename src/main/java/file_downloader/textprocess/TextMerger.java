package main.java.file_downloader.textprocess;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class TextMerger {
    private String fullPath;
    private File file;
    public TextMerger(){

    }
    public TextMerger(String fullPath) throws IOException {
        this.fullPath = fullPath;
        file = new File(fullPath);
        textMerger();
    }
    private void textMerger() throws IOException {

        FileSaver(ListSorter());
    }

    private String[] ListSorter(){
        String[] tempList = file.list();
        // list가 순서없이 만들어지므로 정렬
        // 이름-20 이 이름-2 보다 앞순서로 정렬하기 때문에 길이별 정렬 후, 다시 이름순으로 정렬.
        Arrays.sort(tempList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                if (o1.length() < o2.length()){
                    return  -1;
                } else if ( o1.length() > o2.length()){
                    return  1;
                }
                return o1.compareTo(o2);
            }
        });
        return tempList;
    }

    private void FileSaver(String[] tempList) throws IOException {
        // 리스트를 받아 파일 작성.
        String[] path = fullPath.split("/");
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
    }

}
