package main.java.file_downloader;

import main.java.file_downloader.connector.ConnectListUrl;
import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.responseprocess.ListObj;
import main.java.file_downloader.responseprocess.SplitLiTag;
import main.java.file_downloader.imageprocess.ImageMaker;
import main.java.file_downloader.responseprocess.GetBody;
import main.java.file_downloader.fileprocess.SaveText;
import main.java.file_downloader.textprocess.GetValueByVarName;
import main.java.file_downloader.textprocess.TextTransform;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {
    private final String address;
    public Downloader(){
        this.address  = new ReadProperty("main/setting.properties").readProperties().getProperty("Download.url");
    }
    public Downloader(String address) {
        this.address = address;
    }
    public void makeFullToOnefile() throws IOException, URISyntaxException {
        // 목록 연결
        Connector connector = new Connector(address) ;

        GetBody titleList = new GetBody(connector.getList());

        List list = titleList.getResult();

        SaveText saveOneTextFile = new SaveText(titleList.getTitle());
        for (int idx = (list.size()+1) / 2 ; idx>0 ; idx--) {
            String smallUrl = (String) list.get(idx * 2 - 1);
            String smallTitle = (String) list.get(idx * 2 -2);
            ConnectListUrl tempConnect = new ConnectListUrl(smallUrl);
            GetBody temp = new GetBody(tempConnect.getResult(), smallTitle);
            System.out.println(temp.getTitle() + " is reading.");

            saveOneTextFile.appendText(temp.getResult());
        }
            saveOneTextFile.save();
    }
    public void makeFulltoMultifile() throws IOException, URISyntaxException {
        Connector connector = new Connector(address);
        GetBody titleList = new GetBody(connector.getList());
        List list = titleList.getResult();
        for (int idx = (list.size()+1) / 2 ; idx>0 ; idx--) {
            String smallUrl = (String) list.get(idx * 2 - 1);
            String smallTitle = (String) list.get(idx * 2 -2);
            ConnectListUrl tempConnect = new ConnectListUrl(smallUrl);
            GetBody temp = new GetBody(tempConnect.getResult(), smallTitle);
            SaveText saveText = new SaveText(titleList.getTitle(), smallTitle, temp.getResult());
            saveText.save();
            System.out.println(temp.getTitle() + " has saved.");
        }
    }
    public void makeImg() throws IOException, URISyntaxException {
        Queue original = makeImageList();

        int index = 0;

        for (Object map : original) {
            index++;
            HashMap obj = (HashMap) map;
            String title = (String) obj.get("title");
            String[] array = (String[]) obj.get("imgPath");
            System.out.printf("start making %s\n",title);
            for (int idx = 0; idx < array.length; idx++) {
                Float percent = (float) (Math.round((float) idx / array.length * 10000)/100);
                System.out.printf("%s : (%d/%d) %s \n",title,idx + 1,array.length, percent.toString()+"%");
                array[idx] = "https:" + array[idx];

                String fileIdx = new TextTransform().lPad(String.valueOf(idx),String.valueOf(array.length).length());
                new ImageMaker(array[idx],title,title + "-"+fileIdx).make();
            }
            Float percent = (float) (Math.round( (float) index / original.size() * 10000) ) / 100;
            System.out.printf("----------- \n complete making %s (%d / %d) %s ) \n ",title, index, original.size(), percent.toString()+"%" );

        }

    }
    public void makeImgList() throws IOException, URISyntaxException {
        List original = makeImageList();
        for (Object map : original) {
            HashMap obj = (HashMap) map;
            String title = (String) obj.get("title");
            String[] array = (String[]) obj.get("imgPath");
            for (int idx = 0; idx < array.length; idx++) {
                array[idx] = "https:" + array[idx];
            }
            new SaveText( title, array).save();
        }
        
    }
    private LinkedList makeImageList() throws IOException, URISyntaxException {
        String original ="";
//        read Data
        try {
            Connector connector = new Connector(address);
            original = connector.getResult();
        // 만약... 페이징이 만들어져 있다면 여기에 페이징을 추가해 original에 페이지 추가
            if(original.contains("pg_end")){
                String endLine = patternMaker("href=\"(.*)\" class=\"pg_page pg_end",original);
                String lastPage = (patternMaker("page=(.*)",endLine).replaceAll("page=",""));
                Integer last = Integer.valueOf(lastPage);

                for ( int idx = 2; idx <= last; idx++){
                    Connector connection = new Connector(address+"&page="+idx);
                    original += connection.getResult();
                }
                // 현재 페이지 = pg_end 페이지?
                //address 수정후 getResult 해서 original에 붙이
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        // get Collumn
        List<String> splittedLiTag = new SplitLiTag(original).getResult();

        // make List contains title, address
        LinkedList<HashMap> pathList = new LinkedList<>();
        String keyword = "img_list";

        for(String str : splittedLiTag){
            ListObj obj = new ListObj(str);
            HashMap hashMap = new HashMap<>();
            String title = obj.getTitle().replaceAll("\\s\\s","");
            hashMap.put("title", title);
            pathList.add(hashMap);
            String[] imgPath = new GetValueByVarName(keyword,new Connector(getAddress(obj.getAddress())).getResult()).getResult();
            hashMap.put("imgPath",imgPath);
            System.out.printf("%s is loaded\n",title);
        }

        return pathList;
//        new SaveText("makeImage.txt");
    }
    public String getAddress(String originalTag){
        String str = originalTag;
        String regex = "'./(.*)";
        String result = patternMaker(regex, str).replaceFirst("'.","").replaceAll("'","");

        return address.substring(0,address.indexOf(result.substring(0,5))) + result;

    }
    public String patternMaker(String originalPattern, String str){
        Pattern pattern = Pattern.compile(originalPattern);
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text.replaceFirst("\\S*=\"","").replaceFirst("\"(.*)","");

    }
}
