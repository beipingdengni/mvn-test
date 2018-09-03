package com.tian.spring.tomcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tianbeiping
 * @Title: HomeController
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/3下午12:01
 */
@Controller
@RequestMapping("home")
public class HomeController {


    @RequestMapping("index")
    @ResponseBody
    public String index() {

        return "hello " + " world   ===== >?";
    }

}
