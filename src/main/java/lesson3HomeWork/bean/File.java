package lesson3HomeWork.bean;
import javax.persistence.*;

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
    @Column(name = "ID_FILE")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FILE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "FILE_FORMAT")
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

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "STORAGE_ID", nullable = false)
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (size != file.size) return false;
        if (name != null ? !name.equals(file.name) : file.name != null) return false;
        return format != null ? format.equals(file.format) : file.format == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (int) (size ^ (size >>> 32));
        return result;
    }

    @Override
    public String toString() {

        return "\n File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storage=" + storage.toString() +
                '}';
    }
}
