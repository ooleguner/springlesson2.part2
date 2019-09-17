package com.lesson6_1.service;

import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.repository.RepositoryInterface;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PassengerService {

    RepositoryInterface repositoryInterface;

    public PassengerService(RepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }


    public Passenger savePassenger(Passenger passenger) {
        return (Passenger) repositoryInterface.save(passenger);
    }

    public void delPasenger(long l) throws DataIntegrityViolationException {
        Passenger passenger = (Passenger) repositoryInterface.findById(l);
        try {
            repositoryInterface.delete(passenger);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }

// примусово витерти навіть якщо є посилання на пасажира в їнших обєктах (польоту)
    public void forceDelPasenger(long l) {
        Passenger passenger = (Passenger) repositoryInterface.findById(l);
        for (Flight flight : passenger.getFlights()) {
            flight.getPassengers().remove(passenger);
        }
        repositoryInterface.delete(passenger);
    }
}
