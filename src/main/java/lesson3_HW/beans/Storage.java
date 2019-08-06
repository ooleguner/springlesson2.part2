package lesson3_HW.beans;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STORAGE")
public class Storage {
    private long id;
    private String formatsSupported;
    private String storageCountry;
    private long storageSize;
    private List<File> files;

    public Storage() {
    }

    public Storage(String formatsSupported, String storageCountry, long storageSize, List<File> files) {
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;
        this.files = files;
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

    @OneToMany
    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        if (storageSize != storage.storageSize) return false;
        if (!formatsSupported.equals(storage.formatsSupported)) return false;
        return storageCountry.equals(storage.storageCountry);
    }

    @Override
    public int hashCode() {
        int result = formatsSupported.hashCode();
        result = 31 * result + storageCountry.hashCode();
        result = 31 * result + (int) (storageSize ^ (storageSize >>> 32));
        return result;
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
