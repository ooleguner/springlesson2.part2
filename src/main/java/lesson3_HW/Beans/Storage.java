package lesson3_HW.Beans;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by oleg on 25.07.2019.
 */
public class Storage {
   private long id;
    private String[] formatsSupported;
    private String storageCountry;
    private long storageSize;
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);


    public Storage(String[] formatsSupported, String storageCountry, long storageSize) {
        this.id = AUTO_ID.getAndIncrement();
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;
    }

    public long getId() {
        return id;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String[] formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
