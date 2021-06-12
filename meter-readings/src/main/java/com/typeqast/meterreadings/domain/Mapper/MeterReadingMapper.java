package com.typeqast.meterreadings.domain.Mapper;

import com.typeqast.meterreadings.domain.MeterReading;
import com.typeqast.meterreadings.domain.dto.MeterReadingCreateDTO;
import com.typeqast.meterreadings.domain.dto.MeterReadingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {})
@Component
public interface MeterReadingMapper extends EntityMapper<MeterReadingDTO, MeterReading>{

    MeterReadingDTO toDto(MeterReading meterReading);

    MeterReading createDtoToEntity(MeterReadingCreateDTO meterReading);

    @Mapping(source = "meter.id", target = "meterId")
    MeterReadingCreateDTO entityToCreateDto(MeterReading meterReading);
}
