package com.lesson6_1.service;

import com.lesson6_1.model.Plane;
import com.lesson6_1.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlaneService {

    PlaneRepository planeRepository;

    @Autowired
    public PlaneService(PlaneRepository planeRepository){
        this.planeRepository = planeRepository;
    }

    public Plane savePlane(Plane plane){
      return planeRepository.savePlane(plane);
    }
}
