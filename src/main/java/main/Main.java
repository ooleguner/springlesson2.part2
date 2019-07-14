package main;

import controller.ItemController;
import exception.RepoAccessEcxeption;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by oleg on 09.07.2019.
 */
//@Controller
//public class Main {
//    @Autowired
//    ItemController itemController;
//
//    @RequestMapping(method = RequestMethod.GET, value = "/items", produces = "texp/plain")
//    public @ResponseBody
//    String itemsList(){
//        System.out.println("0");
//        try {
//            System.out.println("1");
//            return itemController.getAllItems().toString();
//        } catch (RepoAccessEcxeption repoAccessEcxeption) {
//            repoAccessEcxeption.printStackTrace();
//        }
//        System.out.println("2");
//        return null;
//    }
//
//
////    String test(){
////        return "ok";
////    }
//
//
//    @RequestMapping(method = RequestMethod.POST, value = "/hello", produces = "texp/plain")
//    public @ResponseBody String hello(){
//        return "hello";
////    }
//}