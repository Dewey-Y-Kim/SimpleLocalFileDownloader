package main.java.file_downloader.imageprocess;

import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.responseprocess.ListObj;
import main.java.file_downloader.responseprocess.SplitLiTag;
import main.java.file_downloader.textprocess.GetValueByVarName;
import main.java.file_downloader.textprocess.TextTransform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.*;

public class ImgProcess implements ImageProcesser{
    String address = "";
    public ImgProcess(String address){
        this.address = address;
    }

    public List makeImageList() throws IOException {
        // make List contains title, address
        String original = readFulllist();
        // get Collumn
        List<String> splittedLiTag = new SplitLiTag(original).getResult();
        // make List contains title, address
        return makeHashList(splittedLiTag);
    }
    private String readFulllist(){
        return readFullList(null);
    }

    private String readFullList(String splitCode){
        // pg_end만 입력시 동일 수행하도록 수정 준비
        String original ="";
//        read Data
        try {
//            Connector connector = new Connector(address);
            original = new Connector(address).readUrl();
            // 만약... 페이징이 만들어져 있다면 여기에 페이징을 추가해 original에 페이지 추가
            if(original.contains("pg_end")){
                String endLine = new TextTransform().patternMaker("href=\"(.*)\" class=\"pg_page pg_end",original);
                String lastPage = (new TextTransform().patternMaker("page=(.*)",endLine).replaceAll("page=",""));
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
        return original;
    }
    private List makeHashList( List splittedLiTag) throws IOException {
        // make List contains title, address
        List<HashMap> pathList = new ArrayList<>();
//        Iterator iterator = splittedLiTag.iterator();
//        질문
//        // Tread process
//        for( int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
//            new MakeHashList(pathList, iterator,address).start();
//        }

        // make List contains title, address
        String keyword = "img_list";
        String error="";
        Iterator iterator = splittedLiTag.iterator();
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
                    new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"["+e.getClass().getName() + "]\n"+e, error+"\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            } catch (MalformedURLException e) {
                try {
                    new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"["+e.getClass().getName() + "]\n"+e, error+"\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"["+e.getClass().getName() + "]\n"+e, error+"\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }
        }
        return pathList;
    }
    private void HashAccess(){



    }
    public void makeImg(List source) {
        // Img 파일 객체 리스트 생성
        Deque original = new LinkedList();

        for (Object map : source){
            HashMap tmp  = (HashMap) map;
            String[] path = (String[]) tmp.get("imgPath");
            String title = (String) tmp.get("title");
            for (int idx = 0 ; idx < path.length ; idx ++){
                HashMap newMap = new HashMap();
                newMap.put("title",title);
                String fileIdx = new TextTransform().lPad(String.valueOf(idx),String.valueOf(path.length).length());
                newMap.put("filename",title+"-"+fileIdx);
                newMap.put("path","https:" + path[idx]);
                original.add(newMap);
            }
        }
        // Tread 처리
        Integer idx = 0;
        Float percent = (float) 0;
        int numberOfItem = original.size();
        for ( int i  = 0; i < Runtime.getRuntime().availableProcessors() +1; i++){
            new ListToImg(original, idx, percent, numberOfItem).start();
        }
    }
    public String getAddress(String originalTag){
        String str = originalTag;
        String regex = "'./(.*)";
        String result = new TextTransform().patternMaker(regex, str).replaceFirst("'.","").replaceAll("'","");

        return address.substring(0,address.indexOf(result.substring(0,5))) + result;
    }
}
