package com.lesson6_1.service;

import com.lesson6_1.model.Plane;
import com.lesson6_1.repository.PlaneRepository;
import com.lesson6_1.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlaneService {

    RepositoryInterface repositoryInterface;

    @Autowired
    public PlaneService(RepositoryInterface repositoryInterface){
        this.repositoryInterface = repositoryInterface;
    }

    public Plane savePlane(Plane plane){
      return (Plane) repositoryInterface.save(plane);
    }
}
