package com.lesson6.service;

import com.lesson5.PersistException;
import com.lesson6.exception.ItemExistException;
import com.lesson6.model.Item;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import com.lesson6.repository.ItemDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 14.07.2019.
 */
public class ItemService {


    ItemDao dao;

    @Autowired
    public ItemService(ItemDao dao) {
        this.dao = dao;
    }

    @Transactional
    public Item save(Item item) throws PersistException {
        checkIfItemExist(item);
        return dao.save(item);
    }

    @Transactional
    private void checkIfItemExist(Item item) throws PersistException {
        if (dao.checkIfItemExist(item.getDescription())) {
            throw new PersistException("Item with description " + item.getDescription() + " is already present in DB.");
        }
    }

    @Transactional
    public void delete(long id) throws PersistException {
        findById(id);
        dao.delete(id);
    }
    public Item findById(long id) throws PersistException {
 /*
        for me test
        System.out.println("Transaction is open? Service  " + TransactionSynchronizationManager.isActualTransactionActive());

 */
       Item item = dao.findById(id);
        if (item == null) {
            throw new PersistException("Item with id " + id + " not found in DB. ");
        }
        return item;
    }

    @Transactional
    public Item update(Item item) throws PersistException {
        findById(item.getId());
        return dao.update(item);
    }

}
/*
    ItemDao dao;

    @Autowired
    public ItemService(ItemDao dao){
        this.dao = dao;
    }


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


    public Item save(Item item)  {
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
 */