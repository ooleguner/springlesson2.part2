package lesson3.service;


import lesson3.bean.File;
import lesson3.bean.Storage;
import lesson3.dao.FileDao;
import lesson3.exceptions.ConditionException;
import lesson3.exceptions.ObjectPersistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleg on 08.08.2019.
 */

/*
@Service це бін в якому знаходиться бізнес логіка
 */
@Service
public class FileService {
    FileDao fileDao;

    @Autowired
    public FileService(FileDao fileDao) {
        this.fileDao = fileDao;
    }

// тут убрал проверки на ограничения файла по хранилищу  ибо создаю файл без хранилища а уже когда
// добавляю его в хранилище put(File file, Storage storage) то тода проверяю на ограничения предоставленные
// хранилищем к файлу

    public File save(File file) throws ConditionException {
        checkLenghName(file.getName());
        return fileDao.save(file);
    }

    private void checkFreeSizeStorage(long size, Storage storage) throws ConditionException {
        long freeSizeStorage = getFreeSizeStorage(storage);
        if (size > freeSizeStorage) {
            throw new ConditionException("Not enough free size of Storage " + storage.toString());
        }
    }

    private long getFreeSizeStorage(Storage storage) {
        long fillSpace = fileDao.getFillSpace(storage.getId());
        return storage.getStorageSize() - fillSpace;
    }

    private void checkLenghName(String name) throws ConditionException {
        if (name.length() > 10) {
            throw new ConditionException("Name " + name + " is too long.");
        }
    }

    private void checkFormatSupported(String fileFormat, String storageFormats) throws ConditionException {
        String[] fotmatsStorage = getFormatsStorages(storageFormats);
        for (String format : fotmatsStorage) {
            if (format.equals(fileFormat)) {
                return;
            }
        }
        throw new ConditionException("Format of file " + fileFormat + " is not supported by Storage. Only  " +storageFormats + " support.");
    }

    private String[] getFormatsStorages(String formats) {
        // "txt, jpg, doc"
        String temp = formats.substring(0, formats.length());
        String[] formatArray = temp.trim().split(", ");
        return formatArray;
    }

    private List<File> getAllFiles() {
        return fileDao.getAll();
    }

    private boolean checkIfFileIsPersist(File file, Storage storage) throws ObjectPersistException {

        return  fileDao.checkIfFileIsPersist(file.getId(),storage.getId());
    }

    public void delete(long id) throws ObjectPersistException {
        File file = fileDao.findById(id);
        if (file == null) {
            throw new ObjectPersistException("File with id " + id + " not present in DB");
        }
        fileDao.delete(file);
        System.out.println("File id " + id + " was deleted.");
    }

    public File update(File file) throws ObjectPersistException, ConditionException {
        if (checkIfFileIsPersist(file, file.getStorage())) {
            throw new ObjectPersistException("File " + file.toString() + " is already persist ib Storage " + file.getStorage());
        }
        checkFormatSupported(file.getFormat(), file.getStorage().getFormatsSupported());
        checkLenghName(file.getName());
        checkFreeSizeStorage(file.getSize(), file.getStorage());
        return fileDao.update(file);
    }

    public File findById(long id) throws ObjectPersistException {
        return fileDao.findById(id);
    }

    public File put(Storage storage, File file) throws ObjectPersistException, ConditionException {
        if (checkIfFileIsPersist(file, storage)) {
            throw new ObjectPersistException("File " + file.toString() + " is already persist ib storage " + storage.toString());
        }
        checkFormatSupported(file.getFormat(), storage.getFormatsSupported());
        checkLenghName(file.getName());
        checkFreeSizeStorage(file.getSize(), storage);
        file.setStorage(storage);
        return fileDao.update(file);
    }

    public void deleteFromStorage(Storage storage, File file) throws ObjectPersistException {

        if (!checkIfFileIsPersist(file, storage)) {
            throw new ObjectPersistException("File  id " + file.getId() + " was not found in Storage id " + storage.getId());
        }
        file.setStorage(null);
        fileDao.update(file);
    }

    public int transferAll(Storage storageFrom, Storage storageTo) throws ConditionException, ObjectPersistException {
        List<File> filesStorageFrom = storageFrom.getFiles();
        long sizeOfFiles = 0;
        for (File file : filesStorageFrom) {
            if (checkIfFileIsPersist(file, storageTo)) {
                throw new ObjectPersistException("File " + file.toString() + " is already persist ib storage " + storageTo.toString());
            }
            checkFormatSupported(file.getFormat(), storageTo.getFormatsSupported());
            checkLenghName(file.getName());
            sizeOfFiles = sizeOfFiles + file.getSize();
        }
        checkFreeSizeStorage(sizeOfFiles, storageTo);

        for (File file : storageFrom.getFiles()) {
            file.setStorage(storageTo);
        }

        return fileDao.updateList(filesStorageFrom);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws ObjectPersistException, ConditionException {
        File file = findById(id);
        put(storageTo, file);
    }
}
