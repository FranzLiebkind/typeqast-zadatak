package com.typeqast.meterreadings.service.contract;

import com.typeqast.meterreadings.domain.Meter;

import java.util.List;

public interface IMeterService {

    List<Meter> findAll();

    Meter findById(Long id);
}
