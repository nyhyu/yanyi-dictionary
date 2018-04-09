package com.yanyi.common.utils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleSheet;

public class CSSUtil {

    private CSSStyleSheet sheet = null;

    public CSSStyleSheet getSheet() {
        return sheet;
    }

    public void setSheet(CSSStyleSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * 指定文件流
     * @param stream
     */
    public CSSUtil(InputStream stream) {

        InputSource source = new InputSource(new InputStreamReader(stream));

        CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
        try {
            sheet = parser.parseStyleSheet(source, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取样式信息
     * @param className 样式名
     * @return 返回该样式的信息
     */
    public String getClass(String className){
        if(sheet == null){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        CSSRuleList rules = sheet.getCssRules();
        for (int i = 0; i < rules.getLength(); i++) {
            CSSRule rule = rules.item(i);
            Pattern pattern = Pattern.compile("\\."+className + "([^\\{]\\{.*[^\\}]\\})");
            Matcher matcher = pattern.matcher(rule.getCssText());
            while(matcher.find()){
                stringBuilder.append(matcher.group(1));
            }
        }

        stringBuilder.delete(stringBuilder.indexOf("{"), stringBuilder.indexOf("{") + 1);
        stringBuilder.delete(stringBuilder.indexOf("}"), stringBuilder.indexOf("}") + 1);

        return stringBuilder.toString();
    }
}
