package lesson3_HW;

import lesson3_HW.beans.File;
import lesson3_HW.beans.Storage;
import lesson3_HW.controller.StorageController;
import lesson3_HW.dao.StorageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * Created by oleg on 25.07.2019.
 */
@Controller
public class Test {
    @Autowired
    Storage storage_1;
    @Autowired
    StorageController storageController;

    public static void main(String[] args) {
        Test test = new Test();
        test.testAddstprage();
    }

    private void testAddstprage() {
        storage_1.setFormatsSupported("jpg");
        storage_1.setStorageSize(1000);
        storage_1.setStorageCountry("China");
        storageController.add(storage_1);
    }
}