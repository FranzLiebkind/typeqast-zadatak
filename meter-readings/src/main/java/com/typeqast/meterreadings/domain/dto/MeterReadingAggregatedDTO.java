package com.typeqast.meterreadings.domain.dto;

import java.io.Serializable;

public class MeterReadingAggregatedDTO implements Serializable {

    private Integer year;
    private Double electricityConsumptionAggregated;

    public MeterReadingAggregatedDTO() {
        this.year = year;
        this.electricityConsumptionAggregated = electricityConsumptionAggregated;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getElectricityConsumptionAggregated() {
        return electricityConsumptionAggregated;
    }

    public void setElectricityConsumptionAggregated(Double electricityConsumptionAggregated) {
        this.electricityConsumptionAggregated = electricityConsumptionAggregated;
    }
}
