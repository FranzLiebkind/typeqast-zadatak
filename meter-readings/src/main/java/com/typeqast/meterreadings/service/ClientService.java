package com.typeqast.meterreadings.service;

import com.typeqast.meterreadings.domain.Address;
import com.typeqast.meterreadings.domain.Client;
import com.typeqast.meterreadings.domain.Mapper.AddressMapper;
import com.typeqast.meterreadings.domain.Mapper.ClientMapper;
import com.typeqast.meterreadings.domain.Mapper.MeterMapper;
import com.typeqast.meterreadings.domain.Meter;
import com.typeqast.meterreadings.domain.dto.ClientCreateDTO;
import com.typeqast.meterreadings.domain.dto.ClientDTO;
import com.typeqast.meterreadings.exceptions.EntityExistsException;
import com.typeqast.meterreadings.repository.AddressRepository;
import com.typeqast.meterreadings.repository.ClientRepository;
import com.typeqast.meterreadings.repository.MeterRepository;
import com.typeqast.meterreadings.service.contract.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final MeterMapper meterMapper;
    private final MeterRepository meterRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, MeterMapper meterMapper,
                         MeterRepository meterRepository, AddressRepository addressRepository, AddressMapper addressMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.meterMapper = meterMapper;
        this.meterRepository = meterRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public ClientDTO save(ClientCreateDTO clientCreateDTO) {
        checkIfAddressAlreadyExists(clientCreateDTO.getAddress().getStreet());

        Client client = clientMapper.createDtoToEntity(clientCreateDTO);

        Address address = addressMapper.toEntity(clientCreateDTO.getAddress());
        address.setClient(client);

        Meter meter = meterMapper.createDtoToEntity(clientCreateDTO.getMeter());
        meter.setClient(client);

        Client newSavedClient = clientRepository.save(client);

        meterRepository.save(meter);
        addressRepository.save(address);

        newSavedClient.setAddress(address);
        newSavedClient.setMeter(meter);

        return clientMapper.toDto(newSavedClient);
    }

    private void checkIfAddressAlreadyExists(String street) {
        Optional<Address> existingAddress = addressRepository.findByStreet(street);

        if (existingAddress.isPresent()) {
            throw new EntityExistsException(String.format("Address by street name: %s already exists!", street));
        }
    }
}
