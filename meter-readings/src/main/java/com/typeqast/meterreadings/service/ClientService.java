package com.typeqast.meterreadings.service;

import com.typeqast.meterreadings.domain.Address;
import com.typeqast.meterreadings.domain.Client;
import com.typeqast.meterreadings.domain.payload.UpdateClient;
import com.typeqast.meterreadings.repository.AddressRepository;
import com.typeqast.meterreadings.repository.ClientRepository;
import com.typeqast.meterreadings.service.contract.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;


    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<Client> updateClientById(Long clientId, UpdateClient updateClient) {
        checkIfAddressAlreadyExists(updateClient.getAddress().getStreet());

        final Optional<Client> updatedClient;

        updatedClient = this.clientRepository
                .findById(clientId)
                .map(client -> {
                    if (!"".equals(updateClient.getFirstName()) && updateClient.getFirstName() != null) {
                        client.setFirstName(updateClient.getFirstName());
                    }
                    if (!"".equals(updateClient.getLastName()) && updateClient.getLastName() != null) {
                        client.setLastName(updateClient.getLastName());
                    }
                    if (updateClient.getAddress() != null) {
                        client.setAddress(updateClient.getAddress());
                    }
                    if (updateClient.getMeter() != null) {
                        client.setMeter(updateClient.getMeter());
                    }
                    return clientRepository.save(client);
                });
        return updatedClient;
    }

    private void checkIfAddressAlreadyExists(String street) {
        Optional<Address> existingAddress = addressRepository.findByStreet(street);

        if (existingAddress.isPresent()) {
            throw new EntityNotFoundException(String.format("Address by street name: %s already exists!", street));
        }
    }
}
