package lesson3_HW.service;

import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.File;
import lesson3_HW.dao.FileDaoImpl;
import lesson3_HW.dao.GeneralDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 06.08.2019.
 */
public class FileService {

    GeneralDao generalDao;

    @Autowired
    FileService(FileDaoImpl fileDao) {
        this.generalDao = fileDao;
    }


    public File save(File file) throws BadRequestException {
        if (checkIfExist(file)) {
            throw new BadRequestException("The same File : " + file.toString() + " is already present in DB");
        }
        return (File) generalDao.save(file);

    }

    private boolean checkIfExist(File file) {
        ArrayList<File> fileArrayList = getAllFiles();
        if (fileArrayList.size() == 0) {
            return false;
        }
        for (File currentFile : fileArrayList) {
            if (currentFile.equals(file)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<File> getAllFiles() {
        return (ArrayList<File>) generalDao.listAll();
    }

    public String delete(long id) throws BadRequestException {
        File file = (File) generalDao.getById(id);
        if (file == null) {
            throw new BadRequestException("File with id : " + id + " not found in DB");
        }
        generalDao.remove(id);
        return "File id = " + id + " was  deleted";
    }

    public String gerAllFiles() {
        List<File> fileList = generalDao.listAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (File file: fileList) {
            stringBuilder.append(file.toString());
        }
        return stringBuilder.toString();
    }

    public File update(File currentFile) throws BadRequestException {
        if (checkIdIsExist(currentFile.getId())) {
            return (File) generalDao.update(currentFile);
        }
        throw new BadRequestException("File with id : " + currentFile.getId() + " not found in DB");
    }

    private boolean checkIdIsExist(Long id) {
        List<File> fileList = getAllFiles();

        for (File file : fileList) {
            if (file.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
