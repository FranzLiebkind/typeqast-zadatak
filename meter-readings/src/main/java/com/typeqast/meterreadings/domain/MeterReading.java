package com.typeqast.meterreadings.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.typeqast.meterreadings.domain.enumeration.Month;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meter_reading")
public class MeterReading implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "month")
    private Month month;

    @Column(name = "electricity_consumption")
    private Double electricityConsumption;

    @ManyToOne
    @JsonIgnoreProperties("meterReadings")
    private Meter meter;

    public MeterReading() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Double getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(Double electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }
}
