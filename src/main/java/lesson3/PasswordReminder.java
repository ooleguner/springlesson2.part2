package lesson3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by oleg on 22.07.2019.
 */

@Controller
public class PasswordReminder {


    DBConnector dbConnector;

    public PasswordReminder(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void sendPassword() {
        //logic
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hi", produces = "texp/plain")
    public @ResponseBody
    String test(){
        return "test";
    }

}
