package com.example.videoparse.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2019/12/11 11:29
 * @description
 */
@ControllerAdvice
public class ExceptionHandleConfig {


    @ResponseBody
    @ExceptionHandler(value = {SQLException.class, PlatException.class})
    public Response handle(SQLException s) {
        return Response.exception(s.getMessage());
    }


}
