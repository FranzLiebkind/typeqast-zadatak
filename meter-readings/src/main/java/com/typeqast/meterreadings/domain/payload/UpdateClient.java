package com.typeqast.meterreadings.domain.payload;

import com.typeqast.meterreadings.domain.Address;
import com.typeqast.meterreadings.domain.Meter;

public class UpdateClient {

    private String firstName;

    private String lastName;

    private Address address;

    private Meter meter;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }
}
