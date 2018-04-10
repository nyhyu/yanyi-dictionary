package com.yanyi.dao;

import com.yanyi.common.model.RussianWord;

import java.util.List;

public interface RussianWordDAO {

    List<RussianWord> getRussianWordList(RussianWord russianWord);
}
