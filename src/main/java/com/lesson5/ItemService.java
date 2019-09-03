package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by oleg on 03.09.2019.
 */
public class ItemService {

    DAO dao;

    @Autowired
    public ItemService(DAO dao) {
        this.dao = dao;
    }

    public Item save(Item item) throws PersistException {
        checkIfItemExist(item);
        return dao.save(item);
    }

    private void checkIfItemExist(Item item) throws PersistException {
        if (dao.checkIfItemExist(item.getDescription())) {
            throw new PersistException("Item with description " + item.getDescription() + " is already present in DB.");
        }
    }

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

    public Item update(Item item) throws PersistException {
        findById(item.getId());
        return dao.update(item);
    }
}
