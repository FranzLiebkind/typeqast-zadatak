package com.typeqast.meterreadings.repository;

import com.typeqast.meterreadings.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    Iterable<Address> findByStreet(String street);
}
