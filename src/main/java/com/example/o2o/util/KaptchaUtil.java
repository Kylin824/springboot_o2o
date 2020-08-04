package com.example.o2o.util;

import com.google.code.kaptcha.Constants;
import javax.servlet.http.HttpServletRequest;

public class KaptchaUtil {

    public static boolean checkKaptcha(HttpServletRequest req) {
        String sessionKaptcha = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String receivedKaptcha = HttpServletRequestUtil.getString(req, "verifyCodeReceived");
        if (receivedKaptcha == null || !receivedKaptcha.equalsIgnoreCase(sessionKaptcha)) {
            return false;
        }
        return true;
    }
}
