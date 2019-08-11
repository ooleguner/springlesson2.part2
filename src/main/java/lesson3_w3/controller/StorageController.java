package lesson3_w3.controller;


import lesson3_w3.bean.Storage;
import lesson3_w3.exceptions.ObjectPersistException;
import lesson3_w3.service.StorageService;

import java.util.List;

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

    public List<Storage> getAll(){
        return storageService.getAllStorages();
    }

    public void delete(long id) throws ObjectPersistException {
        storageService.delete(id);
    }

    public Storage update (Storage storage) throws ObjectPersistException {
        return storageService.update(storage);
    }
}
