package com.lesson6_1.repository;
import com.lesson6_1.model.Plane;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryInterface<T>{

    public T save(T t);

    T findById(Long id);

    T update(T t);

    void delete(T t) throws DataIntegrityViolationException;

    boolean checkIfPresent(T t);
}
