package test.java;

import main.java.file_downloader.fileprocess.ReadText;
import main.java.file_downloader.imageprocess.ImageMaker;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test{

    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException, ParseException {
//        String test = "\n" +
//                "<meta name=\"title\" content=\"GTO 쇼난 14days 후지사와 토오루,일일툰, 무료웹툰\" />\n" +
//                "<meta name=\"subject\" content=\"GTO 쇼난 14days 후지사와 토오루,일일툰, 무료웹툰\" />\n" +
//                "<meta name=\"publisher\" content=\"일일툰\" />\n" +
//                "<meta name=\"author\" content=\"후지사와 토오루\" />\n" +
//                "<meta name=\"robots\" content=\"index,follow\" />\n" +
//                "<meta name=\"keywords\" content=\"GTO 쇼난 14days 후지사와 토오루,웹툰,만화,코믹,무료,Free,Webtoon,Comic,Comix,Manga,ani,animation,애니,최신,최신화,무료만화,원펀맨,무료애니,무료웹툰\" />\n" +
//                "<meta name=\"description\" content=\"GTO 쇼난 14days 후지사와 토오루,\" />\n" +
//                "<link rel=\"image_src\" href=\"//11toonimg2.spotv24.com/data/toon_category/8288\" />\n" +
//                "<link rel=\"canonical\" href=\"//11toon144.com/bbs/board.php?bo_table=toons&stx=GTO%20%EC%87%BC%EB%82%9C%2014days&search=1&is=8288\" />\n" +
//                "\n" +
//                "<!-- for Facebook -->\n" +
//                "<meta property=\"og:title\" content=\"GTO ";

        String[] source = {"http://www.pl3040.com/kr//11/4076/245547/0b9bd122d3bb6f25392da63f60ea89b2.jpg","http://www.pl3040.com/kr//11/4076/245547/73dfc3dcafc45a9ba35c093d50e8efa2.jpg","http://www.pl3040.com/kr//11/4076/245547/010141767d0ed0d5c1a87c17b77a46bf.jpg","http://www.pl3040.com/kr//11/4076/245547/10e5ed9e9f7694c6e438d54cec212eec.jpg","http://www.pl3040.com/kr//11/4076/245547/bdeebcc0943732d3db3a1960fbaa7157.jpg","http://www.pl3040.com/kr//11/4076/245547/80a41c65d5a6335d04f4fdb860992ea8.jpg","http://www.pl3040.com/kr//11/4076/245547/42389f1a2e0e18440e7fd2f49382a785.jpg","http://www.pl3040.com/kr//11/4076/245547/63c2c0c5a0d12b928e5cd0530f5c7f9f.jpg","http://www.pl3040.com/kr//11/4076/245547/92832b1ae425ba21f833f6bd86bdd591.jpg","http://www.pl3040.com/kr//11/4076/245547/89830704f5d56c6528d3b4cd64c83aef.jpg","http://www.pl3040.com/kr//11/4076/245547/216e46888c3bb4309acea3e2e22814b3.jpg","http://www.pl3040.com/kr//11/4076/245547/cebf10a9f247b14cb3b7d42d77737409.jpg","http://www.pl3040.com/kr//11/4076/245547/5a10b5ddca0455c14e5298a389712d33.jpg","http://www.pl3040.com/kr//11/4076/245547/67ccf1add3c4f5190359f089c44cc1ce.jpg","http://www.pl3040.com/kr//11/4076/245547/30f079f9d946bb8728a793b63e4ccdf0.jpg","http://www.pl3040.com/kr//11/4076/245547/20c3b300b75fc4aa70e2683d63360dee.jpg","http://www.pl3040.com/kr//11/4076/245547/7cf65ca1a9b2d1167e5763244cdcff80.jpg","http://www.pl3040.com/kr//11/4076/245547/7e71e8372b92a910e4b24936c08401fb.jpg","http://www.pl3040.com/kr//11/4076/245547/a96c55dd41c82635519c4b3df0425584.jpg","http://www.pl3040.com/kr//11/4076/245547/37205a17af9ca517c7db439a7916bb52.jpg","http://www.pl3040.com/kr//11/4076/245547/b146776ec0c9a15cd30a32ee96892d52.jpg","http://www.pl3040.com/kr//11/4076/245547/7bde5be6cda0036afbf47a6bba27bfb8.jpg","http://www.pl3040.com/kr//11/4076/245547/0817936e1dabc701c4426538d09c5463.jpg","http://www.pl3040.com/kr//11/4076/245547/2d2c64c5e6390965801034a32d1cd9b8.jpg","http://www.pl3040.com/kr//11/4076/245547/47dd4261349734b48e814917820c91d7.jpg","http://www.pl3040.com/kr//11/4076/245547/3fb237c1e2ce02d5dff16b21a5b6d44d.jpg","http://www.pl3040.com/kr//11/4076/245547/7f7b25c4d1b63863a6c17493232bcd5e.jpg","http://www.pl3040.com/kr//11/4076/245547/160e32d3937cae2e0f54d9858a84196b.jpg","http://www.pl3040.com/kr//11/4076/245547/fc6b148225e3988af7f78d64cc6d9dc1.jpg","http://www.pl3040.com/kr//11/4076/245547/8e8e37639371ff6b16e787d1ad5d386d.jpg","http://www.pl3040.com/kr//11/4076/245547/4aa53d37c0262d83e9ad85043b91484a.jpg","http://www.pl3040.com/kr//11/4076/245547/fb53e4a915deffd0f15536a0f6fd751c.jpg","http://www.pl3040.com/kr//11/4076/245547/41a46fb89eb57454cdc5be76177d699e.jpg","http://www.pl3040.com/kr//11/4076/245547/0b9bd122d3bb6f25392da63f60ea89b2.jpg","http://www.pl3040.com/kr//11/4076/245547/73dfc3dcafc45a9ba35c093d50e8efa2.jpg","http://www.pl3040.com/kr//11/4076/245547/010141767d0ed0d5c1a87c17b77a46bf.jpg","http://www.pl3040.com/kr//11/4076/245547/10e5ed9e9f7694c6e438d54cec212eec.jpg","http://www.pl3040.com/kr//11/4076/245547/bdeebcc0943732d3db3a1960fbaa7157.jpg","http://www.pl3040.com/kr//11/4076/245547/80a41c65d5a6335d04f4fdb860992ea8.jpg","http://www.pl3040.com/kr//11/4076/245547/42389f1a2e0e18440e7fd2f49382a785.jpg","http://www.pl3040.com/kr//11/4076/245547/63c2c0c5a0d12b928e5cd0530f5c7f9f.jpg","http://www.pl3040.com/kr//11/4076/245547/92832b1ae425ba21f833f6bd86bdd591.jpg","http://www.pl3040.com/kr//11/4076/245547/89830704f5d56c6528d3b4cd64c83aef.jpg","http://www.pl3040.com/kr//11/4076/245547/216e46888c3bb4309acea3e2e22814b3.jpg","http://www.pl3040.com/kr//11/4076/245547/cebf10a9f247b14cb3b7d42d77737409.jpg","http://www.pl3040.com/kr//11/4076/245547/5a10b5ddca0455c14e5298a389712d33.jpg","http://www.pl3040.com/kr//11/4076/245547/67ccf1add3c4f5190359f089c44cc1ce.jpg","http://www.pl3040.com/kr//11/4076/245547/30f079f9d946bb8728a793b63e4ccdf0.jpg","http://www.pl3040.com/kr//11/4076/245547/20c3b300b75fc4aa70e2683d63360dee.jpg","http://www.pl3040.com/kr//11/4076/245547/7cf65ca1a9b2d1167e5763244cdcff80.jpg","http://www.pl3040.com/kr//11/4076/245547/7e71e8372b92a910e4b24936c08401fb.jpg","http://www.pl3040.com/kr//11/4076/245547/a96c55dd41c82635519c4b3df0425584.jpg","http://www.pl3040.com/kr//11/4076/245547/37205a17af9ca517c7db439a7916bb52.jpg","http://www.pl3040.com/kr//11/4076/245547/b146776ec0c9a15cd30a32ee96892d52.jpg","http://www.pl3040.com/kr//11/4076/245547/7bde5be6cda0036afbf47a6bba27bfb8.jpg","http://www.pl3040.com/kr//11/4076/245547/0817936e1dabc701c4426538d09c5463.jpg","http://www.pl3040.com/kr//11/4076/245547/2d2c64c5e6390965801034a32d1cd9b8.jpg","http://www.pl3040.com/kr//11/4076/245547/47dd4261349734b48e814917820c91d7.jpg","http://www.pl3040.com/kr//11/4076/245547/3fb237c1e2ce02d5dff16b21a5b6d44d.jpg","http://www.pl3040.com/kr//11/4076/245547/7f7b25c4d1b63863a6c17493232bcd5e.jpg","http://www.pl3040.com/kr//11/4076/245547/160e32d3937cae2e0f54d9858a84196b.jpg","http://www.pl3040.com/kr//11/4076/245547/fc6b148225e3988af7f78d64cc6d9dc1.jpg","http://www.pl3040.com/kr//11/4076/245547/8e8e37639371ff6b16e787d1ad5d386d.jpg","http://www.pl3040.com/kr//11/4076/245547/4aa53d37c0262d83e9ad85043b91484a.jpg","http://www.pl3040.com/kr//11/4076/245547/fb53e4a915deffd0f15536a0f6fd751c.jpg","http://www.pl3040.com/kr//11/4076/245547/41a46fb89eb57454cdc5be76177d699e.jpg"};
        for (int i = 0 ; i < source.length; i++){
            new ImageMaker(source[i],"클레이"+i).make();
            System.out.println("크레이"+i);
        }
    }
    public static void runTread(){
        List list = new ArrayList<>();
        for(int i=0; i<5; i++) list.add(i);
        Iterator idx = list.iterator();
        System.out.println(list.size());
        Thread tread1 = new shop(idx, "tread1") {};
        Thread tread2 = new shop(idx, "tread2") {};
        Thread tread3 = new shop(idx, "tread3") {};
        tread3.start();;
        tread1.start();
        tread2.start();
    }

    public static String patternMaker(String originalPattern, String str){
        Pattern pattern = Pattern.compile(originalPattern);
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while ( matcher.find()){
            text= matcher.group();
        }
        return text.replaceFirst("\\S*=\"","").replaceFirst("\"(.*)","");

    }


}
class shop extends Thread{
    Iterator i;
    String name;
    shop(Iterator i, String name){
        this.i = i;
        this.name = name;
    }
    @Override
    public void run(){
        while(i.hasNext()){
            System.out.printf("%s\t%d\n",name, i.next());
        }
    }

}