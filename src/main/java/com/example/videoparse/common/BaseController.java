package com.example.videoparse.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2019/9/26 18:10
 * @description
 */
@Controller
public class BaseController {
    protected static String SORT_FILED = "sort_field";

    protected static String MODEL_USER = "login_user";

/*
    protected File staticDir;

    @Value("${staticFilePath}")
    public void setStaticDir(String dir) {

        File file = new File(dir);

        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }

        staticDir = file;
    }*/

    /**
     * 获取request对象
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Response对象
     */
    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取Session对象
     */
    protected HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

   /* protected User getSessionUser() {
        return (User) getSession().getAttribute(AppConst.USER_LOGIN_SESSION);
    }

    protected void setSessionUser(Object user) {
        getSession().setAttribute(AppConst.USER_LOGIN_SESSION, user);
    }

    protected String getKaptchaCode() {
        return (String) getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
    }
*/
}


