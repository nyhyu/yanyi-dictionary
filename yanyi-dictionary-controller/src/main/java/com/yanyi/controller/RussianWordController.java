package com.yanyi.controller;

import com.alibaba.fastjson.JSON;
import com.yanyi.common.model.RussianWord;
import com.yanyi.common.utils.CglibBeanCopier;
import com.yanyi.request.RussianWordRequest;
import com.yanyi.response.BaseResponse;
import com.yanyi.response.RussianWordResponse;
import com.yanyi.service.RussianWordSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/yanyi_dict/word",  produces = {APPLICATION_JSON_VALUE})
public class RussianWordController {

    private static Logger LOGGER = LoggerFactory.getLogger(RussianWordController.class);

    @Resource
    private RussianWordSevice russianWordSevice;

    @ResponseBody
    @RequestMapping(value = "/prototype_list", method = RequestMethod.POST)
    public RussianWordResponse getRussianWordList(@Valid @RequestBody RussianWordRequest russianWordRequest) {
        LOGGER.info("Get russian prototype list：params are {}", JSON.toJSONString(russianWordRequest));
        RussianWordResponse response = new RussianWordResponse();
        try {
            RussianWord russianWord = new RussianWord();
            CglibBeanCopier.copyProperties(russianWordRequest, russianWord);
            List<RussianWord> russianWordList = russianWordSevice.getRussianWordList(russianWord);
            response.setRussianWordList(russianWordList);
            response.setCode(BaseResponse.ResponseCode.OK.getCode());
            response.setMessage(BaseResponse.ResponseCode.OK.getMessage());

        } catch (RuntimeException e) {
            response.setCode(BaseResponse.ResponseCode.ServerErr.getCode());
            response.setMessage(BaseResponse.ResponseCode.ServerErr.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public RussianWordResponse getRussianWordInfo(@Valid @RequestBody RussianWordRequest russianWordRequest) {
        LOGGER.info("Get russian word info：params are {}", JSON.toJSONString(russianWordRequest));
        RussianWordResponse response = new RussianWordResponse();
        try {
            RussianWord russianWord = new RussianWord();
            CglibBeanCopier.copyProperties(russianWordRequest, russianWord);
            response.setHtmlText(russianWordSevice.getRussionWordInfo(russianWord));
            response.setCode(BaseResponse.ResponseCode.OK.getCode());
            response.setMessage(BaseResponse.ResponseCode.OK.getMessage());
        } catch (RuntimeException e) {
            response.setCode(BaseResponse.ResponseCode.ServerErr.getCode());
            response.setMessage(BaseResponse.ResponseCode.ServerErr.getMessage());
        }
        return response;
    }

}
