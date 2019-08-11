package lesson3_w3.service;

import lesson3_w3.bean.Storage;
import lesson3_w3.dao.GeneralDao;
import lesson3_w3.dao.StorageDao;
import lesson3_w3.exceptions.ObjectPersistException;

import java.util.List;
import java.util.Set;

/**
 * Created by oleg on 08.08.2019.
 */
public class StorageService {
    GeneralDao<Storage> generalDao = new StorageDao();

    public Storage save(Storage storage) throws ObjectPersistException {
       if (checkIfStorageIsPersist(storage)){
           throw new ObjectPersistException("Storage " + storage.toString() + "is already persist in DB");
       }
        return generalDao.save(storage);
    }

    public List<Storage> getAllStorages(){
      return generalDao.getAll();
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
       Storage storage = generalDao.findById(id);
       if (storage==null){
           throw new ObjectPersistException("Storage with id " + id + " not present in DB.");
       }
       return storage;
    }

    public void delete(long id) throws ObjectPersistException {
        Storage storage = generalDao.findById(id);
        if (storage == null){
            throw new ObjectPersistException("Storage with id "+ id + " not present in DB");
        }
        generalDao.delete(storage);
        System.out.println("Storage with  id " + id + " was deleted.");
    }

    public Storage update(Storage storage) throws ObjectPersistException {
        if (checkIfStorageIsPersist(storage)) {
            throw new ObjectPersistException("Storage " + storage.toString() + " is already persist ib BD.");
        }
        return generalDao.update(storage);
    }
}
