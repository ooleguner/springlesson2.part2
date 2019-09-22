package com.lesson6_1.service;

import com.lesson6_1.exception.ObjectExistException;
import com.lesson6_1.model.Plane;
import com.lesson6_1.repository.FilterHelper;
import com.lesson6_1.repository.PlaneRepository;
import com.lesson6_1.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.FileHandler;

@Service
@Transactional
public class PlaneService {

    RepositoryInterface repositoryInterface;
    FilterHelper filterHelper;

    @Autowired
    public PlaneService(RepositoryInterface repositoryInterface, FilterHelper filterHelper) {
        this.repositoryInterface = repositoryInterface;
        this.filterHelper = filterHelper;
    }

    public Plane savePlane(Plane plane) throws ObjectExistException {
        // перевірити чи вже є такий літак
        checkIfExist(plane);
        return (Plane) repositoryInterface.save(plane);
    }

    public void delPlane(long l) throws ObjectExistException {
        Plane plane = (Plane) repositoryInterface.findById(l);
        if (plane == null) {
            throw new ObjectExistException("Method delPlane(). Plane with id: " + l + "not found ib DB");
        }
        try {
            repositoryInterface.delete(plane);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }

    public Plane getPlane(long l) throws ObjectExistException {
        Plane plane = (Plane) repositoryInterface.findById(l);
        if (plane == null) {
            throw new ObjectExistException("Plane with id: " + l + " not found in DB. ");
        }
        return plane;
    }

    public Plane updatePlane(Plane plane) throws ObjectExistException {
        // перевірка чи є такий літак з заданним ІД
        Plane planeInDB = (Plane) repositoryInterface.findById(plane.getId());
        if (planeInDB == null) {
            throw new ObjectExistException("Plane with id: " + plane.getId() + " not found in DB. ");
        }
        // перевірка чи вже є літак з тими ж MODEL та CODE на який поновлюємо
        checkIfExist(plane);
        // саме поновлення
        return (Plane) repositoryInterface.update(plane);
    }

    private void checkIfExist(Plane plane) throws ObjectExistException {
        if (repositoryInterface.checkIfPresent(plane)) {
            throw new ObjectExistException("Plane with same MODEL " + plane.getModel() + " and CODE " + plane.getCode() + " is already present in DB.");
        }
    }


    public List<Plane> getOldPlanes() {
        return filterHelper.oldPlanes();
    }

    public String getRegularPlanes(int year) {
        List<Object[]>  result =  filterHelper.getRegularPlanes(year);

        if (result.isEmpty() || result.size()==0){
            return "There is no one plane with more than 300 flights per year " + year;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(Object[] o : result){
            stringBuilder.append("Planes with more than 300 flights per year " + year + "\n" +
                    "Plane id: " + o[1] + " Model : " + o[2] + ". Count of flights " + o[0] + "\n" );
        }
        String s = stringBuilder.toString();
        return s;
    }
}
