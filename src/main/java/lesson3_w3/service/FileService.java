package lesson3_w3.service;

import lesson3_w3.bean.File;
import lesson3_w3.bean.Storage;
import lesson3_w3.dao.FileDao;
import lesson3_w3.dao.GeneralDao;
import lesson3_w3.exceptions.ObjectPersistException;
import lesson3_w3.exceptions.ConditionException;

import java.util.List;

/**
 * Created by oleg on 08.08.2019.
 */
public class FileService {

    GeneralDao<File> generalDao = new FileDao();

// тут убрал проверки на ограничения файла по хранилищу  ибо создаю файл без хранилища а уже когда
// добавляю его в хранилище put(File file, Storage storage) то тода проверяю на ограничения предоставленные
// хранилищем к файлу

    public File save(File file) throws ConditionException {
        /*
        if (checkIfFileIsPersist(file, file.getStorage())) {
            throw new ObjectPersistException("File " + file.toString() + " is already persist ib BD.");
        }
                checkFormatSupported(file, file.getStorage());
                checkFreeSizeStorage(file.getSize(), file.getStorage());
        */
        checkLenghName(file.getName());
        return generalDao.save(file);
    }

    private void checkFreeSizeStorage(long size, Storage storage) throws ConditionException {
        long freeSizeStorage = getFreeSizeStorage(storage);
        if (size > freeSizeStorage) {
            throw new ConditionException("Not enough free size of Storage " + storage.toString());
        }
    }

    private long getFreeSizeStorage(Storage storage) {
        List<File> filesInStorage = storage.getFiles();
        long fillSpace = 0;
        if (filesInStorage != null && filesInStorage.size() != 0) {
            for (File file : filesInStorage) {
                fillSpace = fillSpace + file.getSize();
            }
        }
        return storage.getStorageSize()- fillSpace;
    }

    private void checkLenghName(String name) throws ConditionException {
        if (name.length() > 10) {
            throw new ConditionException("Name " + name + " is too long.");
        }
    }

    private void checkFormatSupported(File file, Storage storage) throws ConditionException {
        String[] fotmatsStorage = getFormatsStorages(storage.getFormatsSupported());
        for (String format : fotmatsStorage) {
            if (format.equals(file.getFormat())) {
                return;
            }
        }
        throw new ConditionException("Format of file " + file.toString() + " is not supported by Storage");
    }

    private String[] getFormatsStorages(String formats) {
        // "txt, jpg, doc"
        String temp = formats.substring(0, formats.length());
        String[] formatArray = temp.trim().split(", ");
        return formatArray;
    }

    private List<File> getAllFiles() {
        return generalDao.getAll();
    }

    private boolean checkIfFileIsPersist(File file, Storage storage) {
        List<File> fileList = storage.getFiles();
        for (File fileInDb : fileList) {
            if (file.equals(fileInDb)) {
                return true;
            }
        }
        return false;
    }

    public void delete(long id) throws ObjectPersistException {
        File file = generalDao.findById(id);
        if (file == null){
            throw new ObjectPersistException("File with id "+ id + " not present in DB");
        }
            generalDao.delete(file);
        System.out.println("File id " + id + " was deleted.");
    }

    public File update(File file) throws ObjectPersistException, ConditionException {
        if (checkIfFileIsPersist(file, file.getStorage())) {
            throw new ObjectPersistException("File " + file.toString() + " is already persist ib BD.");
        }
        checkFormatSupported(file,file.getStorage());
        checkLenghName(file.getName());
        checkFreeSizeStorage(file.getSize(), file.getStorage());
       return generalDao.update(file);
    }

    public File findById(long id){
        return generalDao.findById(id);
    }

    public File put(Storage storage, File file) throws ObjectPersistException, ConditionException {
        if (checkIfFileIsPersist(file,storage)) {
            throw new ObjectPersistException("File " + file.toString() + " is already persist ib storage " + storage.toString());
        }
        checkFormatSupported(file, storage);
        checkLenghName(file.getName());
        checkFreeSizeStorage(file.getSize(), storage);
        file.setStorage(storage);
        return generalDao.update(file);
    }

    public void deleteFromStorage(Storage storage, File file) throws ObjectPersistException {

        if (!checkIfFileIsPersist(file,storage)){
            throw new ObjectPersistException("File  id "+ file.getId() + " was not found in Storage id " + storage.getId());
        }
        file.setStorage(null);
        generalDao.update(file);
    }

    public int transferAll(Storage storageFrom, Storage storageTo) throws ConditionException, ObjectPersistException {
        List<File> filesStorageFrom = storageFrom.getFiles();
        long sizeOfFiles=0;
        for(File file:filesStorageFrom){
            if (checkIfFileIsPersist(file,storageTo)) {
                throw new ObjectPersistException("File " + file.toString() + " is already persist ib storage " + storageTo.toString());
            }
            checkFormatSupported(file, storageTo);
            checkLenghName(file.getName());
            sizeOfFiles = sizeOfFiles + file.getSize();
        }
        checkFreeSizeStorage(sizeOfFiles,storageTo);

        for (File file : storageFrom.getFiles()){
            file.setStorage(storageTo);
        }

      return generalDao.updateList(filesStorageFrom);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws ObjectPersistException, ConditionException {
        File file = findById(id);
        put(storageTo,file);
    }
}
