package com.lesson6_1.controller;

import com.lesson6_1.helpers.GeneralMapper;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.model.Plane;
import com.lesson6_1.service.PassengerService;
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
public class PassengerController {

    private GeneralMapper generalMapper;
    private PassengerService passengerService;

    @Autowired
    public PassengerController(GeneralMapper generalMapper, PassengerService passengerService) {
        this.generalMapper = generalMapper;
        this.passengerService = passengerService;
    }

/*
{"lastName":"Shevchenko",
"nationality":"Ukr",
"dateOfBirth":"1980-09-11",
"passportCode":"123456789"}
*/
    @RequestMapping(method = RequestMethod.POST, value = "/savePassenger", produces = "text/plain")
    public ResponseEntity<String> save(HttpServletRequest request) {
        try {
            Passenger passenger = generalMapper.mappingObject(request, Passenger.class);
            return new ResponseEntity<String>(passengerService.savePassenger(passenger).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
