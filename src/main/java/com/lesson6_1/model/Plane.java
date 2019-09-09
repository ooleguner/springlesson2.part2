package com.lesson6_1.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lesson6_1.helpers.LocalDateDeserializer;
import com.lesson6_1.helpers.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PLANE")
public class Plane {
    private Long id;
    private String model;
    private String code;
    private LocalDate yearProduced;
    private Double avgFuelConsumption;

    public Plane() {
    }

    public Plane(String model, String code, LocalDate yearProduced, Double avgFuelConsumption) {
        this.model = model;
        this.code = code;
        this.yearProduced = yearProduced;
        this.avgFuelConsumption = avgFuelConsumption;
    }

    @Id
    @SequenceGenerator(name = "PLANE_SEQ", sequenceName = "PLANE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANE_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "YEAR_PRODUCED")
    public LocalDate getYearProduced() {
        return yearProduced;
    }

    public void setYearProduced(LocalDate yearProduced) {
        this.yearProduced = yearProduced;
    }
@Column(name = "AVG_FUEL_CONSUMPTION")
    public Double getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setAvgFuelConsumption(Double avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", code='" + code + '\'' +
                ", yearProduced=" + yearProduced +
                ", avgFuelConsumption=" + avgFuelConsumption +
                '}';
    }
}
