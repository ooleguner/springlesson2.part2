package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    private DAO dao;

    @Autowired
    public ItemController(DAO dao) {
        this.dao = dao;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/save-item", produces = "text/plain")
    public @ResponseBody String saveItem() {
      Item item = new Item();
      item.setDescription("Test Description");
      dao.save(item);
      return "ok";
    }
}
