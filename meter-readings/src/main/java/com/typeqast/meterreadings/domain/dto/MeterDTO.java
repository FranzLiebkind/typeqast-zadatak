package com.typeqast.meterreadings.domain.dto;

import java.io.Serializable;

public class MeterDTO implements Serializable {

    private Long id;
    private String model;
    private Long clientId;

    public MeterDTO(Long id, String model, Long clientId) {
        this.id = id;
        this.model = model;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
