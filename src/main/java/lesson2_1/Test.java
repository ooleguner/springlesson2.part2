package lesson2_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oleg on 14.07.2019.
 */

@Controller
public class Test {

    @Autowired
    Service service1;
    @Autowired
    Service service2;
    @Autowired
    Step step1;
    @Autowired
    Step step2;
    @Autowired
    Route route;

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "texp/plain")
    public @ResponseBody
    String callByBean() {

        int size1 = service1.getParamsToCall().size();
        int size2 = service2.getParamsToCall().size();

        Map map1 = new HashMap();
        for (int i = 0; i < size1; i++) {
            map1.put(i, service1.getParamsToCall().get(i));
        }
        Map map2 = new HashMap();
        for (int i = 0; i < size2; i++) {
            map2.put(i, service2.getParamsToCall().get(i));
        }

        step1.setParamsServiceFrom(map1);
        step1.setParamsServiceTo(map2);

        step2.setParamsServiceFrom(map1);
        step2.setParamsServiceTo(map2);
        route.setId("1000");
        route.setSteps(Arrays.asList(step1, step2));


        return "route :  " +
                "id" + route.getId() +
                "<p> steps " + route.getSteps();
    }
}
