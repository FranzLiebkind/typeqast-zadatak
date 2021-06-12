package com.typeqast.meterreadings.service.contract;

import com.typeqast.meterreadings.domain.dto.MeterReadingAggregatedDTO;
import com.typeqast.meterreadings.domain.dto.MeterReadingCreateDTO;
import com.typeqast.meterreadings.domain.dto.MeterReadingDTO;
import com.typeqast.meterreadings.domain.enumeration.Month;

public interface IMeterReadingService {

    MeterReadingDTO save(MeterReadingCreateDTO meterReadingCreateDTO);

    MeterReadingDTO findByYearOrMonthAndYear(Month month, Integer year);

    MeterReadingAggregatedDTO findAllByYearAggregated(Integer year);
}
