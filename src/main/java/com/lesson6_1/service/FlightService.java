package com.lesson6_1.service;

import com.lesson6_1.exception.ObjectExistException;
import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.repository.FilterHelper;
import com.lesson6_1.repository.FlightRepository;
import com.lesson6_1.repository.PassengerRepository;
import com.lesson6_1.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FlightService {

    FlightRepository repositoryFlight;
    PassengerRepository repositoryPassenger;

    @Autowired
    public FlightService(FlightRepository repositoryFlight, PassengerRepository repositoryPassenger) {
        this.repositoryFlight = repositoryFlight;
        this.repositoryPassenger = repositoryPassenger;
    }

    @Transactional
    public Flight saveFlight(Flight flight) throws ObjectExistException {
        // перевірити чи вже є такий політ
        checkIfExist(flight);

        return (Flight) repositoryFlight.save(flight);
    }

    private void checkIfExist(Flight flight) throws ObjectExistException {
        if (repositoryFlight.checkIfPresent(flight)) {
            throw new ObjectExistException("Flight on Plane MODEL: " + flight.getPlane().getModel() + " from " + flight.getCityFrom() + " to " + flight.getCityTo() + " on " + flight.getDateFlight() + " is already present in DB.");
        }
    }


    @Transactional
    public Flight addPasengerToFlight(long idPasenger, long idFlight) throws ObjectExistException {

        Passenger passenger = (Passenger) repositoryPassenger.findById(idPasenger);
        Flight flight = (Flight) repositoryFlight.findById(idFlight);

        if (passenger != null && flight != null) {
            flight.getPassengers().add(passenger);
            passenger.getFlights().add(flight);

            return (Flight) repositoryFlight.update(flight);
        } else {
            throw new ObjectExistException("Method : addPasengerToFlight(). Flight or Pasenger not found on DB. Flight id " + idFlight + " Pasenger id " + idPasenger);
        }
    }

    @Transactional
    public void delFlight(long l) throws ObjectExistException {
        Flight flight = (Flight) repositoryFlight.findById(l);
        if (flight == null) {
            throw new ObjectExistException("Method delFlite(). Flite with id: " + l + "not found ib DB");
        }
        try {
            repositoryFlight.delete(flight);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }

    @Transactional
    public Flight getFlight(long l) throws ObjectExistException {
        Flight flight = (Flight) repositoryFlight.findById(l);
        if (flight == null) {
            throw new ObjectExistException("Flight with id: " + l + " not found in DB. ");
        }
        return flight;
    }

    @Transactional
    public Flight updateFlight(Flight flight) throws ObjectExistException {

        // перевірка чи є такий flight з заданним ІД
        Flight flightInDB = (Flight) repositoryFlight.findById(flight.getId());
        if (flightInDB == null) {
            throw new ObjectExistException("Plane with id: " + flight.getId() + " not found in DB. ");
        }
        // перевірка чи вже є flight з тими ж MODEL та CityFrom, CityTo,  DateFlight  на який поновлюємо
        checkIfExist(flight);
        // саме поновлення
        return (Flight) repositoryFlight.update(flight);
    }

    @Transactional
    public String regularPassengers(int year) {
        List<Object[]> result = repositoryPassenger.customersWithMoreThan25Flights(year);

        if (result.isEmpty() || result.size() == 0) {
            return "There is no one customer with more than 25 flights";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Object[] o : result) {
            stringBuilder.append("Passsenger id: " + o[1] + " Last Name : " + o[2] + ". Count of flights " + o[0] + "\n");
        }
        String s = stringBuilder.toString();
        System.out.println(s);
        return s;
    }

    @Transactional
    public String mostPopularTo() {
        List<Object[]> result = repositoryFlight.mostPopularTo();
        if (result.isEmpty() || result.size() == 0) {
            return "List of flights is empty.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object[] o : result) {
            stringBuilder.append("CITY NAME " + o[1] + " Count of flights " + o[0] + "\n");
        }
        String s = stringBuilder.toString();
        System.out.println(s);
        return s;
    }

    @Transactional
    public String mostPopularFrom() {
        List<Object[]> result = repositoryFlight.mostPopularFrom();
        if (result.isEmpty() || result.size() == 0) {
            return "List of flights is empty.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object[] o : result) {
            stringBuilder.append("CITY NAME " + o[1] + " Count of flights " + o[0] + "\n");
        }
        String s = stringBuilder.toString();
        System.out.println(s);
        return s;
    }

    public String flightsByDate(String model, String dateString, String cityFrom, String dateBegin, String dateEnd, String cityTo) {
        List<Flight> result = repositoryFlight.flightsByDate(model, dateString, cityFrom, dateBegin, dateEnd, cityTo);

        StringBuilder stringBuilder = new StringBuilder();
        if (result.size() != 0) {
            for (Flight flight : result) {
                stringBuilder.append(flight.toString() + "\n");
            }
            return stringBuilder.toString();
        }
        return "NO ONE FLIGHT FOUND";
    }
}