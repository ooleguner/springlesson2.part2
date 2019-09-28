package com.lesson6.service;

import com.lesson6.exception.ItemExistException;
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
    public Item save(Item item) throws ItemExistException {
        checkIfItemExist(item);
        return dao.save(item);
    }

    @Transactional
    private void checkIfItemExist(Item item) throws ItemExistException {
        if (dao.checkIfItemExist(item.getName())) {
            throw new ItemExistException("Item with name " + item.getName() + " is already present in DB.");
        }
    }

    @Transactional
    public void delete(long id) throws ItemExistException {
        findById(id);
        dao.delete(id);
    }

    public Item findById(long id) throws ItemExistException {

        Item item = dao.findById(id);
        if (item == null) {
            throw new ItemExistException("Item with id " + id + " not found in DB. ");
        }
        return item;
    }

    @Transactional
    public Item update(Item item) throws ItemExistException {
        findById(item.getId());
        return dao.update(item);
    }

    @Transactional
    public int deleteByName(String name) throws ItemExistException {
        int res = dao.deleteByName(name);
        if (res == 0) {
            throw new ItemExistException("Item with name " + name + " not found in DB. ");
        }
        return res;
    }
}