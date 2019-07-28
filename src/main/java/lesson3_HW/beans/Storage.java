package lesson3_HW.beans;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "STORAGES")
public class Storage {
    private long id;
    private String formatsSupported;
    private String storageCountry;
    private long storageSize;


    public Storage() {
    }

    @Id
    @SequenceGenerator(name = "SEQ_ST", sequenceName = "SEQ_STORAGE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ST")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FORMATS_SUPPORTED")
    public String getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    @Column(name = "STORAGE_COUNTY")
    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    @Column(name = "STORAGE_SIZE")
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
                ", formatsSupported=" + formatsSupported +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
