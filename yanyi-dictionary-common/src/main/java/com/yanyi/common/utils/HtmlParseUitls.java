package com.yanyi.common.utils;


import com.alibaba.fastjson.JSON;
import com.yanyi.common.model.RussianWord;
import com.yanyi.common.model.RussianWordSense;
import com.yanyi.common.model.RussionWordExample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class HtmlParseUitls {

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

        Element element = elements.get(0);
        Elements a1 = element.getElementsByClass("Pos");
        Elements a3 = element.getElementsByClass("HeadWord");
        System.out.println("HeadWord = " + a1.text());
        Elements a2 = element.getElementsByClass("Sense");
        Element e1 = a2.get(0);
        Elements exs = e1.getElementsByClass("Example");
        //String str = e1.ownText();
        List<RussianWord> russianWordList = HtmlParseUitls.parseHtml(input);
        System.out.println(JSON.toJSONString(russianWordList));
    }

    /**
     *  解析 HTML 文本为 RussianWord 对象
     * @param originalHtml 原始文本
     * @return RussianWord
     */
    public static List<RussianWord> parseHtml(File originalHtml) {
        List<RussianWord> russianWordList = new ArrayList<>();
        //Document doc = Jsoup.parse(originalHtml);
        Document doc = null;
        try {
            doc = Jsoup.parse(originalHtml,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements entries = doc.getElementsByClass("Entry");
        for(Element entry : entries) {
            RussianWord russianWord = new RussianWord();
            Elements helloWorlds = entry.getElementsByClass("HeadWord");
            if(helloWorlds.size() <= 0) {
                continue;
            } else if(helloWorlds.size() > 1) {
                russianWord.setType(helloWorlds.get(1).text());
            }
            russianWord.setWordName(helloWorlds.get(0).text());
            Elements posWord = entry.getElementsByClass("Pos");
            russianWord.setFlag(posWord.text());
            Elements senses = entry.getElementsByClass("Sense");
            List<RussianWordSense> russianWordSenseList = new ArrayList<>();
            for(Element sense : senses) {
                RussianWordSense russianWordSense = new RussianWordSense();
                russianWordSense.setRussianSenseName(sense.ownText());
                Elements trans = sense.getElementsByClass("Trans");
                Elements posSense = sense.getElementsByClass("Pos");
                russianWordSense.setFlag(posSense.text());
                russianWordSense.setChineseSenseName(trans.get(0).text());
                Elements examples = sense.getElementsByClass("Example");
                List<RussionWordExample> russionWordExampleList = new ArrayList<>();
                for(Element examle : examples) {
                    RussionWordExample russionWordExample = new RussionWordExample();
                    Elements ex = examle.getElementsByClass("Ex");
                    Elements tran = examle.getElementsByClass("Trans");
                    russionWordExample.setRussianExample(ex.get(0).text());
                    russionWordExample.setChineseExample(tran.get(0).text());
                    russionWordExampleList.add(russionWordExample);
                }
                russianWordSense.setExampleList(russionWordExampleList);
                russianWordSenseList.add(russianWordSense);
            }
            russianWord.setRussianWordSenseList(russianWordSenseList);
            russianWordList.add(russianWord);
        }
        return russianWordList;
    }
}
