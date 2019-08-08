package lesson3HomeWork.dao;

import java.util.List;
import java.util.Set;

/**
 * Created by oleg on 08.08.2019.
 */
public interface GeneralDao<T> {
   T save(T object);
   void delete(long id);
   T update(T object);
   T findById(long id);
   List<T> getAll();
}
