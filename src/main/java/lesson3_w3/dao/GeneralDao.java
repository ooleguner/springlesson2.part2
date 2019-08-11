package lesson3_w3.dao;
import lesson3_w3.bean.Storage;

import java.util.List;

public interface GeneralDao<T> {
   T save(T object);

   void delete(T object);

   T update(T object);

   T findById(long id);

   List<T> getAll();

   int updateList(List<T> files);
}
