package com.lesson6.controller;

import com.lesson5.PersistException;
import com.lesson6.melpers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionSynchronizationManager;
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




/*
@Controller
public class ItemController {
    @Autowired
    ItemService itemService;


    @RequestMapping(method = RequestMethod.GET, value = "/items", produces = "text/plain")
    public @ResponseBody String getItems() {
        try {
            return itemService.getAllItems().toString();
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }


    http://localhost:8080/item?ID=3011

    @RequestMapping(method = RequestMethod.GET, value = "/item", produces = "text/plain")
    public @ResponseBody
    String getItemByID(HttpServletRequest req, HttpServletResponse resp) {
        try {
            return itemService.getByID(Integer.parseInt(req.getParameter("ID"))).toString();
        } catch (NumberFormatException parseException) {
            return "ID has wrong format - only digit is allowed :  " + parseException.getMessage();
        } catch (HibernateException e) {
            return e.getMessage();
        } catch (ItemExistException e) {
            return e.getMessage();
        }
    }


    http://localhost:8080/delete?ID=3011

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    String doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            itemService.delete(Integer.parseInt(req.getParameter("ID")));
            return "Item with ID " + req.getParameter("ID") + " is deleted";
        } catch (NumberFormatException parseException) {
            return "ID has wrong format - only digit is allowed :  " + parseException.getMessage();
        } catch (HibernateException e) {
            return e.getMessage();
        } catch (ItemExistException e) {
            return e.getMessage();
        }
    }


{"name":"33e3",
"dateCreated":"2019-07-02",
"lastUpdatedDate":"2019-07-02",
"description":"Tes222tOlko2"}


    @RequestMapping(method = RequestMethod.POST, value = "/addItem", produces = "text/plain")
    public @ResponseBody
    String  saveItem(HttpServletRequest req) {

        try {
           return "Item " + itemService.save(mappingItem(req)).toString() +" successfully saved";
        } catch (IOException e) {
           return e.getMessage();
        } catch (ItemExistException e) {
           return e.getMessage();
        } catch (HibernateException e) {
           return e.getMessage();
        }
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    public @ResponseBody
    String  updateItem(HttpServletRequest req) {

        try {
            return "Item " + itemService.update(mappingItem(req)).toString() +" successfully updated";
        } catch (IOException e) {
            return e.getMessage();
        } catch (ItemExistException e) {
            return e.getMessage();
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    private Item mappingItem(HttpServletRequest req) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        String line;
        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return objectMapper.readValue(stringBuilder.toString(), Item.class);
    }
*/

