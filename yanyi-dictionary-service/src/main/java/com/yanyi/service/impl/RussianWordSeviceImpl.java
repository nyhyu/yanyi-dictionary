package com.yanyi.service.impl;

import com.alibaba.fastjson.JSON;
import com.yanyi.common.constants.Constants;
import com.yanyi.common.model.RussianWord;
import com.yanyi.common.utils.CSSUtil;
import com.yanyi.dao.RussianWordDAO;
import com.yanyi.rpc.SearchRussianWord;
import com.yanyi.service.RussianWordSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class RussianWordSeviceImpl implements RussianWordSevice {

    private static Logger LOGGER = LoggerFactory.getLogger(RussianWordSeviceImpl.class);

    private SearchRussianWord searchRussianWord = new SearchRussianWord();

    @Resource
    private RussianWordDAO russianWordDAO;

    @Override
    public List<RussianWord> getRussianWordList(RussianWord russianWord) {
        try {
            boolean hasAccent = false;
            List<RussianWord> russianWordList = new ArrayList<>();
            for(String accent : Constants.HAS_ACCENTS) {
                if (russianWord.getRussianPrototype().contains(accent)) {
                    hasAccent = true;
                    break;
                }
            }

            if(!hasAccent) {
                russianWordList.addAll(russianWordDAO.getRussianWordList(russianWord));
                for (int i=0; i < Constants.NO_ACCENTS.length; i++) {
                    if (russianWord.getRussianPrototype().contains(Constants.NO_ACCENTS[i])) {
                        int idx = russianWord.getRussianPrototype().indexOf(Constants.NO_ACCENTS[i]);
                        StringBuilder stringBuilder = new StringBuilder(russianWord.getRussianPrototype());
                        stringBuilder.replace(idx, idx + 1, Constants.HAS_ACCENTS[i]);
                        russianWordList.addAll(russianWordDAO.getRussianWordList(new RussianWord(stringBuilder.toString())));
                    }
                }
            } else {
                russianWordList.addAll(russianWordDAO.getRussianWordList(russianWord));
            }

            LOGGER.info("russianWordList = " + JSON.toJSONString(russianWordList));
            return russianWordList;
        } catch (RuntimeException e) {
            LOGGER.error("Get russian word list failed, error is ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getRussionWordInfo(RussianWord russianWord) {

        try {
            String cssFile = searchRussianWord.getRussianWordCss();
            LOGGER.info("cssFile = " + cssFile);
            CSSUtil cssUtil = new CSSUtil(new ByteArrayInputStream(cssFile.getBytes()));
            String htmlText= searchRussianWord.getRussianWordInfoFromFLTRP(russianWord.getRussianPrototype());
            LOGGER.info("originalText = " + htmlText);
            for(String className: Constants.CLASS_NAMES) {
                String style = cssUtil.getClass(className);
                htmlText = htmlText.replaceAll("class=\"" + className + "\"", "style=\"" + style + "\"");
            }
            LOGGER.info("AfterText = " + htmlText);
            return htmlText;
        } catch (RuntimeException e) {
            LOGGER.error("Get russian word info failed, error is ", e);
            throw new RuntimeException(e);
        }
    }
}
