package com.typeqast.meterreadings.service.contract;

import com.typeqast.meterreadings.domain.dto.MeterDTO;

import java.util.List;

public interface IMeterService {

    List<MeterDTO> findAll();

    MeterDTO findById(Long id);
}
