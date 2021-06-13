package com.typeqast.meterreadings.repository;

import com.typeqast.meterreadings.domain.MeterReading;
import com.typeqast.meterreadings.domain.enumeration.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReading, Long> {

    MeterReading findByMonthAndYear(Month month, Integer year);

    List<MeterReading> findAllByYear(Integer year);
}
