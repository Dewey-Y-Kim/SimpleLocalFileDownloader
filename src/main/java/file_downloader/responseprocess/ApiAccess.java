package main.java.file_downloader.responseprocess;

import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.domain.Img;
import main.java.file_downloader.domain.ResultObj;
import main.java.file_downloader.domain.TextData;
import main.java.file_downloader.fileprocess.ReportError;
import main.java.file_downloader.fileprocess.SaveText;
import main.java.file_downloader.imageprocess.ImgProcess;
import main.java.file_downloader.textprocess.TextTransform;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiAccess {
    String address;
    String title;
    public ApiAccess(String address){
        this.address = address;
    }

    public void start() throws URISyntaxException, IOException, ParseException {
        List apiresult = apiRead(address);
        String type = apiresult.getFirst().getClass().getName().replaceAll("(.*)\\.","");
        switch(type){
            case "TextData": textDataProcess(apiresult);break;
            case "Img" : new ImgProcess().makeImg(apiresult);break;
        }
    }

    private List apiRead(String apiPath) throws URISyntaxException, IOException, ParseException {

        String id = "";
        URI uri = new URI(apiPath);
        Pattern typePattern = Pattern.compile("(?<=/)\\D+(?=/)");
        String type = "";
        String path = uri.getPath();
        Matcher matcher = typePattern.matcher(path);

        String host = uri.getScheme() + "://" +uri.getHost();
        String endpoint =host+"/api/";

        if(matcher.find()){
            type = matcher.group();
        }
        Pattern idPattern = Pattern.compile("(?<=/)(\\d+)(?=/)?");
        Matcher idMatcher = idPattern.matcher(path);
        if(idMatcher.find()){
            id = idMatcher.group();
        }

        String title = getTitle(type, endpoint, id);
        List thisResult = new ArrayList();

        List result = getListAPI(type, id, endpoint, title) ;
        Iterator iterator = result.iterator();

        switch (type){
            case "webtoon":
                while( iterator.hasNext()){
                    ResultObj obj = (ResultObj) iterator.next();
                    List getImgPathResult = getImgPath(host,id, obj.getEpisode());
                    thisResult.addAll(getImgPathResult);
                    System.out.println(obj.getChapterTitle()+ " is loaded");

                }

                break;
            case "novel" :
                while(iterator.hasNext() ){
                    ResultObj obj = (ResultObj) iterator.next();
                    TextData textData = getTextdata(obj.getPath());
                    thisResult.add(textData);
                    System.out.println(obj.getChapterTitle()+ " is loaded");
                }
                break;
        }
        // Img download

        return thisResult;
    }
    //getTitle
    private String getTitle(String type, String endpoint, String id ) throws IOException, URISyntaxException, ParseException {
        // info api
        String infoApi ="";
        switch (type){
            case "webtoon":infoApi=endpoint + "webtooninfo?toon_id="+id; break;
            case "novel":infoApi=endpoint +"novelinfo?novel_id="+id; break;
        }
        Connector infoConnector = new Connector(infoApi);

        JSONObject tmpObj = (JSONObject) new JSONParser().parse((String) infoConnector.getResult());
        String title_key =(type.equals("webtoon")?"toon_title":"novel_title");
        String title = String.valueOf(tmpObj.get(title_key));
        
        return title;
    }
//list api connect
    private List getListAPI(String type, String id, String endpoint , String title) throws IOException, URISyntaxException, ParseException {
        // list api
        String listApi = endpoint + type+"list?list_id="+id+"&sort=desc";
        Connector connector = new Connector(listApi);
        String result = connector.getResult();
        JSONArray listArray = null;

        if( type.equals("novel")){
            listArray = (JSONArray) new JSONParser().parse(result);
        } else if(type.equals("webtoon")){
            JSONObject tmp = (JSONObject) new JSONParser().parse(result);
            listArray = (JSONArray) tmp.get("listData");
        }
        Iterator iterator = listArray.iterator();
        ArrayList<ResultObj> episodeList = new ArrayList<>();
        String[] defaultData = {"list_episode",      "list_title",};
        if(type.equals("novel")){
            
            for (int i=0; i<defaultData.length ; i++){
                defaultData[i] = "novel_" + defaultData[i];
            }
        }

        while( iterator.hasNext()){
            JSONObject obj = (JSONObject) iterator.next();
            String listEpisode = String.valueOf( obj.get(defaultData[0]));
            String listTitle = String.valueOf(obj.get(defaultData[1]));
            ResultObj resultobj = new ResultObj(title, type, id, listEpisode, listTitle, endpoint);
            episodeList.add(resultobj);
        }
      return episodeList;
    }
    private TextData getTextdata(String textApiAddress) throws IOException, URISyntaxException, ParseException {
        Connector connector;
        String result ="";
        String error = title;
        try {
            connector = new Connector(textApiAddress);
            result = connector.getResult();
        }catch (IOException e){
            new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"[getImgPath]["+e.getClass().getName() + "]\n"+e, error+"\n");

        }
        JSONObject resultObj = (JSONObject) new JSONParser().parse(result);

        JSONArray temp = (JSONArray) new JSONParser().parse(String.valueOf(resultObj.get("listData")));
        JSONObject listData = (JSONObject) temp.getFirst();
        JSONObject novel = (JSONObject) (listData.get("novel"));
        JSONObject content = (JSONObject) new JSONParser().parse(String.valueOf(resultObj.get("content")));
        String novel_title = String.valueOf(novel.get("novel_title"));
        String chapter = String.valueOf(listData.get("novel_list_title"));
        String novel_list_episode = String.valueOf(listData.get("novel_list_episode"));
        String data = String.valueOf(content.get("data"));

        data = data.replaceAll("<(/?)p>","\n").replaceAll(chapter,"").replaceAll("\r\n","\n").replaceAll("\n(\n+)","\n");
        data = setChapter(chapter) + data;
        return new TextData(novel_title, novel_list_episode, chapter, data);
    }
    private String setChapter(String chapter){
        return new String("--".repeat(chapter.length())+"\n"+chapter+"\n"+"--".repeat(chapter.length())+"\n");
    }

    private List getImgPath(String host, String id, String episode) throws IOException, URISyntaxException, ParseException {
        String imgApiAddress = host + "/api/getViewData?webtoonID="+id+"&episodeID="+episode;
        String error = null;

        error = imgApiAddress;
        List<Img> imgList = new ArrayList();
        Connector connector;
        String result ="";
        try {
            connector = new Connector(imgApiAddress);
            result = connector.getResult();
        } catch (IOException e){
            new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"[getImgPath]["+e.getClass().getName() + "]\n"+e, error+"\n");
        }
        try{
        JSONObject resultObj = (JSONObject) new JSONParser().parse(result);
        JSONArray tempViewData = (JSONArray) resultObj.get("view_data");
        JSONObject viewData = (JSONObject) tempViewData.getFirst();
        JSONObject webtoon = (JSONObject) viewData.get("webtoon");
        
        String toonTitle = String.valueOf(webtoon.get("toon_title"));
        error = toonTitle;
        String chapter = String.valueOf(viewData.get("view_title"));

        JSONArray view_image = (JSONArray) resultObj.get("view_image");
        for(int idx = 0 ; idx < view_image.size() ; idx++){
            String imgAddress = host + "/webtoondata/"+id+"/img/"+episode +"/"+ view_image.get(idx);
            String tmp = String.valueOf( view_image.get(idx) );
            String filename = new TextTransform().patternMaker("\\d+-\\d+|\\d+\\.\\d+|\\d+",tmp,-1);
//            filename = "0".repeat(-filename.length())+filename;
            filename = new TextTransform().lPad(filename,String.valueOf(view_image.size()).length());

            imgList.add (new Img(toonTitle,chapter,imgAddress, idx, filename) );
        }
        } catch (NullPointerException e){
            new ReportError(new Object(){}.getClass().getEnclosingClass().getName(),"[getImgPath]["+e.getClass().getName() + "]\n"+e, error+"\n");
            System.out.println(error);

        }
        return imgList;
    }

    private void textDataProcess(List apiresult) {
        Iterator iterator = apiresult.iterator();
        List<String> list = new ArrayList<>();
        TextData first = (TextData) apiresult.getFirst();
        String title = first.getTitle();
        while ( iterator.hasNext()){
            TextData tmp = (TextData) iterator.next();

            list.add(tmp.getData());
        }
        new SaveText(title, list.reversed()).save();;
    }
}


