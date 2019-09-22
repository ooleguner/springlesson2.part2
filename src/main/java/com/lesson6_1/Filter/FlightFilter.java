package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> filter (String param);
}
