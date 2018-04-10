package com.yanyi.response;

import com.yanyi.common.model.RussianWord;

import java.util.List;

public class RussianWordResponse extends BaseResponse {

    private List<RussianWord> russianWordList;

    private String htmlText;

    public List<RussianWord> getRussianWordList() {
        return russianWordList;
    }

    public void setRussianWordList(List<RussianWord> russianWordList) {
        this.russianWordList = russianWordList;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }
}
