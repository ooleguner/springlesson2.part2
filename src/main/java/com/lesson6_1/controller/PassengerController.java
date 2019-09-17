package com.lesson6_1.controller;

import com.lesson6_1.helpers.GeneralMapper;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.model.Plane;
import com.lesson6_1.service.PassengerService;
import com.lesson6_1.service.PlaneService;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
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
            return new ResponseEntity<String>(passengerService.savePassenger(passenger).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

/*
       http://localhost:8080/delPasenger?idPasenger=1005
*/
    @RequestMapping(method = RequestMethod.DELETE, value = "/delPasenger", produces = "text/plain")
    public ResponseEntity<String> delPasengert(HttpServletRequest req)   {
        String idPasenger = req.getParameter("idPasenger");
        try {
            passengerService.delPasenger(Long.parseLong(idPasenger));
            return new ResponseEntity<String>("Passenger id: " + idPasenger + " was deleted. All flights was canceled", HttpStatus.OK);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<String>("Passenger id: " + idPasenger + " was not deleted. Child record found.", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


/*     примусово витерти навіть якщо є посилання на пасажира в їнших обєктах (польоту)
       http://localhost:8080/forceDelPasenger?idPasenger=1005
*/
    @RequestMapping(method = RequestMethod.DELETE, value = "/forceDelPasenger", produces = "text/plain")
    public ResponseEntity<String> forceDelPasengert(HttpServletRequest req)   {
        String idPasenger = req.getParameter("idPasenger");
            passengerService.forceDelPasenger(Long.parseLong(idPasenger));
            return new ResponseEntity<String>("Passenger id: " + idPasenger + " was forced deleted. All flights was canceled", HttpStatus.OK);
    }

}
