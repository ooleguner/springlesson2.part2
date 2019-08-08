package lesson3HomeWork.service;

import lesson3HomeWork.bean.File;
import lesson3HomeWork.dao.FileDao;
import lesson3HomeWork.dao.GeneralDao;
import lesson3HomeWork.exceptions.FormatSupportedException;
import lesson3HomeWork.exceptions.ObjectPersistException;

import java.util.List;

/**
 * Created by oleg on 08.08.2019.
 */
public class FileService {

    GeneralDao<File> generalDao = new FileDao();

    public File save(File file) throws ObjectPersistException, FormatSupportedException {
        if (checkIfFileIsPersist(file)) {
            throw new ObjectPersistException("File " + file.toString() + " is already persist ib BD.");
        }
        checkFormatSupperted(file);

        return generalDao.save(file);
    }

    private void checkFormatSupperted(File file) throws FormatSupportedException {
        String[] fotmatsStorage = getFormatsStorages(file.getStorage().getFormatsSupported());
        for (String format:fotmatsStorage) {
            if (format.equals(file.getFormat())){
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
