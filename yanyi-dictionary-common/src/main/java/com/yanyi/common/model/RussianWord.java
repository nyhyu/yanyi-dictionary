package com.yanyi.common.model;

public class RussianWord {

    private int id;

    private String russianPrototype;

    private String changeType;

    private String flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRussianPrototype() {
        return russianPrototype;
    }

    public void setRussianPrototype(String russianPrototype) {
        this.russianPrototype = russianPrototype;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public RussianWord() {
    }

    public RussianWord(String russianPrototype) {
        this.russianPrototype = russianPrototype;
    }
}
