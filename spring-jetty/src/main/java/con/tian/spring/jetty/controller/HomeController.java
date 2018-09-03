package con.tian.spring.jetty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tianbeiping
 * @Title: HomeController
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/4上午12:19
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