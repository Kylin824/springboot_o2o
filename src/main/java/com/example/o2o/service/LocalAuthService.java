package com.example.o2o.service;

import com.example.o2o.dto.LocalAuthExecution;
import com.example.o2o.entity.LocalAuth;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;

public interface LocalAuthService {

    LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password);

    LocalAuth getLocalAuthByUserId(long userId);

    LocalAuthExecution registerOwner(LocalAuth localAuth,
                                     InputStream profileInputStream,
                                     String fileName) throws RuntimeException;

    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws RuntimeException;

    LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword);
}
