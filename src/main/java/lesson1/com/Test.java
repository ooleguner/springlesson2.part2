package lesson1.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by oleg on 04.07.2019.
 */
@Controller
public class Test {
    @Autowired
    Student student;

    @RequestMapping(method = RequestMethod.GET, value = "/hi", produces = "texp/plain")
    public @ResponseBody String getTest() {
        student.setName("Nick");
        return "test : " + student.toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "texp/plain")
    public @ResponseBody
    String hello() {
        return "hello";
    }
}
