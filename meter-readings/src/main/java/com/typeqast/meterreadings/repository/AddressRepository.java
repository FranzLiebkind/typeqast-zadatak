package com.typeqast.meterreadings.repository;

import com.typeqast.meterreadings.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findByStreet(String street);
}
