package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by oleg on 22.09.2019.
 */
public class Filter {

    FlightFilter oneDayFilter;
    FlightFilter cityFromFilter;
    FlightFilter datesFlightFilter;
    FlightFilter cityToFilter;
    FlightFilter modelPlaneFilter;

    public Filter(FlightFilter oneDayFilter, FlightFilter datesFlightFilter, FlightFilter cityFromFilter, FlightFilter cityToFilter, FlightFilter modelPlaneFilter) {
        this.oneDayFilter = oneDayFilter;
        this.cityFromFilter = cityFromFilter;
        this.datesFlightFilter = datesFlightFilter;
        this.cityToFilter = cityToFilter;
        this.modelPlaneFilter = modelPlaneFilter;
    }


    public Filter() {
    }

    public void setOneDayFilter(FlightFilter oneDayFilter) {
        this.oneDayFilter = oneDayFilter;
    }

    public void setCityFromFilter(FlightFilter cityFromFilter) {
        this.cityFromFilter = cityFromFilter;
    }

    public void setDatesFlightFilter(FlightFilter datesFlightFilter) {
        this.datesFlightFilter = datesFlightFilter;
    }

    public void setCityToFilter(FlightFilter cityToFilter) {
        this.cityToFilter = cityToFilter;
    }

    public void setModelPlaneFilter(FlightFilter modelPlaneFilter) {
        this.modelPlaneFilter = modelPlaneFilter;
    }

    public FlightFilter getOneDayFilter() {
        return oneDayFilter;
    }

    public FlightFilter getCityFromFilter() {
        return cityFromFilter;
    }

    public FlightFilter getDatesFlightFilter() {
        return datesFlightFilter;
    }

    public FlightFilter getCityToFilter() {
        return cityToFilter;
    }

    public FlightFilter getModelPlaneFilter() {
        return modelPlaneFilter;
    }
}
