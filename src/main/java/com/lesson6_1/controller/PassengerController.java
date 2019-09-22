package com.lesson6_1.controller;

import com.lesson6_1.exception.ObjectExistException;
import com.lesson6_1.helpers.GeneralMapper;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            return new ResponseEntity<String>("savePassenger(). Result:  success" + passengerService.savePassenger(passenger).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("ObjectExistException : " + e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    /*
           http://localhost:8080/delPasenger?idPasenger=1005
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delPasenger", produces = "text/plain")
    public ResponseEntity<String> delPasengert(HttpServletRequest req) {
        String idPasenger = req.getParameter("idPasenger");
        try {
            passengerService.delPasenger(Long.parseLong(idPasenger));
            return new ResponseEntity<String>("Passenger id: " + idPasenger + " was deleted. All flights was canceled", HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>("Passenger id: " + idPasenger + " was not deleted. Child record found.", HttpStatus.METHOD_NOT_ALLOWED);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("Pasenger id: " + idPasenger + " was not deleted. Pasenger with id: " + idPasenger + " not found in DB.\n" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    /*     примусово витерти навіть якщо є посилання на пасажира в їнших обєктах (польоту)
           http://localhost:8080/forceDelPasenger?idPasenger=1005
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/forceDelPasenger", produces = "text/plain")
    public ResponseEntity<String> forceDelPasengert(HttpServletRequest req) {
        String idPasenger = req.getParameter("idPasenger");
        try {
            passengerService.forceDelPasenger(Long.parseLong(idPasenger));
            return new ResponseEntity<String>("Passenger id: " + idPasenger + " was forced deleted. All flights was canceled", HttpStatus.OK);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("Pasenger id: " + idPasenger + " not found in DB. ", HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getPasenger", produces = "text/plain")
    public ResponseEntity<String> getPasengert(HttpServletRequest req) {
        String idPasenger = req.getParameter("idPasenger");
        Passenger passenger = null;
        try {
            passenger = passengerService.getPasenger(Long.parseLong(idPasenger));
            return new ResponseEntity<String>("Passenger id: " + idPasenger + " found in DB. " + passenger.toString(), HttpStatus.OK);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("Pasenger id: " + idPasenger + " not found in DB. ", HttpStatus.NOT_FOUND);
        }
    }
/*
       {"lastName":"ShevchenkoUpdated",
       "nationality":"Ukr",
       "dateOfBirth":"1980-09-11",
       "passportCode":"000"}
*/
    @RequestMapping(method = RequestMethod.PUT, value = "/updatePasenger", produces = "text/plain")
    public ResponseEntity<String> updatePasenger(HttpServletRequest request) {
        try {
            Passenger passenger = generalMapper.mappingObject(request, Passenger.class);
            return new ResponseEntity<String>("updatePasenger(). Result:  success" +passengerService.updatePassenger(passenger).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
