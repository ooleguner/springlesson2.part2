package service;

import exception.ItemExistException;
import model.Item;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ItemDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 14.07.2019.
 */
public class ItemService {

    @Autowired
    ItemDao dao;

    List<Item> items;

    public ArrayList<Item> getAllItems() throws HibernateException {
        return dao.getAllItems();
    }

    public Item getByID(long id) throws HibernateException, ItemExistException {
        if (checkIfIsExist(id)) {
            return dao.getItemById(id);
        }
        throw new ItemExistException("Item with id : " + id + " is not present in base");
    }

    private boolean checkIfIsExist(Long id) throws HibernateException {
        items = getAllItems();
        for (Item itemFromDB : items) {
            if (itemFromDB.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void delete(long id) throws HibernateException, ItemExistException {
        if (!checkIfIsExist(id)) {
            throw new ItemExistException("Item with id : " + id + " is not present in base");
        }
        dao.deleteItem(id);
    }


    public Item save(Item item) throws HibernateException, ItemExistException {
        items = getAllItems();
        for (Item itemFromDB : items) {
            if (itemFromDB.getName().equals(item.getName())) {
                throw new ItemExistException("Item with name : " + item.getName() + " is  present in base. Try another name");
            }
        }
       return dao.saveItem(item);
    }


    public Item update(Item item) throws HibernateException, ItemExistException {
        if (checkIfIsExist(item.getId())) {
            return dao.updateItem(item);
        }
        throw new ItemExistException("Item with id : " + item.getId() + " is not present in base");
    }
}
