package lesson3_w3;

import lesson3_w3.bean.File;
import lesson3_w3.bean.Storage;
import lesson3_w3.controller.FileController;
import lesson3_w3.controller.StorageController;
import lesson3_w3.exceptions.ObjectPersistException;
import lesson3_w3.exceptions.ConditionException;

import java.util.List;

/**
 * Created by oleg on 10.08.2019.
 */
public class Main {
    public static void main(String[] args) throws ObjectPersistException, ConditionException {
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
        FileController fileController = new FileController();
//        Storage storage = storageController.findById(453L);

        System.out.println("+++++++++ LIST OF FILES IN STORAGE ++++++++++++++++++++++++++");
//        List<File> files = storage.getFiles();
//        for (File file : files) {
//            System.out.println(file.toString());
//        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

//        File file = new File();
//        file.setFormat("jpg");
//        file.setSize(160);
//        file.setName("K6k666i");
//        file.setStorage(storage);
//        fileController.save(file);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

//        fileController.delete(960);
//        fileController.delete(961);
//        fileController.delete(963);
//        fileController.delete(964);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

//        File file = fileController.findById(960L);
//        file.setName("Kitgki");
//        fileController.update(file);

//        File file = fileController.findById(956L);
//        Storage storage1 = storageController.findById(453L);
//
//        fileController.delete(storage1,file);

        Storage storage = storageController.findById(452);
        for (File file : storage.getFiles()){
            System.out.println(file);
        }
        System.out.println("Size storage " + storage.getFiles().size());

        System.out.println("+++++++++Transfer  +++++++++++++++++++");
//
//        fileController.transferAll(storageController.findById(452L),storageController.findById(453L));
//        System.out.println("++++all ok");

        fileController.transferFile(storageController.findById(452L), storageController.findById(453L), 959);



        storage = storageController.findById(452);
        for (File file : storage.getFiles()){
            System.out.println(file);
        }
        System.out.println("Size storage " + storage.getFiles().size());

    }
}
