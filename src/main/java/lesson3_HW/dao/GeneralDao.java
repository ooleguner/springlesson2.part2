package lesson3_HW.dao;

import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.File;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;

public interface GeneralDao<T> {

    public T save (T t);

    public void remove(Long id) throws BadRequestException;

    public T getById(Long id);

    public T update(T t);

    public List<T> listAll();
}