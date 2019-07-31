package lesson3_HW.service;

import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.Storage;
import lesson3_HW.dao.GeneralDao;
import lesson3_HW.dao.StorageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class StorageService {

    GeneralDao generalDao;

    @Autowired
    StorageService(StorageDaoImpl storageDao) {
        this.generalDao = storageDao;
    }


    public Storage save(Storage storage) throws BadRequestException {
        if (checkIfExist(storage)) {
            throw new BadRequestException("The same Storage : " + storage.toString() + " is already present in DB");
        }
        return (Storage) generalDao.save(storage);

    }

    private boolean checkIfExist(Storage storage) {
        ArrayList<Storage> storages = getAllStorages();
        if (storages.size()==0) {
            return false;
        }
        for (Storage currentStorage : storages) {
            if (currentStorage.equals(storage)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Storage> getAllStorages() {
        return (ArrayList<Storage>) generalDao.listAll();
    }
}
