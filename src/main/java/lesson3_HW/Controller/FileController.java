package lesson3_HW.Controller;

import lesson3_HW.Beans.File;
import lesson3_HW.Beans.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 25.07.2019.
 */
public class FileController {

    List<File> filesList= new ArrayList<>();
    List<File> filesListInStorage = new ArrayList<>();

    public void addFile(File file){
        filesList.add(file);

    }

    public List<File> getFilesList(){
        return filesList;
    }

    public void deleteFile(File file){
        filesList.remove(file);

    }

    public List<File> getFilesInStorage(Storage storage){
        filesListInStorage.clear();

        for(File file: filesList){
            if (file.getStorage() != null && file.getStorage().getId()==storage.getId()){
                filesListInStorage.add(file);
            }
        }
        return filesListInStorage;
    }


}
