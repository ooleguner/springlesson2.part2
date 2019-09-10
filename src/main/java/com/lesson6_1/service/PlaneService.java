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

    RepositoryInterface planeRepository;

    @Autowired
    public PlaneService(RepositoryInterface planeRepository){
        this.planeRepository = planeRepository;
    }

    public Plane savePlane(Plane plane){
      return (Plane) planeRepository.save(plane);
    }
}
