package test.java;

import main.java.file_downloader.connector.Connector;
import main.java.file_downloader.textprocess.TextTransform;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test2{
    @Test
    public void test(){
        String s = "";
        Pattern pattern = Pattern.compile("(?>\"og:title\" content=\")(.*)\" />");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            System.out.println(matcher.group(1));
        }
    }
    @Test
    public void getResponse(){
        String address = "";
        Connector connector = null;
        String result = null;
        try {
            connector = new Connector(address);
            result = connector.getResult();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        result = new TextTransform().TagToEnd("body", result);

        //        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(result);
//
//        if (matcher.find()) {
//            String bodyContent = matcher.group(1).replaceAll("\\s\\s","").replaceAll("\\n\\n","\n");
//            System.out.println("Body Content: " + bodyContent);
//        } else {
//            System.out.println("No <body> tag found.");
//        }
        System.out.println(result);
    }

    @Test
    public void getImg(){
        String str ="                        <li>\n" +
                "                <input class=\"bulk-checkbox\" type=\"checkbox\" aria-labelledby=\"g6276500605894656\"><i></i>\n" +
                "                <button type=\"button\" class=\"episode is-series\" data-episode-id=\"6276500605894656\" data-episode-type=\"g\" data-purchased=\"false\" \n" +
                "                    data-free=\"true\" data-viewed=\"false\" data-locked=\"false\" data-ga-on=\"click\" data-ga-event-action=\"goto-episode\" data-ga-event-label=\"바나나툰\" \n" +
                "                    onclick=\"location.href='./board.php?bo_table=toons&wr_id=1742751&stx=고블린 슬레이어 외전 : 이어 원&is=7583'\"\n" +
                "                >\n" +
                "                    <div class=\"banner-wrap\">\n" +
                "                        <div class=\"episode-banner\" style=\"background-image: url('//11toon7.com/01/1742751.webp');\"></div>\n" +
                "                        <div class=\"d-day-wrap\">\n" +
                "                            <span class=\"lock\"></span>\n" +
                "                            <span class=\"d-day\"></span>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div id=\"g6276500605894656\" class=\"episode-seq\">\n" +
                "                        <div class=\"episode-name ellipsis\"></div>\n" +
                "                        <div class=\"episode-title ellipsis\">고블린 슬레이어 외… 이어 원 91화</div>\n" +
                "                        <div class=\"free-date\">23.09.06<font color=\"red\">(22)</font></div>\n" +
                "                    </div>\n" +
                "                    <div class=\"episode-price\">\n" +
                "                        <span class=\"list_hit \">10177                            \n" +
                "                        </span>\n" +
                "                    </div>\n" +
                "<!--                    <div class=\"episode-price\">-->\n" +
                "<!--                        <span>무료</span>-->\n" +
                "<!--                    </div>-->\n" +
                "                </button>\n" +
                "            </li>"
                +"                        <li>\n" +
                "                <input class=\"bulk-checkbox\" type=\"checkbox\" aria-labelledby=\"g6276500605894656\"><i></i>\n" +
                "                <button type=\"button\" class=\"episode is-series\" data-episode-id=\"6276500605894656\" data-episode-type=\"g\" data-purchased=\"false\" \n" +
                "                    data-free=\"true\" data-viewed=\"false\" data-locked=\"false\" data-ga-on=\"click\" data-ga-event-action=\"goto-episode\" data-ga-event-label=\"바나나툰\" \n" +
                "                    onclick=\"location.href='1./board.php?bo_table=toons&wr_id=1742751&stx=1고블린 슬레이어 외전 : 이어 원&is=7583'\"\n" +
                "                >\n" +
                "                    <div class=\"banner-wrap\">\n" +
                "                        <div class=\"episode-banner\" style=\"background-image: url('//11toon7.com/01/1742751.webp');\"></div>\n" +
                "                        <div class=\"d-day-wrap\">\n" +
                "                            <span class=\"lock\"></span>\n" +
                "                            <span class=\"d-day\"></span>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div id=\"g6276500605894656\" class=\"episode-seq\">\n" +
                "                        <div class=\"episode-name ellipsis\"></div>\n" +
                "                        <div class=\"episode-title ellipsis\">고블린 슬레이어 외… 이어 원 91화</div>\n" +
                "                        <div class=\"free-date\">23.09.06<font color=\"red\">(22)</font></div>\n" +
                "                    </div>\n" +
                "                    <div class=\"episode-price\">\n" +
                "                        <span class=\"list_hit \">10177                            \n" +
                "                        </span>\n" +
                "                    </div>\n" +
                "<!--                    <div class=\"episode-price\">-->\n" +
                "<!--                        <span>무료</span>-->\n" +
                "<!--                    </div>-->\n" +
                "                </button>\n" +
                "            </li>";

        ;
        String path1 =  "<button[^>]*onclick=\"location\\.href='([^']+)'\"";
        String path2 = "<button[^>]*location\\.href='([^']+)'";
        String path4 = "<button[^>]*href='([^']+)'";

        // <div class="episode-title ellipsis">고블린 슬레이어 외… 이어 원 91화</div>
        String chapter = "<div[^>]*([^1]+)</div>";
        String chapter2 = "<div class=\"episode-title ellipsis\">([^<]+)</div>";
        // [^>]* : "닫는 꺾쇠(>)가 나오기 전까지의 모든 문자"
        String chapter3 = "<div class=\"episode-title [^>]*>([^<]+)</div>";

        String result = new TextTransform().patternMaker(path1, str);
        System.out.println( result);
//        Pattern pattern = Pattern.compile(path4);
//
////        System.out.println(str+"\n");
//        Matcher matcher = pattern.matcher(str);
////
//        while (matcher.find()) {
//            String url = matcher.group(1);
//            System.out.println("추출된 URL: \n" + url+"\n");
//        }
////        Pattern pattern = Pattern.compile(regex);
////        Matcher matcher = pattern.matcher(str);
////
////        while (matcher.find()) {
////            String url = matcher.group(1);
////            System.out.println("추출된 URL: " + url);
////        }
    }
    @Test
    public void regex(){
        String url = null;
//        문자들/+ 숫자
//        String pattern = "(?<=[\\D+]/)\\d+";
//        문자 + /다음 숫자만
//        String pattern = "(?<=[\\D]/)\\d+";
//        / 다음 숫자만
//        12725
//        String pattern = "(?<=/)\\d+(?=/)";
        String pattern = "(?<=/)(\\d+)(?=/)?";
//        w1btoon/12725/4
//        String pattern = "(?<=/)[^.]+(?=/)";
        // w1ebtoon
//        String pattern = "(?<=/)[^./]+(?=/)";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(url);
        while (matcher.find()){
            System.out.println(matcher.group());
        }

    }
}
