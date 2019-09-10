package com.lesson6_1.controller;

import com.lesson6_1.helpers.GeneralMapper;
import com.lesson6_1.model.Plane;
import com.lesson6_1.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class PlaneController {

    private GeneralMapper generalMapper;
    private PlaneService planeService;

    @Autowired
    public PlaneController(GeneralMapper generalMapper, PlaneService planeService) {
        this.generalMapper = generalMapper;
        this.planeService = planeService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public ResponseEntity<String> save(HttpServletRequest request) {
        try {
            Plane plane = generalMapper.mappingObject(request, Plane.class);
            return new ResponseEntity<String>(planeService.savePlane(plane).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
