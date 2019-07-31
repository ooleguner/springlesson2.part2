package lesson3_HW.controller;

import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.Storage;
import lesson3_HW.dao.GeneralDao;
import lesson3_HW.dao.StorageDaoImpl;
import lesson3_HW.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class StorageController {

    StorageService storageService;


    @Autowired
    StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    public Storage save(Storage storage) throws BadRequestException {
        return  storageService.save(storage);
    }
}
