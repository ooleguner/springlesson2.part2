package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;

import java.util.List;

public interface FlightFilter {
    String[] params = null;

    List<Flight> filter ();

    String[] getParam();
}
