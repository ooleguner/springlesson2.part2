package lesson3.dao;

import lesson3.exceptions.ObjectPersistException;

import java.util.List;

public interface GeneralDao<T> {
   T save(T object);

   void delete(T object);

   T update(T object);

   T findById(long id) throws ObjectPersistException;

   List<T> getAll();

   int updateList(List<T> files);
}
