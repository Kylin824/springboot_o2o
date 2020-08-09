package com.example.o2o.controller.shopadmin;

import com.example.o2o.dto.LocalAuthExecution;
import com.example.o2o.entity.LocalAuth;
import com.example.o2o.enums.LocalAuthStateEnum;
import com.example.o2o.service.LocalAuthService;
import com.example.o2o.util.CodeUtil;
import com.example.o2o.util.HttpServletRequestUtil;
import com.example.o2o.util.MDUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shopadmin")
public class OwnerAuthController {

    @Autowired
    private LocalAuthService localAuthService;

    @PostMapping("/login")
    private Object ownerLoginCheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");

        if (userName != null && password != null) {
            password = DigestUtils.md5Hex(password);
            LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(userName, password);
            if (localAuth != null) {
                if (localAuth.getPersonInfo().getUserType() == 2) {
                    modelMap.put("success", true);
                    request.getSession().setAttribute("user", localAuth.getPersonInfo());
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "permission denied, not shop owner");
                }
            }
            else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "wrong userName or password");
            }
        }
        else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "userName and password cant be null");
        }
        return modelMap;
    }

    @PostMapping("/register")
    private Map<String, Object> ownerRegister(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "wrong captcha!");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        LocalAuth owner;
        String localAuthStr = HttpServletRequestUtil.getString(request, "localAuthStr");
        MultipartFile ownerImg;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            ownerImg =  multipartRequest.getFile("thumbnail");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "need thumbnail image!");
            return modelMap;
        }
        try {
            owner = mapper.readValue(localAuthStr, LocalAuth.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        if (owner != null && ownerImg != null) {
            try {
                LocalAuthExecution res = localAuthService.registerOwner(owner, ownerImg.getInputStream(), ownerImg.getOriginalFilename());
                if (res.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", res.getStateInfo());
                }
            } catch (RuntimeException | IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "owner info cant be null");
        }
        return modelMap;
    }}
