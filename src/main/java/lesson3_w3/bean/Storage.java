package lesson3_w3.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 10.08.2019.
 */


@Entity
@Table(name = "STORAGES")
public class Storage {
    @Id
    @SequenceGenerator(name = "SEQ_ST", sequenceName = "SEQ_STORAGE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ST")
    @Column(name = "ID_STORAGE")
    private long id;

    @Column(name = "FORMATS_SUPPORTED")
    private String formatsSupported;
    @Column(name = "STORAGE_COUNTY")
    private String storageCountry;
    @Column(name = "STORAGE_SIZE")
    private long storageSize;

/*
Unable to evaluate the expression Method threw 'org.hibernate.LazyInitializationException' exception.
хібернейт не інициализував поле files поки  до нього не звернулися. Але оскільки я викликав дію з полем  не в межах транскації - вилатала помилка LazyInitialization.
*/

    @OneToMany(mappedBy = "storage", fetch = FetchType.EAGER)
    List<File> files = new ArrayList<>();

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Storage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String formatsSupported) {
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
}
