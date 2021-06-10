package com.typeqast.meterreadings.service.contract;

import com.typeqast.meterreadings.domain.Client;
import com.typeqast.meterreadings.domain.payload.UpdateClient;

import java.util.Optional;

public interface IClientService {

    Optional<Client> updateClientById(Long clientId, UpdateClient updateClient);
}
