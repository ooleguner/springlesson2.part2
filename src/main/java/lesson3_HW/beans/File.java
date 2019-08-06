package lesson3_HW.beans;

import org.springframework.stereotype.Controller;
import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by oleg on 25.07.2019.
 */
@Entity
@Table(name = "FILES")
public class File {

    private long id;
    private String name;
    private String format;
    private long size;
    private Storage storage;

    public File() {
    }

    public File(String name, String format, long size, Storage storage) {
        this.name = name;
        this.format = format;
        this.size = size;
        this.storage = storage;
    }

    @Id
    @SequenceGenerator(name = "SEQ_FL", sequenceName = "SEQ_FILE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FL")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "FORMAT")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Column(name = "FILE_SIZE")
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @ManyToOne
    @JoinColumn(name = "STORAGE_ID", nullable = false)
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {

        return "\n File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                //               ", storage=" + storage.toString() +
                '}';
    }
}
