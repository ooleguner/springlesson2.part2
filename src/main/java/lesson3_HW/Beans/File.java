package lesson3_HW.Beans;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by oleg on 25.07.2019.
 */
public class File {

    private long id;
    private String name;
    private String format;
    private long size;
    private Storage storage;

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    public File(String name, String format, long size, Storage storage) {
        this.id = AUTO_ID.getAndIncrement();
        this.name = name;
        this.format = format;
        this.size = size;
        this.storage = storage;
    }

    public long getId() {
        return id;
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

        return "\n File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storage=" + storage.toString() +
                '}';
    }
}
