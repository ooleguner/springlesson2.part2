package com.lesson6_1.service;

import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FlightService {

    RepositoryInterface repositoryFlight;
    RepositoryInterface repositoryPassenger;

    @Autowired
    public FlightService(RepositoryInterface repositoryFlight, RepositoryInterface repositoryPassenger) {
        this.repositoryFlight = repositoryFlight;
        this.repositoryPassenger = repositoryPassenger;
    }


    public Flight saveFlight(Flight flight) {
        return (Flight) repositoryFlight.save(flight);
    }

    @Transactional
    public void addPasengerToFlight(long idPasenger, long idFlight) {
        Passenger passenger = (Passenger) repositoryPassenger.findById(idPasenger);
        Flight flight = (Flight) repositoryFlight.findById(idFlight);

        if (passenger != null && flight != null) {
            flight.getPassengers().add(passenger);
            passenger.getFlights().add(flight);

  //          repositoryPassenger.update(passenger);
            repositoryFlight.update(flight);

            Flight flightTest  = (Flight) repositoryFlight.findById(idFlight);
            Passenger passengerTest = (Passenger) repositoryPassenger.findById(idPasenger);

            System.out.println(flightTest.getPassengers().size());
            System.out.println(passengerTest.getFlights().size());
        }
    }
}
