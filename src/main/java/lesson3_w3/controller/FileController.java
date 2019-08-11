package lesson3_w3.controller;


import lesson3_w3.bean.File;
import lesson3_w3.bean.Storage;
import lesson3_w3.exceptions.ObjectPersistException;
import lesson3_w3.exceptions.ConditionException;
import lesson3_w3.service.FileService;

/**
 * Created by oleg on 08.08.2019.
 */
public class FileController {

    FileService fileService = new FileService();

    public File save(File file) throws ObjectPersistException, ConditionException {
        return fileService.save(file);
    }

    public void delete(long id) throws ObjectPersistException {
        fileService.delete(id);
    }

    public File update(File file) throws ObjectPersistException, ConditionException {
        return fileService.update(file);
    }

    public File findById(Long id) {
        return fileService.findById(id);
    }

    File put(Storage storage, File file) throws ObjectPersistException, ConditionException {
        return fileService.put(storage, file);
    }

   public void delete(Storage storage, File file){
         fileService.deleteFromStorage(storage,file);
    }


    public int transferAll(Storage storageFrom, Storage storageTo) throws ObjectPersistException, ConditionException {
      return fileService.transferAll(storageFrom, storageTo);
    }
    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws ObjectPersistException, ConditionException {
       fileService.transferFile (storageFrom,storageTo,id);
    }

}
