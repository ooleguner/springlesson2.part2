package lesson3_HW.Controller;

import lesson3_HW.Beans.Storage;

import javax.swing.plaf.synth.SynthToolTipUI;
import java.util.List;

/**
 * Created by oleg on 25.07.2019.
 */
public class StorageController {

    List<Storage> storageList;

    public void addStorage(Storage storage){
        storageList.add(storage);
    }
    public void deleteStorage(Storage storage){
        storageList.remove(storage);
    }
    public List<Storage> getStorageList(){
        return storageList;
    }


}
