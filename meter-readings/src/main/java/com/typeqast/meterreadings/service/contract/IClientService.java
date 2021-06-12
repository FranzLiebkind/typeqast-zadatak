package com.typeqast.meterreadings.service.contract;

import com.typeqast.meterreadings.domain.dto.ClientCreateDTO;
import com.typeqast.meterreadings.domain.dto.ClientDTO;

public interface IClientService {

    ClientDTO save(ClientCreateDTO clientCreateDTO);
}
