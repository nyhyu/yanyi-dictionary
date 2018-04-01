package com.yanyi.common.model;

import java.util.List;

public class RussianWordSense {

    private String russianSenseName;

    private String chineseSenseName;

    private String flag;

    private List<RussionWordExample> exampleList;

    public String getRussianSenseName() {
        return russianSenseName;
    }

    public void setRussianSenseName(String russianSenseName) {
        this.russianSenseName = russianSenseName;
    }

    public String getChineseSenseName() {
        return chineseSenseName;
    }

    public void setChineseSenseName(String chineseSenseName) {
        this.chineseSenseName = chineseSenseName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<RussionWordExample> getExampleList() {
        return exampleList;
    }

    public void setExampleList(List<RussionWordExample> exampleList) {
        this.exampleList = exampleList;
    }
}
