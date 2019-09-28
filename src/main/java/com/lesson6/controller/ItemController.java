package com.lesson6.controller;

import com.lesson6.exception.ItemExistException;
import com.lesson6.helpers.ItemMapper;
import com.lesson6.model.Item;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lesson6.service.ItemService;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;


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
      {"description":"testSaveItem",
      "lastUpdatedDate":"2019-02-02",
      "dateCreated":"2019-03-03",
      "name":"ggg"}
    */
    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public ResponseEntity<String> save(HttpServletRequest request) {
        try {
            Item item = itemMapper.mappingItem(request);

            itemService.save(item);
            return new ResponseEntity<String>(item.toString() + "saved into DB", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ItemExistException e) {
            return new ResponseEntity<String>("PersistException " + e.getMessage(), HttpStatus.CONFLICT);
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
        }
    }

    /*
http://localhost:8080/get/101
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}", produces = "text/plain")
    public ResponseEntity<String> findById(@PathVariable String id) {
        try {
            return new ResponseEntity<String>(itemService.findById(Long.parseLong(id)).toString(), HttpStatus.OK);
        } catch (IllegalArgumentException  e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ItemExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);  //404
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
        }
    }

    /*
    http://localhost:8080/update
     {
    {"description":"testSaveItem",
      "lastUpdatedDate":"2019-02-02",
      "dateCreated":"2019-03-03",
      "name":"ggg"}
     }
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    public ResponseEntity<String> update(HttpServletRequest req) {
        try {
            return new ResponseEntity<String>(itemService.update(itemMapper.mappingItem(req)).toString() + " was updated in DB. ", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ItemExistException e) {
            return new ResponseEntity<String>("PersistException " + e.getMessage(), HttpStatus.CONFLICT);
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
        }
    }

    /*
    http://localhost:8080/deleteByName/ss
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteByName/{name}", produces = "text/plain")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        try {
            int count = itemService.deleteByName(name);
            return new ResponseEntity<String>(count + " Items with name like : " + name + " was deleted", HttpStatus.OK);
        } catch (IllegalArgumentException  e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ItemExistException e) {
            return new ResponseEntity<String>("PersistException " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
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
        } catch (IllegalArgumentException  e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ItemExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
        }
    }
}
