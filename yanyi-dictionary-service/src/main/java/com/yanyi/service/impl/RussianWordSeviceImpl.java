package com.yanyi.service.impl;

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
            List<RussianWord> russianWordList = russianWordDAO.getRussianWordList(russianWord);
            return russianWordList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getRussionWordInfo(RussianWord russianWord) {

        try {
            String cssFile = searchRussianWord.getRussianWordCss();
            CSSUtil cssUtil = new CSSUtil(new ByteArrayInputStream(cssFile.getBytes()));
            String htmlText= searchRussianWord.getRussianWordInfoFromFLTRP(russianWord.getRussianPrototype());
            for(String className: Constants.CLASS_NAMES) {
                String style = cssUtil.getClass(className);
                htmlText.replaceAll("class=\"" + className + "\"", "style=\"" + style + "\"");
            }
            return htmlText;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
