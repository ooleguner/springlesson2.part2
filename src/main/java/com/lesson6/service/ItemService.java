package com.lesson6.service;

import com.lesson5.PersistException;
import com.lesson6.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import com.lesson6.repository.ItemDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
        if (dao.checkIfItemExist(item.getName())) {
            throw new PersistException("Item with name " + item.getName() + " is already present in DB.");
        }
    }

    @Transactional
    public void delete(long id) throws PersistException {
        findById(id);
        dao.delete(id);
    }

    public Item findById(long id) throws PersistException {

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

    @Transactional
    public int deleteByName(String name) throws PersistException {
        int res = dao.deleteByName(name);
        if (res == 0) {
            throw new PersistException("Item with name " + name + " not found in DB. ");
        }
        return res;
    }
}