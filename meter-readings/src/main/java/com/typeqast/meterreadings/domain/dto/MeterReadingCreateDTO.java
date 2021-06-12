package com.typeqast.meterreadings.domain.dto;

import com.typeqast.meterreadings.domain.enumeration.Month;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MeterReadingCreateDTO implements Serializable {

    private Integer year;

    private Month month;
    @NotNull
    private Long meterId;

    private Double electricityConsumption;

    public MeterReadingCreateDTO(Integer year, Month month, Long meterId, Double electricityConsumption) {
        this.year = year;
        this.month = month;
        this.meterId = meterId;
        this.electricityConsumption = electricityConsumption;
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

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Double getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(Double electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }
}
