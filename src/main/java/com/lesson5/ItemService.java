package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.transaction.Transactional;

/**
 * Created by oleg on 03.09.2019.
 */
public class ItemService {

    DAO dao;

    @Autowired
    public ItemService(DAO dao) {
        this.dao = dao;
    }

    @Transactional
    public Item save(Item item) throws PersistException {
        checkIfItemExist(item);
        return dao.save(item);
    }

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
/*
означає що в методі будуть перераховані методи які необхідно виконати в рамках однієї трансакціїї
підключення до бази відкривається при заході в метод та відбувається коміт при виході
з методу і тоді закривається конекшен
 */
    @Transactional
    public Item findById(long id) throws PersistException {

 /*
for me test
 */

        System.out.println("Transaction is open? Service  " + TransactionSynchronizationManager.isActualTransactionActive());


        Item item = dao.findById(id);
        if (item == null) {
            throw new PersistException("Item with id " + id + " not found in DB. ");
        }

/*
for me test
 */

        System.out.println("Transaction is open? Service  " + TransactionSynchronizationManager.isActualTransactionActive());

        return item;
    }

    @Transactional
    public Item update(Item item) throws PersistException {
        findById(item.getId());
        return dao.update(item);
    }
}
