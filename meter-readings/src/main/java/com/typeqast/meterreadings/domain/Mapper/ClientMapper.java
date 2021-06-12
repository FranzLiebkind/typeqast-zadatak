package com.typeqast.meterreadings.domain.Mapper;

import com.typeqast.meterreadings.domain.Client;
import com.typeqast.meterreadings.domain.dto.ClientCreateDTO;
import com.typeqast.meterreadings.domain.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ClientMapper extends EntityMapper<ClientDTO, Client>{

    @Mapping(target = "address", ignore = true)
    @Mapping(target = "meter", ignore = true)
    Client createDtoToEntity(ClientCreateDTO clientCreateDTO);
}
