package com.yanyi.service.impl;

import com.yanyi.common.model.UserInfo;
import com.yanyi.dao.UserInfoDAO;
import com.yanyi.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    private UserInfoDAO userInfoDAO;
    @Override
    public void registerUserInfo(UserInfo userInfo) {
        try {
            userInfoDAO.insertUserInfo(userInfo);
        } catch (RuntimeException e) {
            LOGGER.error("Insert user info failed, error is", e);
            throw new RuntimeException(e);
        }
    }
}
