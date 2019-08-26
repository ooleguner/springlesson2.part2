package lesson2_2.exception;

/**
 * Created by oleg on 23.06.2019.
 */
public class ItemExistException extends Exception {
    public ItemExistException (String measge){
        super(measge);
    }
}
