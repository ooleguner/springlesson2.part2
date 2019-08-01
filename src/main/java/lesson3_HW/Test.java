package lesson3_HW;

import lesson2_1.Service;
import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.File;
import lesson3_HW.beans.Storage;
import lesson3_HW.controller.StorageController;
import lesson3_HW.dao.StorageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
    @Autowired
    Storage storage_1;
    @Autowired
    StorageController storageController;

//    @RequestMapping(method = RequestMethod.GET, value = "/add", produces = "texp/plain")
//    public @ResponseBody
//    String testAddStorage() {
//        try {
//            return "Test add storsge \n" + storageController.save(storage_1);
//        } catch (BadRequestException e) {
//            return e.getMessage();
//        }
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/del", produces = "texp/plain")
    public @ResponseBody
    String testDellStorage() {
        try {
            return "Test add storsge \n" + storageController.save(storage_1);
        } catch (BadRequestException e) {
            return e.getMessage();
        }
    }
}