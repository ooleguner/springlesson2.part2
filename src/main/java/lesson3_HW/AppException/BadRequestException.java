package lesson3_HW.AppException;

/**
 * Created by oleg on 31.07.2019.
 */
public class BadRequestException extends Exception {
   public BadRequestException(String msg){
        super(msg);
    }
}
