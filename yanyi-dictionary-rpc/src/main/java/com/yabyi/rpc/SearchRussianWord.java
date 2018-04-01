package com.yabyi.rpc;

import com.yanyi.common.utils.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class SearchRussianWord {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRussianWord.class);

    @Value("${fltrp.search.url}")
    private String rpcUrl;

    @Value("${localhost.ip}")
    private String localIp;

    public String getRussianWordInfoFromFLTRP(String russianWordName) {
        String utl = String.format(rpcUrl, russianWordName, localIp);
        try {
            return HttpRequestUtils.httpGet(utl);
        } catch (Exception e) {
            LOGGER.error("Search Russian word failed, err = ", e);
            throw new RuntimeException(e);
        }
    }
}
