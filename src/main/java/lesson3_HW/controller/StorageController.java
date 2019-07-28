package lesson3_HW.controller;

import lesson3_HW.beans.Storage;
import lesson3_HW.dao.GeneralDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageController {

    GeneralDao generalDao;

    @Autowired
    StorageController(GeneralDao generalDao) {
        this.generalDao = generalDao;
    }

    public Storage add(Storage storage) {
        return (Storage) generalDao.add(storage);
    }
}
