package com.example.videoparse.controller;

import com.example.videoparse.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/3 10:38
 * @description
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping(value = {"/index","/"})
    public ModelAndView index(ModelAndView mo){
        mo.setViewName("/index");
        return mo;
    }
}
