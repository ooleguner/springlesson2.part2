package lesson3HomeWork;

import lesson3HomeWork.bean.File;
import lesson3HomeWork.bean.Storage;
import lesson3HomeWork.controller.FileController;
import lesson3HomeWork.controller.StorageController;
import lesson3HomeWork.exceptions.FormatSupportedException;
import lesson3HomeWork.exceptions.ObjectPersistException;
import lesson3HomeWork.service.StorageService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


/**
 * Created by oleg on 08.08.2019.
 */
public class MainHibernate {
    public static void main(String[] args) {
        StorageController storageController = new StorageController();
        FileController fileController = new FileController();
/*
        Storage storage = new Storage("xlsx", "Ukr", 5000);
        File file = new File("test", "xlsx", 50, storage);

        storageController.save(storage);
        fileController.save(file);
*/
        Storage storage = null;
        try {
            storage = storageController.findById(452);
            File file = new File("Picture", "jpg", 500, storage);
            fileController.save(file);
        } catch (ObjectPersistException e) {
            System.out.println(e.getMessage());
        } catch (FormatSupportedException e) {
            System.out.println(e.getMessage());
        }


    }
}
