package lesson3_w3.dao;
import java.util.List;

public interface GeneralDao<T> {
   T save(T object);
   void delete(long id);
   T update(T object);
   T findById(long id);
   List<T> getAll();
}
