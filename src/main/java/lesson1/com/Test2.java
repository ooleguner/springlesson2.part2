package lesson1.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by oleg on 04.07.2019.
 */
@Controller
public class Test2 {
    @RequestMapping(method = RequestMethod.GET, value = "/hi2", produces = "texp/plain")
    public @ResponseBody String test(){
        return "test";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello2", produces = "texp/plain")
    public @ResponseBody String hello(){
        return "hello";
    }
}
