package com.yanyi.service;

import com.yanyi.common.model.RussianWord;

import java.util.List;

public interface RussianWordSevice {

    List<RussianWord> getRussianWordList(RussianWord russianWord);

    String getRussionWordInfo(RussianWord russianWord);
}
