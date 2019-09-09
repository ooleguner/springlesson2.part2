package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ItemController {

    private ItemService itemService;
    private ItemMapper itemMapper;

    @Autowired
    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    /*
      {"description":"testSaveItem"}
    */
    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public ResponseEntity<String> save(HttpServletRequest request) {
        try {
            return new ResponseEntity<String>(itemService.save(itemMapper.mappingItem(request)).toString() + "saved into DB", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (PersistException e) {
            return new ResponseEntity<String>("PersistException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    http://localhost:8080/delete/101
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", produces = "text/plain")
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            itemService.delete(Long.parseLong(id));
            return new ResponseEntity<String>("Item id : " + id + " was deleted. ", HttpStatus.OK);
        } catch (PersistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
http://localhost:8080/get/101
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}", produces = "text/plain")
    public ResponseEntity<String> findById(@PathVariable String id) {
/*
for me test
 */

        System.out.println("Transaction is open? " + TransactionSynchronizationManager.isActualTransactionActive());

        try {
            return new ResponseEntity<String>(itemService.findById(Long.parseLong(id)).toString(), HttpStatus.OK);


        } catch (PersistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    http://localhost:8080/update
     {
     "id":"102",
     "description":"22"
     }
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    public ResponseEntity<String> update(HttpServletRequest req) {
        try {
            return new ResponseEntity<String>(itemService.update(itemMapper.mappingItem(req)).toString() + " was updated in DB. ", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (PersistException e) {
            return new ResponseEntity<String>("PersistException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
