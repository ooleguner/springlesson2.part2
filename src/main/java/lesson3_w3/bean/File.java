package lesson3_w3.bean;

import javax.persistence.*;

/**
 * Created by oleg on 10.08.2019.
 */
@Entity
@Table(name = "FILES")
public class File {
    @Id
    @SequenceGenerator(name = "SEQ_FL", sequenceName = "SEQ_FILE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FL")
    @Column(name = "ID_FILE")
    private long id;

    @Column(name = "FILE_NAME")
    private String name;
    @Column(name = "FILE_FORMAT")
    private String format;
    @Column(name = "FILE_SIZE")
    private long size;

    @ManyToOne
    @JoinColumn(name = "STORAGE_ID")
    private Storage storage;

    public File() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }


    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storage=" + storage.getId() +
                '}';
    }
}
