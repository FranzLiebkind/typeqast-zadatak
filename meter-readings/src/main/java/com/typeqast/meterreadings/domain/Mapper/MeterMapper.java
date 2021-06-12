package com.typeqast.meterreadings.domain.Mapper;

import com.typeqast.meterreadings.domain.Meter;
import com.typeqast.meterreadings.domain.dto.MeterCreateDTO;
import com.typeqast.meterreadings.domain.dto.MeterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MeterMapper extends EntityMapper<MeterDTO, Meter>{

    @Mapping(source = "client.id", target = "clientId")
    MeterDTO toDto(Meter meter);

    Meter createDtoToEntity(MeterCreateDTO meterCreateDTO);
}
