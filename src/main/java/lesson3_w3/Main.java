package lesson3_w3;

import lesson3_w3.bean.File;
import lesson3_w3.bean.Storage;
import lesson3_w3.controller.StorageController;
import lesson3_w3.exceptions.ObjectPersistException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by oleg on 10.08.2019.
 */
public class Main {
    public static void main(String[] args) throws ObjectPersistException {
//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();

//        Storage storage = new Storage();
//        storage.setFormatsSupported("doc");
//        storage.setStorageCountry("Zimbabua");
//        storage.setStorageSize(100);
//
//
//        File file = new File();
//        file.setFormat("jpg");
//        file.setName("Newname");
//        file.setSize(100);
//        file.setStorage(storage);
//
//        storage.getFiles().add(file);
//
//        session.save(storage);
//        session.save(file);
//        File file2 = new File();
//        file2.setFormat("jpeg");
//        file2.setName("Newname-2");
//        file2.setSize(1200);

//        Storage storage = session.get(Storage.class, 453L);
//        file2.setStorage(storage);

 //       session.save(file2);

        StorageController storageController = new StorageController();
        Storage storage = storageController.findById(453L);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        List<File> files = storage.getFiles();
        for (File file : files) {
            System.out.println(file.toString());
        }

    }
}
