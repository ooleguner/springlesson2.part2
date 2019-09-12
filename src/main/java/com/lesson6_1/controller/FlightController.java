package com.lesson6_1.controller;

import com.lesson6_1.helpers.GeneralMapper;
import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.service.FlightService;
import com.lesson6_1.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FlightController {

    private GeneralMapper generalMapper;
    private FlightService flightService;

    @Autowired
    public FlightController(GeneralMapper generalMapper, FlightService flightService) {
        this.generalMapper = generalMapper;
        this.flightService = flightService;
    }

/*
{"lastName":"Shevchenko",
"nationality":"Ukr",
"dateOfBirth":"1980-09-11",
"passportCode":"123456789"}
*/
    @RequestMapping(method = RequestMethod.POST, value = "/saveFlight", produces = "text/plain")
    public ResponseEntity<String> save(HttpServletRequest request) {
        try {
            Flight flight = generalMapper.mappingObject(request, Flight.class);
            return new ResponseEntity<String>(flightService.saveFlight(flight).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
