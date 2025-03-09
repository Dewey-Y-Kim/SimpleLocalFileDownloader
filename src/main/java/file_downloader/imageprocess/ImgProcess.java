package main.java.file_downloader.imageprocess;

import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.domain.Img;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.responseprocess.ListObj;
import main.java.file_downloader.responseprocess.SplitLiTag;
import main.java.file_downloader.textprocess.GetValueByVarName;
import main.java.file_downloader.textprocess.TextTransform;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutorService;

public class ImgProcess implements ImageProcesser{
    String address = "";
    String title = "";
    private int paddingNumber;
    ExecutorService executorService;
    public ImgProcess(String address){
        this.address = address;
    }

    public ImgProcess(ExecutorService service) {
        this.executorService = service;
    }

    public List makeImageList() throws IOException {
        // make List contains title, address
        String original = readFulllist();
        // get Collumn
        List<String> splittedLiTag = new SplitLiTag(original,"comic-episode-list").getResult();
        // make List contains title, address
        String keyword = "img_list";
//        this.title = title;
        this.title =new TextTransform().patternMaker("(?>\"og:title\" content=\")(.*)\" />",original);
        return makeImglist(splittedLiTag, keyword);
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
    private List makeImglist( List splittedLiTag) throws IOException {
        return makeImglist(splittedLiTag);
    }
    private List makeImglist( List splittedLiTag, String keyword) throws IOException {
        // make List contains title, address
        List<Img> pathList = new ArrayList<>();

        // make List contains title, address
        String error="";
        Iterator iterator = splittedLiTag.iterator();
        while(iterator.hasNext()){
            error ="";
            String str = (String) iterator.next();
            try {
                ListObj obj = new ListObj(str);

//                HashMap hashMap = new HashMap<>();
                String chapter = obj.getTitle().replaceAll("\\s\\s", "");
//                hashMap.put("title", title);

                error = chapter;
                String imgConnectResult ="";
                String detailAddress = getAddress(obj.getAddress());
                error += detailAddress;
                imgConnectResult = new Connector(detailAddress).getResult();

                String[] imgPath = new GetValueByVarName(keyword, imgConnectResult).getResult();

//                hashMap.put("imgPath", imgPath);
//                pathList.add(hashMap);
                for(int i= 0 ; i < imgPath.length; i++){
                    String fileIdx = new TextTransform().lPad(String.valueOf(i),String.valueOf(imgPath.length).length());
                    String filename = chapter+"-"+fileIdx;
                    String path = imgPath[i];
                    if(path.substring(0,1).equals("/")){
                            path = "https:"+imgPath[i];
                    }
                    Img img = new Img(title, chapter, path, i, filename);
                    pathList.add(img);
                }
                System.out.printf("%dth image is loaded\n", pathList.size());
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
    private List makeHashlist( List splittedLiTag, String keyword) throws IOException {
        // make List contains title, address
        List<HashMap> pathList = new LinkedList<>();

        // make List contains title, address
        String error="";
        Iterator iterator = splittedLiTag.iterator();
        while(iterator.hasNext()){
            error ="";
            String str = (String) iterator.next();
            try {
                ListObj obj = new ListObj(str);
                HashMap hashMap = new HashMap<>();
                String chapter = obj.getTitle().replaceAll("\\s\\s", "");
                error = chapter;
                pathList.add(hashMap);
                String imgConnectResult ="";
                String detailAddress = getAddress(obj.getAddress());
                error += detailAddress;
                imgConnectResult = new Connector(detailAddress).getResult();

                String[] imgPath = new GetValueByVarName(keyword, imgConnectResult).getResult();
                hashMap.put("title", title);
                hashMap.put("chapter", chapter);
                hashMap.put("imgPath", imgPath);
                System.out.printf("%s is loaded\n", chapter);
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
    public void makeImg(List source) {
        // Img 파일 객체 리스트 생성
        Deque original = new LinkedList(source);

        // Tread 처리
        Integer idx = 0;
        Float percent = (float) 0;
        int numberOfItem = original.size();
        if( executorService != null){
            for (int i=0; i< Runtime.getRuntime().availableProcessors(); i++ ){
                executorService.submit(new ListToImg(original, idx, percent, numberOfItem, paddingNumber + 1));
            }
            executorService.shutdown();
        }else{
            for (int i = 0; i < Runtime.getRuntime().availableProcessors() + 1; i++) {
                new ListToImg(original, idx, percent, numberOfItem, paddingNumber + 1).start();
            }
        }
    }
    public String getAddress(String originalTag){
        String str = originalTag;
        String regex = "'./(.*)";
        String result = new TextTransform().patternMaker(regex, str).replaceFirst("'.","").replaceAll("'","");

        return address.substring(0,address.indexOf(result.substring(0,5))) + result;
    }

    public void makeImg(List apiresult, int numberOfepisode) {
        paddingNumber = String.valueOf(numberOfepisode).length();
        makeImg(apiresult);
    }


}
