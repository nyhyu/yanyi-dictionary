package com.yanyi.common.utils;


import com.alibaba.fastjson.JSON;
import com.yanyi.common.model.RussianWord;
import com.yanyi.common.model.RussianWordSense;
import com.yanyi.common.model.RussionWordExample;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUitl {

    public static void main(String [] args) {
        File input = new File("C:\\Users\\niuyueheng\\Desktop\\yanyi-dict\\three.html");
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elements = doc.getElementsByClass("Entry");
//        System.out.println(elements.get(0));

        Element element = elements.get(3);
        Elements a1 = element.getElementsByClass("Pos");
        Elements a3 = element.getElementsByClass("HeadWord");
        System.out.println("a1 = " + a1);
        Elements a2 = element.getElementsByClass("Sense");
        Element e1 = a2.get(0);
        Elements exs = e1.getElementsByClass("Example");
        //String str = e1.ownText();/System.out.println(e1.text());
//        List<RussianWord> russianWordList = HtmlParseUitl.parseHtml(input);
//        System.out.println("size = " + russianWordList.size());
//        for(RussianWord russianWord: russianWordList) {
//            System.out.println(JSON.toJSONString(russianWord));
//        }
//        System.out.println(JSON.toJSONString(russianWordList));
    }

}
