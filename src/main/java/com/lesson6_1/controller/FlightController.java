package com.lesson6_1.controller;

import com.lesson6_1.exception.ObjectExistException;
import com.lesson6_1.helpers.GeneralMapper;
import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.service.FlightService;
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
public class FlightController {

    private GeneralMapper generalMapper;
    private FlightService flightService;

    @Autowired
    public FlightController(GeneralMapper generalMapper, FlightService flightService) {
        this.generalMapper = generalMapper;
        this.flightService = flightService;
    }


/*
http://localhost:8080/saveFlight
{"plane":{"id":"1022", "model":"oleg_today","code":"2433","yearProduced":"2019-03-03","avgFuelConsumption":"200"},
"dateFlight":"1980-09-11",
"cityFrom":"Kiev",
"cityTo":"Lvov"}
 */

    @RequestMapping(method = RequestMethod.POST, value = "/saveFlight", produces = "text/plain")
    public ResponseEntity<String> save(HttpServletRequest request) {
        try {
            Flight flight = generalMapper.mappingObject(request, Flight.class);
            return new ResponseEntity<String>("saveFlight(). Result:  success" + flightService.saveFlight(flight).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("ObjectExistException : " + e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    /*
    ***  ADD PASENGER TO FLIGHT
         http://localhost:8080/addPasengerToFlight?idPasenger=452&idFlight=968
    */
    @RequestMapping(method = RequestMethod.PUT, value = "/addPasengerToFlight", produces = "text/plain")
    public ResponseEntity<String> addPasengerToFlight(HttpServletRequest req) {
        String idPasenger = req.getParameter("idPasenger");
        String idFlight = req.getParameter("idFlight");
        try {
            flightService.addPasengerToFlight(Long.parseLong(idPasenger), Long.parseLong(idFlight));
            return new ResponseEntity<String>("Passenger id: " + idPasenger + " was regustered to flight id : " + idFlight, HttpStatus.OK);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /*
           http://localhost:8080/delFlight?idFlight=1005
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delFlight", produces = "text/plain")
    public ResponseEntity<String> delFlight(HttpServletRequest req) {
        String idFlight = req.getParameter("idFlight");
        try {
            flightService.delFlight(Long.parseLong(idFlight));
            return new ResponseEntity<String>("Flight id: " + idFlight + " was deleted.", HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>("Flight id: " + idFlight + " was not deleted. Child record found.", HttpStatus.METHOD_NOT_ALLOWED);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("Flight id: " + idFlight + " not found in DB. ", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getFlight", produces = "text/plain")
    public ResponseEntity<String> getFlight(HttpServletRequest req) {
        String idFlight = req.getParameter("idFlight");
        try {
            Flight flight = flightService.getFlight(Long.parseLong(idFlight));
            return new ResponseEntity<String>("Flight id: " + idFlight + " found in DB. " + flight.toString(), HttpStatus.OK);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("Flight id: " + idFlight + " not found in DB. ", HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/updateFlight", produces = "text/plain")
    public ResponseEntity<String> updateFlight(HttpServletRequest request) {
        try {
            Flight flight = generalMapper.mappingObject(request, Flight.class);
            return new ResponseEntity<String>("updateFlight(). Result:  success" + flightService.updateFlight(flight).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (ObjectExistException e) {
            return new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    //regularPassengers(int year) - пассажиры, с больше 25 полетов за год
    @RequestMapping (method = RequestMethod.GET, value = "/regularPassengers", produces = "text/plain")
    public ResponseEntity<String> regularPassengers(HttpServletRequest request , int year){
        String yearStr = request.getParameter("year");
        year = Integer.parseInt(yearStr);
        return new ResponseEntity<String>("RegularPassengers(int year). Result:  SUCCESS.  \n" + flightService.regularPassengers(year), HttpStatus.OK);
    }

    // mostPopularTo() - список ТОП 10 самых популярных рейсов по городам назначения
    @RequestMapping (method = RequestMethod.GET, value = "/mostPopularTo", produces = "text/plain")
    public ResponseEntity<String> mostPopularTo(){

        return new ResponseEntity<String>("MostPopularTo(). Result:  SUCCESS.  \n" + flightService.mostPopularTo(), HttpStatus.OK);
    }

  //  mostPopularFrom() - список ТОП 10 самых популярных рейсов по городам вылета
  @RequestMapping (method = RequestMethod.GET, value = "/mostPopularFrom", produces = "text/plain")
  public ResponseEntity<String> mostPopularFrom(){

      return new ResponseEntity<String>("mostPopularFrom(). Result:  SUCCESS.  \n" + flightService.mostPopularFrom(), HttpStatus.OK);
  }

  // flightsByDate(Filter filter) - список рейсов по дате (в один день), по промежутку даты (с даты-по дату) городу отправки, городу назначения, модели самолета

  @RequestMapping (method = RequestMethod.GET, value = "/flightsByDate", produces = "text/plain")
  public ResponseEntity<String> flightsByDate(HttpServletRequest request) {
      String dateOfFlite = request.getParameter("dateOfFlite");

      return new ResponseEntity<String>("flightsByDate(). Result:  SUCCESS.  \n" + flightService.flightsByDate(dateOfFlite), HttpStatus.OK);
  }
}
