package com.lesson6_1.service;

import com.lesson6_1.model.Passenger;
import com.lesson6_1.repository.RepositoryInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PassengerService {

    RepositoryInterface repositoryInterface;

    public PassengerService(RepositoryInterface repositoryInterface){
        this.repositoryInterface=repositoryInterface;
    }


    public Passenger savePassenger(Passenger passenger){
        return (Passenger) repositoryInterface.save(passenger);
    }
}
