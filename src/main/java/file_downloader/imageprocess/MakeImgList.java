package main.java.file_downloader.imageprocess;

import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.responseprocess.ListObj;
import main.java.file_downloader.textprocess.GetValueByVarName;
import main.java.file_downloader.textprocess.TextTransform;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MakeImgList extends Thread{
    String address;
    List pathList;
    Iterator iterator;
    MakeImgList(List pathList, Iterator iterator, String address){
        this.address = address;
        this.pathList = pathList;
        this.iterator = iterator;
    }
    public void run() {
        // make List contains title, address
        String keyword = "img_list";
        String error="";
//        Iterator iterator = splittedLiTag.iterator();
        while(iterator.hasNext()){
            error ="";
            String str = (String) iterator.next();
            try {
                ListObj obj = new ListObj(str);
                HashMap hashMap = new HashMap<>();
                String title = obj.getTitle().replaceAll("\\s\\s", "");
                hashMap.put("title", title);

                error = title;
                pathList.add(hashMap);
                String imgConnectResult ="";
                String detailAddress = getAddress(obj.getAddress());
                error += detailAddress;
                imgConnectResult = new Connector(detailAddress).getResult();

                String[] imgPath = new GetValueByVarName(keyword, imgConnectResult).getResult();
                hashMap.put("imgPath", imgPath);
                System.out.printf("%s is loaded\n", title);
            }catch (NullPointerException | URISyntaxException e){
                e.printStackTrace();
                try {
                    new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"[image Loading]["+e.getClass().getName() + "]\n"+e, error+"\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            } catch (MalformedURLException e) {
                try {
                    new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"[image Loading]["+e.getClass().getName() + "]\n"+e, error+"\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"[image Loading]["+e.getClass().getName() + "]\n"+e, error+"\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }
        }
        super.interrupt();
    }
    public String getAddress(String originalTag){
        String str = originalTag;
        String regex = "'./(.*)";
        String result = new TextTransform().patternMaker(regex, str).replaceFirst("'.","").replaceAll("'","");

        return address.substring(0,address.indexOf(result.substring(0,5))) + result;
    }
}
