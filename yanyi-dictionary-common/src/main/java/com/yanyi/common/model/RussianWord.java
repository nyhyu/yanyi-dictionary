package com.yanyi.common.model;

import java.util.List;

public class RussianWord {

    private String wordName;

    private String type;

    private String flag;

    private List<RussianWordSense> russianWordSenseList;

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<RussianWordSense> getRussianWordSenseList() {
        return russianWordSenseList;
    }

    public void setRussianWordSenseList(List<RussianWordSense> russianWordSenseList) {
        this.russianWordSenseList = russianWordSenseList;
    }
}
