package com.lesson6_1.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lesson6_1.helpers.LocalDateDeserializer;
import com.lesson6_1.helpers.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "FLIGHT")
public class Flight {

    private Long id;
    private Plane plane;
    private Collection<Passenger> passengers;
    private LocalDate dateFlight;
    private String cityFrom;
    private String cityTo;

    public Flight() {
    }

    public Flight(Plane plane, LocalDate dateFlight, String cityFrom, String cityTo) {
        this.plane = plane;
        this.dateFlight = dateFlight;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
    }

    public Flight(LocalDate dateFlight, String cityFrom, String cityTo) {
        this.dateFlight = dateFlight;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
    }


    @Id
    @SequenceGenerator(name = "FLIGHT_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLIGHT_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "PLANE_ID")
    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }


    @ManyToMany
    @JoinTable(name = "JOIN_FLIGHT_PASSENGER",
            joinColumns = {@JoinColumn(name = "FLIGHT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PASSENGER_ID")})
    public Collection<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Collection passengers) {
        this.passengers = passengers;
    }

    @Column(name = "DATEFLIGHT")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(LocalDate dateFlight) {
        this.dateFlight = dateFlight;
    }

    @Column(name = "CITYFROM")
    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    @Column(name = "CITYTO")
    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", plane=" + plane +
                ", dateFlight=" + dateFlight +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' + "}";
    }
}