package lesson3_HW;

import lesson3_HW.Beans.File;
import lesson3_HW.Beans.Storage;
import lesson3_HW.Controller.FileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by oleg on 25.07.2019.
 */
public class Test {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        File file_1 = (File) ctx.getBean("file_1");
        File file_2 = (File) ctx.getBean("file_2");
        File file_3 = (File) ctx.getBean("file_3");
        Storage storage_1 = (Storage) ctx.getBean("storage_1") ;
        Storage storage_2 = (Storage) ctx.getBean("storage_2") ;

        FileController fileController = (FileController) ctx.getBean("fileController");

        fileController.addFile(file_1);
        fileController.addFile(file_2);
        fileController.addFile(file_3);
        System.out.println("Files in storage_1");
        System.out.println(fileController.getFilesInStorage(storage_1));
        System.out.println("Files in storage_2");
        System.out.println(fileController.getFilesInStorage(storage_2));
        System.out.println("All files");
        System.out.println(fileController.getFilesList());

        fileController.deleteFile(file_2);
        System.out.println("All files");
        System.out.println(fileController.getFilesList());
    }
}