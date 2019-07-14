package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ItemExistException;
import exception.RepoAccessEcxeption;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ItemService;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;


    @RequestMapping(method = RequestMethod.GET, value = "/items", produces = "text/plain")
    public @ResponseBody
    String getItems() {
        try {
            return itemService.getAllItems().toString();
        } catch (RepoAccessEcxeption repoAccessEcxeption) {
            return repoAccessEcxeption.getMessage();
        }
    }

    /*
    http://localhost:8080/item?ID=3011
     */
    @RequestMapping(method = RequestMethod.GET, value = "/item", produces = "text/plain")
    public @ResponseBody
    String getItemByID(HttpServletRequest req, HttpServletResponse resp) {
        try {
            return itemService.getByID(Integer.parseInt(req.getParameter("ID"))).toString();
        } catch (NumberFormatException parseException) {
            return "ID has wrong format - only digit is allowed :  " + parseException.getMessage();
        } catch (RepoAccessEcxeption repoAccessEcxeption) {
            return repoAccessEcxeption.getMessage();
        } catch (ItemExistException e) {
            return e.getMessage();
        }
    }

    /*
    http://localhost:8080/delete?ID=3011
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    String doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            itemService.delete(Integer.parseInt(req.getParameter("ID")));
            return "Item with ID " + req.getParameter("ID") + " is deleted";
        } catch (NumberFormatException parseException) {
            return "ID has wrong format - only digit is allowed :  " + parseException.getMessage();
        } catch (RepoAccessEcxeption repoAccessEcxeption) {
            return repoAccessEcxeption.getMessage();
        } catch (ItemExistException e) {
            return e.getMessage();
        }
    }

    /*
{"name":"33e3",
"dateCreated":"2019-07-02",
"lastUpdatedDate":"2019-07-02",
"description":"Tes222tOlko2"}
     */

    @RequestMapping(method = RequestMethod.POST, value = "/addItem", produces = "text/plain")
    public @ResponseBody
    String  saveItem(HttpServletRequest req) {

        try {
           return "Item " + itemService.save(mappingItem(req)).toString() +" successfully saved";
        } catch (IOException e) {
           return e.getMessage();
        } catch (ItemExistException e) {
           return e.getMessage();
        } catch (RepoAccessEcxeption repoAccessEcxeption) {
           return repoAccessEcxeption.getMessage();
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
        } catch (RepoAccessEcxeption repoAccessEcxeption) {
            return repoAccessEcxeption.getMessage();
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
}
