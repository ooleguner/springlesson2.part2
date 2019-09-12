package com.lesson6_1.service;

import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.repository.RepositoryInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FlightService {

    RepositoryInterface repositoryInterface;

    public FlightService(RepositoryInterface repositoryInterface){
        this.repositoryInterface=repositoryInterface;
    }


    public Flight saveFlight(Flight flight){
        return (Flight) repositoryInterface.save(flight);
    }
}
