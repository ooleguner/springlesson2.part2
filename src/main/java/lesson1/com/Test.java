package lesson1.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by oleg on 04.07.2019.
 */
@Controller
public class Test {
    @RequestMapping(method = RequestMethod.GET, value = "/hi", produces = "texp/plain")
    public @ResponseBody String test(){
        return "test";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "texp/plain")
    public @ResponseBody String hello(){
        return "hello";
    }
}
