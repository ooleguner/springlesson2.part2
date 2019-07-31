package lesson3_HW.dao;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;

public interface GeneralDao<T> {

    public T save (T t);

    public void remove(Long id);

    public T getById(Long id);

    public T update(T t);

    public List<T> listAll();
}