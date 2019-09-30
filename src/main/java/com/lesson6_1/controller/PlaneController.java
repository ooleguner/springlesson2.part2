package com.lesson6_1.controller;

import com.lesson6_1.exception.ObjectExistException;
import com.lesson6_1.helpers.GeneralMapper;
import com.lesson6_1.model.Plane;
import com.lesson6_1.service.PlaneService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class PlaneController {

    private GeneralMapper generalMapper;
    private PlaneService planeService;

    @Autowired
    public PlaneController(GeneralMapper generalMapper, PlaneService planeService) {
        this.generalMapper = generalMapper;
        this.planeService = planeService;
    }

    /*
    {"model":"TestModelPlain",
    "code":"2433",
    "yearProduced":"2019-03-03",
    "avgFuelConsumption":"200"}
     */
    @RequestMapping(method = RequestMethod.POST, value = "/savePlane", produces = "text/plain")
    public ResponseEntity<String> save(HttpServletRequest request) {
        try {
            Plane plane = generalMapper.mappingObject(request, Plane.class);
            return new ResponseEntity<String>("Plane saved: " + planeService.savePlane(plane).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("ObjectExistException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
        }
    }

    /*
           http://localhost:8080/delPlane?idPlane=1005
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delPlane", produces = "text/plain")
    public ResponseEntity<String> delPlane(HttpServletRequest req) {
        String idPlane = req.getParameter("idPlane");
        try {
            planeService.delPlane(Long.parseLong(idPlane));
            return new ResponseEntity<String>("Plane id: " + idPlane + " was deleted.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>("Plane id: " + idPlane + " was not deleted. Child record found.", HttpStatus.METHOD_NOT_ALLOWED);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("Plane id: " + idPlane + " was not deleted. Plane with id: " + idPlane + " not found in DB.\n" + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPlane", produces = "text/plain")
    public ResponseEntity<String> getPlane(HttpServletRequest req) {
        String idPlane = req.getParameter("idPlane");
        Plane plane = null;
        try {
            plane = planeService.getPlane(Long.parseLong(idPlane));
            return new ResponseEntity<String>("Plane id: " + idPlane + " found in DB. " + plane.toString(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>("IOException " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>("Plane id: " + idPlane + " not found in DB. ", HttpStatus.NOT_FOUND);
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
        }
    }

    /*
    {"id":"1020"
    "model":"TestModelPlain",
    "code":"2433",
    "yearProduced":"2019-03-03",
    "avgFuelConsumption":"200"}
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/updatePlane", produces = "text/plain")
    public ResponseEntity<String> updatePlane(HttpServletRequest request) {
        try {
            Plane plane = generalMapper.mappingObject(request, Plane.class);
            return new ResponseEntity<String>("Plane was updated succesfully. " + planeService.updatePlane(plane).toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>("IOException : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ObjectExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (HibernateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  //500
        }
    }


    //  oldPlanes() - самолеты старше 20 лет
    @RequestMapping(method = RequestMethod.GET, value = "/oldPlanes", produces = "text/plain")
    public ResponseEntity<String> getPlane() {
        List<Plane> oldPlanes = planeService.getOldPlanes();
        if (oldPlanes == null || oldPlanes.isEmpty()) {
            return new ResponseEntity<String>("No one plane older 20 years found", HttpStatus.OK);
        }
        StringBuilder stringBuilderPlanes = new StringBuilder();
        for (Plane plane : oldPlanes) {
            stringBuilderPlanes.append(plane).append("\n");
        }
        return new ResponseEntity<String>("Planes older than 20 years: \n" + stringBuilderPlanes, HttpStatus.OK);
    }


    //regularPlanes(int year) - самолеты, которые с больше 300 полетов за год
    @RequestMapping(method = RequestMethod.GET, value = "/regularPlanes", produces = "text/plain")
    public ResponseEntity<String> regularPlanes(HttpServletRequest request) {
        String yearStr = request.getParameter("year");
        int year = Integer.parseInt(yearStr);
        String result = planeService.getRegularPlanes(year);
        return new ResponseEntity<String>("RegularPlanes() method: \n" + result, HttpStatus.OK);
    }
}
