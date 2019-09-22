package com.lesson6_1.service;

import com.lesson6_1.exception.ObjectExistException;
import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.repository.RepositoryInterface;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.transaction.Transactional;

@Service
@Transactional
public class PassengerService {

    RepositoryInterface repositoryInterface;

    public PassengerService(RepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }


    public Passenger savePassenger(Passenger passenger) throws ObjectExistException {
// перевірити чи є такий пасажир
        checkIfExist(passenger);
        return (Passenger) repositoryInterface.save(passenger);
    }

    public void delPasenger(long l) throws DataIntegrityViolationException, ObjectExistException {
        Passenger passenger = (Passenger) repositoryInterface.findById(l);
        // перевіримо чи є такий пасажир по ІД
        if (passenger == null) {
            throw new ObjectExistException("Method delPasenger(). Pasenger with id: " + l + "not found ib DB");
        }
        // якщо є посилання на цього пасажира - отримаємо DataIntegrityViolationException. який прокинемо наверх
        try {
            repositoryInterface.delete(passenger);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }

    // примусово витерти навіть якщо є посилання на пасажира в їнших обєктах (польоту)
    public void forceDelPasenger(long l) throws ObjectExistException {
        Passenger passenger = (Passenger) repositoryInterface.findById(l);
        if (passenger == null) {
            throw new ObjectExistException("Method delPasenger(). Pasenger with id: " + l + "not found ib DB");
        }
        for (Flight flight : passenger.getFlights()) {
            flight.getPassengers().remove(passenger);
        }
        repositoryInterface.delete(passenger);
    }

    public Passenger getPasenger(long l) throws ObjectExistException {
        Passenger passenger = (Passenger) repositoryInterface.findById(l);
        if (passenger == null) {
            throw new ObjectExistException("Pasenger with id: " + l + " not found in DB. ");
        }
        return passenger;
    }

    public Passenger updatePassenger(Passenger passenger) throws ObjectExistException {
        // перевірка чи є такий пасажир з заданним ІД
        Passenger passengerInDB = (Passenger) repositoryInterface.findById(passenger.getId());
        if (passengerInDB == null) {
            throw new ObjectExistException("Plane with id: " + passenger.getId() + " not found in DB. ");
        }
        // перевірка чи вже є літак з тими ж MODEL та CODE на який поновлюємо
        checkIfExist(passenger);
        // саме поновлення
        return (Passenger) repositoryInterface.update(passenger);
    }

    private void checkIfExist(Passenger passenger) throws ObjectExistException {
        if (repositoryInterface.checkIfPresent(passenger)) {
            throw new ObjectExistException("Passenger " + passenger.toString() + " is already present in DB.");
        }
    }

}
