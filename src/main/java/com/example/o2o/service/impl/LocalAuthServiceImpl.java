package com.example.o2o.service.impl;

import com.example.o2o.dao.LocalAuthDao;
import com.example.o2o.dao.PersonInfoDao;
import com.example.o2o.dto.LocalAuthExecution;
import com.example.o2o.entity.LocalAuth;
import com.example.o2o.entity.PersonInfo;
import com.example.o2o.enums.LocalAuthStateEnum;
import com.example.o2o.exceptions.LocalAuthOperationException;
import com.example.o2o.exceptions.PersonInfoOperationException;
import com.example.o2o.service.LocalAuthService;
import com.example.o2o.util.ImageUtil;
import com.example.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    @Autowired
    private LocalAuthDao localAuthDao;

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password) {
        return null;
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return null;
    }

    @Override
    @Transactional
    public LocalAuthExecution registerOwner(LocalAuth localAuth, InputStream profileInputStream, String fileName) throws RuntimeException {
        if (localAuth == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            PersonInfo user = localAuth.getPersonInfo();
            user.setCreateTime(new Date());
            user.setLastEditTime(new Date());
            user.setEnableStatus(0);
            user.setUserType(2);
            int effectedNum = personInfoDao.insertPersonInfo(user);

            if (effectedNum <= 0) {
                throw new PersonInfoOperationException("personinfo insert failed");
            }
            else {
                if (profileInputStream != null) {
                    try {
                        addProfile(user, profileInputStream, fileName);
                    } catch (Exception e) {
                        throw new PersonInfoOperationException("add personinfo img failed");
                    }
                    effectedNum = personInfoDao.updatePersonInfo(user);
                    if (effectedNum <= 0) {
                        throw new PersonInfoOperationException("update personinfo failed");
                    }
                }
            }

            localAuth.setUserId(user.getUserId());
            localAuth.setPassword();
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            effectedNum = localAuthDao.insertLocalAuth(localAuth);
            if (effectedNum <= 0) {
                throw new LocalAuthOperationException("insert local auth failed");
            }

        } catch (Exception e) {
            e.printStackTrace();;
            throw new LocalAuthOperationException("add local auth error : " + e.getMessage());
        }
        return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);

    }

    private void addProfile(PersonInfo personInfo, InputStream profileInputStream, String fileName) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImgPath(personInfo.getUserId());
        String shopImgAddr = ImageUtil.generateThumbnailPlus(profileInputStream, fileName, dest);
        personInfo.setProfileImg(shopImgAddr);
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
