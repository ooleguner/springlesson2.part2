package lesson3.service;

import lesson3.bean.Storage;
import lesson3.dao.StorageDao;
import lesson3.exceptions.ObjectPersistException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by oleg on 08.08.2019.
 */
public class StorageService {

   StorageDao storageDao;

    @Autowired
    public StorageService(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    public Storage save(Storage storage) throws ObjectPersistException {
       if (checkIfStorageIsPersist(storage)){
           throw new ObjectPersistException("Storage " + storage.toString() + "is already persist in DB");
       }
        return storageDao.save(storage);
    }

    public List<Storage> getAllStorages(){
      return storageDao.getAll();
    }

    private boolean checkIfStorageIsPersist(Storage storage) {
       List<Storage> storageList = getAllStorages();
       for (Storage storageInDb : storageList){
           if (storage.equals(storageInDb)){
               return true;
           }
       }return false;
    }

    public Storage findById(long id) throws ObjectPersistException {
       Storage storage = storageDao.findById(id);
       if (storage==null){
           throw new ObjectPersistException("Storage with id " + id + " not present in DB.");
       }
       return storage;
    }

    public void delete(long id) throws ObjectPersistException {
        Storage storage = storageDao.findById(id);
        if (storage == null){
            throw new ObjectPersistException("Storage with id "+ id + " not present in DB");
        }
        storageDao.delete(storage);
        System.out.println("Storage with  id " + id + " was deleted.");
    }

    public Storage update(Storage storage) throws ObjectPersistException {
        if (checkIfStorageIsPersist(storage)) {
            throw new ObjectPersistException("Storage " + storage.toString() + " is already persist ib BD.");
        }
        return storageDao.update(storage);
    }
}
