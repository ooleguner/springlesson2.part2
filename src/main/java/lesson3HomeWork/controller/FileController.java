package lesson3HomeWork.controller;

import lesson3HomeWork.bean.File;
import lesson3HomeWork.exceptions.FormatSupportedException;
import lesson3HomeWork.exceptions.ObjectPersistException;
import lesson3HomeWork.service.FileService;

/**
 * Created by oleg on 08.08.2019.
 */
public class FileController {

    FileService fileService = new FileService();

    public File save(File file) throws ObjectPersistException, FormatSupportedException {
       return fileService.save(file);
    }
}
