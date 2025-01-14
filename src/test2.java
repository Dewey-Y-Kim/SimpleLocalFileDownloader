import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test2 {
    public static void main(String[] args){
        String str = "<li><a href=\"https://newtoki.biz/book/20270/644256\"><i class=\"xi-check xi-x\"></i>화산귀환-1566화<span class=\"date\">2023-08-30</span></a></li>,";
        Pattern urlPattern = Pattern.compile("href=\"(.*?)\"");
        Pattern titlePattern = Pattern.compile("</i>(.*?)<span");
        Matcher titleMatcher = titlePattern.matcher(str);
                Matcher urlMatcher = urlPattern.matcher(str);
//                str = getbody(str);

                while (titleMatcher.find()){
                    String txt=titleMatcher.group();

                }
                while (urlMatcher.find()){
                    String txt = urlMatcher.group();
                    System.out.println(txt);
                }

//                result.add(titleMatcher.group(1));
//                result.add(urlMatcher.group(1));
//                result.add(str);
            }
        }
