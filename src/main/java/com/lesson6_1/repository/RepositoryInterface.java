package com.lesson6_1.repository;
import org.springframework.dao.DataIntegrityViolationException;

public interface RepositoryInterface<T>{

    public T save(T t);

    T findById(Long id);

    T update(T t);

    void delete(T t) throws DataIntegrityViolationException;
}
