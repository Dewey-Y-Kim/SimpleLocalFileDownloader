package main;

import main.java.file_downloader.Downloader;
import main.java.file_downloader.responseprocess.ApiAccess;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        String[] arg = {
                "https://t61.hoduhodu.com/webtoon/13979", //뒷계정트랩
                "https://t61.hoduhodu.com/webtoon/7182", //오늘부터최애
//                "https://t61.hoduhodu.com/webtoon/13438", //스위치
//                "https://t61.hoduhodu.com/webtoon/3541", //클럽
//                "https://t61.hoduhodu.com/webtoon/4238" , //펫
//                "https://t61.hoduhodu.com/webtoon/1408" , //오피스
//                "https://t61.hoduhodu.com/webtoon/1873", //보는남자
//                "https://t61.hoduhodu.com/webtoon/3012", //교실
//                "https://t61.hoduhodu.com/webtoon/3839", //교사
//                "https://t61.hoduhodu.com/webtoon/4569", //징벌
//                "https://t61.hoduhodu.com/webtoon/3807", //충전
//                "https://t61.hoduhodu.com/webtoon/4709",     //가짜였다
//                "https://t61.hoduhodu.com/webtoon/4744", //해변의
//                "https://t61.hoduhodu.com/webtoon/11171", //이세계최
//                "https://t61.hoduhodu.com/webtoon/7045", //주파수
//                "https://t61.hoduhodu.com/webtoon/7112", //구속
//                "https://t61.hoduhodu.com/webtoon/6924", //옐프
//                "https://t61.hoduhodu.com/webtoon/4751", // 쑬싸
//                "https://t61.hoduhodu.com/webtoon/5065", //요마서유
//                "https://t61.hoduhodu.com/webtoon/7210", //여기에
//                "https://t61.hoduhodu.com/webtoon/12707", //성지도
//                "https://t61.hoduhodu.com/webtoon/6079", //최면사
//                "https://t61.hoduhodu.com/webtoon/7315" //로판악녀

        };
        for (String str : arg){
            URI uri = new URI(str);
            String host= uri.getHost();
            Pattern pattern = Pattern.compile("^[^.]+\\.(.*?)\\.(.*)$");
            Matcher matcher = pattern.matcher(host);
            String key="";
            if(matcher.find()){
                key =matcher.group(1).substring(0,4);

            } else {
                key = host.substring(0,4);
            }

            switch (key){
                case "hodu":
                    new ApiAccess(str).start();

                    break;
                case "11to":
                    new Downloader(str).makeImg();
                    break;
                case "newt" :
                    new Downloader(str).makeFulltoMultifile();
                    break;
            }

        }
    }
}