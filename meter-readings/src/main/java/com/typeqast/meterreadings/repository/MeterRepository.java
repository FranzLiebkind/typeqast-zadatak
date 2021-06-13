package com.typeqast.meterreadings.repository;

import com.typeqast.meterreadings.domain.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
}
