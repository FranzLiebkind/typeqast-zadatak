package com.typeqast.meterreadings.domain.Mapper;

import com.typeqast.meterreadings.domain.Address;
import com.typeqast.meterreadings.domain.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
}
