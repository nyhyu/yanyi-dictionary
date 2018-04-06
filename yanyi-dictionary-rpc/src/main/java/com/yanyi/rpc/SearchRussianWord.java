package com.yanyi.rpc;

import com.yanyi.common.constants.Constants;
import com.yanyi.common.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchRussianWord {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRussianWord.class);

    public String getRussianWordInfoFromFLTRP(String russianWordName) {
        String url = String.format(Constants.RPC_URL, russianWordName, Constants.LOCAL_IP);
        try {
            return HttpRequestUtil.httpGet(url);
        } catch (Exception e) {
            LOGGER.error("Search Russian word failed, err = ", e);
            throw new RuntimeException(e);
        }
    }

    public String getRussianWordCss() {
        try {
            String cssFile = HttpRequestUtil.httpGet(Constants.CSS_URL);
            return cssFile;
        } catch (RuntimeException e) {
            LOGGER.error("Get css for russian error is ", e);
            throw new RuntimeException(e);
        }
    }

}
