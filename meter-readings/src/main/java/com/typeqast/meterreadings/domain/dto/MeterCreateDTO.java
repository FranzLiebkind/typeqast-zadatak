package com.typeqast.meterreadings.domain.dto;

import java.io.Serializable;

public class MeterCreateDTO implements Serializable {

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
