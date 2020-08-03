package com.example.o2o.service.impl;

import com.example.o2o.dao.LocalAuthDao;
import com.example.o2o.dto.LocalAuthExecution;
import com.example.o2o.entity.LocalAuth;
import com.example.o2o.service.LocalAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    @Autowired
    private LocalAuthDao localAuthDao;

    @Override
    public LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password) {
        return null;
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return null;
    }

    @Override
    public LocalAuthExecution register(LocalAuth localAuth, CommonsMultipartFile profileImg) throws RuntimeException {
        return null;
    }

    @Override
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws RuntimeException {
        return null;
    }

    @Override
    public LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword) {
        return null;
    }
}
