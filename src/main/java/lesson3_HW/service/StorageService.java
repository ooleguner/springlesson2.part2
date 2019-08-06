package lesson3_HW.service;

import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.Storage;
import lesson3_HW.dao.GeneralDao;
import lesson3_HW.dao.StorageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        if (storages.size() == 0) {
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

    public String delete(long id) throws BadRequestException {
        Storage storage = (Storage) generalDao.getById(id);
        if (storage == null) {
            throw new BadRequestException("Storage with id : " + id + " not found in DB");
        }
        generalDao.remove(id);
        return "Storage id = " + id + " was  deleted";
    }

    public String gerAllStorages() {
        List<Storage> storageList = generalDao.listAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Storage storage : storageList) {
            stringBuilder.append(storage.toString());
        }
        return stringBuilder.toString();
    }

    public Storage update(Storage currentStorage) throws BadRequestException {
        if (checkIdIsExist(currentStorage.getId())) {
            return (Storage) generalDao.update(currentStorage);
        }
        throw new BadRequestException("Storage with id : " + currentStorage.getId() + " not found in DB");
    }

    private boolean checkIdIsExist(Long id) {
        List<Storage> storageList = getAllStorages();

        for (Storage storage : storageList) {
            if (storage.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
