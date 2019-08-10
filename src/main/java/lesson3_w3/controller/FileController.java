package lesson3_w3.controller;


import lesson3_w3.bean.File;
import lesson3_w3.exceptions.FormatSupportedException;
import lesson3_w3.exceptions.LenghNameException;
import lesson3_w3.exceptions.ObjectPersistException;
import lesson3_w3.exceptions.SizeException;
import lesson3_w3.service.FileService;

/**
 * Created by oleg on 08.08.2019.
 */
public class FileController {

    FileService fileService = new FileService();

    public File save(File file) throws ObjectPersistException, FormatSupportedException, LenghNameException, SizeException {
       return fileService.save(file);
    }
}
