package com.typeqast.meterreadings.repository;

import com.typeqast.meterreadings.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
