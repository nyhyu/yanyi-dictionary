package com.yanyi.controller;

import com.alibaba.fastjson.JSON;
import com.yanyi.common.model.UserInfo;
import com.yanyi.common.utils.CglibBeanCopier;
import com.yanyi.request.UserInfoRequest;
import com.yanyi.response.BaseResponse;
import com.yanyi.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/yanyi_dict/user",  produces = {APPLICATION_JSON_VALUE})
public class UserInfoController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResponse resgisterUserInfo(UserInfoRequest userInfoRequest) {
        LOGGER.info("Register user info：params are {}", JSON.toJSONString(userInfoRequest));
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(userInfoRequest.getNickName()) || StringUtils.isEmpty(userInfoRequest.getAvatarUrl())
                || StringUtils.isEmpty(userInfoRequest.getGender())) {
            LOGGER.error("When register user info, params are invalid, params = " + JSON.toJSONString(userInfoRequest));
            return new BaseResponse(BaseResponse.ResponseCode.ClientErr.getCode(), "Request params invalid");
        }

        UserInfo userInfo = new UserInfo();
        try {
            CglibBeanCopier.copyProperties(userInfoRequest, userInfo);
            userInfoService.registerUserInfo(userInfo);
            response.setCode(BaseResponse.ResponseCode.OK.getCode());
            response.setMessage(BaseResponse.ResponseCode.OK.getMessage());
        } catch (RuntimeException e) {
            response.setCode(BaseResponse.ResponseCode.ServerErr.getCode());
            response.setMessage(BaseResponse.ResponseCode.ServerErr.getMessage());
        }

        return response;
    }

}
