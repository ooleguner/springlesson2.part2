package lesson3_w3.service;

import lesson3_w3.bean.File;
import lesson3_w3.bean.Storage;
import lesson3_w3.dao.FileDao;
import lesson3_w3.dao.GeneralDao;
import lesson3_w3.exceptions.FormatSupportedException;
import lesson3_w3.exceptions.LenghNameException;
import lesson3_w3.exceptions.ObjectPersistException;
import lesson3_w3.exceptions.SizeException;

import java.util.List;

/**
 * Created by oleg on 08.08.2019.
 */
public class FileService {

    GeneralDao<File> generalDao = new FileDao();


    public File save(File file) throws ObjectPersistException, FormatSupportedException, LenghNameException, SizeException {
        if (checkIfFileIsPersist(file)) {
            throw new ObjectPersistException("File " + file.toString() + " is already persist ib BD.");
        }
        checkFormatSupperted(file);
        checkLenghName(file.getName());
        checkFreeSizeStorage(file.getSize(), file.getStorage());
        return generalDao.save(file);
    }

    private void checkFreeSizeStorage(long size, Storage storage) throws SizeException {
        long freeSizeStorage = getFreeSizeStorage(storage);
        if (size > freeSizeStorage) {
            throw new SizeException("Not enough free size of Storage " + storage.toString());
        }
    }

    private long getFreeSizeStorage(Storage storage) {
//        List<File> filesInStorage = storage.getFiles();
//        long fillSpace = 0;
//        if (filesInStorage != null && filesInStorage.size() != 0) {
//            for (File file : filesInStorage) {
//                fillSpace = fillSpace + file.getSize();
//            }
//        }
//        return storage.getStorageSize()- fillSpace;
        return 0;
    }

    private void checkLenghName(String name) throws LenghNameException {
        if (name.length() > 10) {
            throw new LenghNameException("Name " + name + " is too long.");
        }
    }

    private void checkFormatSupperted(File file) throws FormatSupportedException {
        String[] fotmatsStorage = getFormatsStorages(file.getStorage().getFormatsSupported());
        for (String format : fotmatsStorage) {
            if (format.equals(file.getFormat())) {
                return;
            }
        }
        throw new FormatSupportedException("Format of file " + file.toString() + " is not supported by Storage");
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

    private boolean checkIfFileIsPersist(File file) {
        List<File> fileList = getAllFiles();
        for (File fileInDb : fileList) {
            if (file.equals(fileInDb)) {
                return true;
            }
        }
        return false;
    }
}
