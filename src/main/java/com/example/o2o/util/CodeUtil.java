package com.example.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {

        // 页面初始化验证码后存在session中的值
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        // 用户输入的验证码值
        String verifyCodeReceived = HttpServletRequestUtil.getString(request, "verifyCodeReceived");

        System.out.println("verifyCodeExpected : " + verifyCodeExpected);
        System.out.println("verifyCodeReceived : " + verifyCodeReceived);

        // return verifyCodeReceived != null && verifyCodeReceived.equalsIgnoreCase(verifyCodeExpected);

        if (verifyCodeReceived == null || !verifyCodeReceived.equalsIgnoreCase(verifyCodeExpected)) {
            return false;
        }
        return true;


    }
}
