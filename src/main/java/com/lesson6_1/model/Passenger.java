package com.lesson6_1.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
@Entity
@Table(name = "PASSENGER")
public class Passenger {

    private Long id;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private String passportCode;
    private Collection flights;

    public Passenger() {
    }

    public Passenger(String lastName, String nationality, LocalDate dateOfBirth, String passportCode) {
        this.lastName = lastName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.passportCode = passportCode;
    }

    @Id
    @SequenceGenerator(name = "PASSENGER_SEQ", sequenceName = "PASSENGER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PASSENGER_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "NATIONALITY")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Column(name="DATEOFBIRTH")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "PASSPORTCODE")
    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }

    @ManyToMany
    @JoinTable(name = "JOIN_FLIGHT_PASSENGER",
    joinColumns = {@JoinColumn(name = "PASSENGER_ID")},
    inverseJoinColumns = {@JoinColumn(name = "FLIGHT_ID")})
    public Collection getFlights() {
        return flights;
    }

    public void setFlights(Collection flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passportCode='" + passportCode + '\'' +
              //  ", flights=" + flights +
                '}';
    }
}
