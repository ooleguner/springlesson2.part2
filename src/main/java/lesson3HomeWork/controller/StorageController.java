package lesson3HomeWork.controller;


import lesson3HomeWork.bean.Storage;
import lesson3HomeWork.exceptions.ObjectPersistException;
import lesson3HomeWork.service.StorageService;

/**
 * Created by oleg on 08.08.2019.
 */
public class StorageController {

    StorageService storageService = new StorageService();

    public Storage save(Storage storage) throws ObjectPersistException {
        return storageService.save(storage);
    }

    public Storage findById(long l) throws ObjectPersistException {
        return storageService.findById(l);
    }
}
