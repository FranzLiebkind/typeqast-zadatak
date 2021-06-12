package com.typeqast.meterreadings.domain.dto;

import java.io.Serializable;

public class ClientDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private AddressDTO address;
    private MeterCreateDTO meter;

    public ClientDTO(Long id, String firstName, String lastName, AddressDTO address, MeterCreateDTO meter) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.meter = meter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public MeterCreateDTO getMeter() {
        return meter;
    }

    public void setMeter(MeterCreateDTO meter) {
        this.meter = meter;
    }
}
