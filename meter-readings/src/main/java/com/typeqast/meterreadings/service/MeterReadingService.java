package com.typeqast.meterreadings.service;

import com.typeqast.meterreadings.domain.Mapper.MeterReadingMapper;
import com.typeqast.meterreadings.domain.Meter;
import com.typeqast.meterreadings.domain.MeterReading;
import com.typeqast.meterreadings.domain.dto.MeterReadingAggregatedDTO;
import com.typeqast.meterreadings.domain.dto.MeterReadingCreateDTO;
import com.typeqast.meterreadings.domain.dto.MeterReadingDTO;
import com.typeqast.meterreadings.domain.enumeration.Month;
import com.typeqast.meterreadings.exceptions.EntityExistsException;
import com.typeqast.meterreadings.exceptions.EntityNotFoundException;
import com.typeqast.meterreadings.repository.MeterReadingRepository;
import com.typeqast.meterreadings.repository.MeterRepository;
import com.typeqast.meterreadings.service.contract.IMeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class MeterReadingService implements IMeterReadingService {

    private final MeterReadingRepository meterReadingRepository;
    private final MeterReadingMapper meterReadingMapper;
    private final MeterRepository meterRepository;

    @Autowired
    public MeterReadingService(MeterReadingRepository meterReadingRepository, MeterReadingMapper meterReadingMapper, MeterRepository meterRepository) {
        this.meterReadingRepository = meterReadingRepository;
        this.meterReadingMapper = meterReadingMapper;
        this.meterRepository = meterRepository;
    }

    @Override
    public MeterReadingDTO save(MeterReadingCreateDTO meterReadingCreateDTO) {
        checkIfMeterReadingAlreadyExists(meterReadingCreateDTO);

        Meter meter = getMeterByIdIfExists(meterReadingCreateDTO.getMeterId());

        MeterReading meterReading = meterReadingMapper.createDtoToEntity(meterReadingCreateDTO);
        meterReading.setMeter(meter);
        meterReading = meterReadingRepository.save(meterReading);

        return getMeterReadingDTO(meterReading);
    }

    private void checkIfMeterReadingAlreadyExists(MeterReadingCreateDTO meterReadingCreateDTO) {
        MeterReading existingMeterReading = meterReadingRepository.findByMonthAndYear(meterReadingCreateDTO.getMonth(), meterReadingCreateDTO.getYear());

        if (nonNull(existingMeterReading)) {
            throw new EntityExistsException(String.format("Meter reading exists for month: %s and year: %d", meterReadingCreateDTO.getMonth(),
                    meterReadingCreateDTO.getYear()));
        }
    }

    @Override
    public MeterReadingDTO findByYearOrMonthAndYear(Month month, Integer year) {
        if (nonNull(month)) {
            return getMeterReadingByMonthAndYearAndMapToDTO(month, year);
        }
        return getMeterReadingsByYearAndMapToDTO(year);
    }

    private MeterReadingDTO getMeterReadingsByYearAndMapToDTO(Integer year) {
        List<MeterReading> meterReadings = getMeterReadingsForYearIfExists(year);

        Map<Month, Double> monthElectricityConsumptionMap = new HashMap<>();
        meterReadings.forEach(meterReading -> monthElectricityConsumptionMap.put(meterReading.getMonth(), meterReading.getElectricityConsumption()));

        MeterReadingDTO meterReadingDTO = new MeterReadingDTO();
        meterReadingDTO.setYear(year);
        meterReadingDTO.setMeterReadings(monthElectricityConsumptionMap);

        return meterReadingDTO;
    }

    private MeterReadingDTO getMeterReadingByMonthAndYearAndMapToDTO(Month month, Integer year) {
        MeterReading meterReading = meterReadingRepository.findByMonthAndYear(month, year);

        if (isNull(meterReading)) {
            throw new EntityNotFoundException(String.format("There is no meter reading for month: %s and year: %d", month, year));
        }

        return getMeterReadingDTO(meterReading);
    }

    private MeterReadingDTO getMeterReadingDTO(MeterReading meterReading) {
        Map<Month, Double> monthElectricityConsumptionMap = new HashMap<>();
        monthElectricityConsumptionMap.put(meterReading.getMonth(), meterReading.getElectricityConsumption());

        MeterReadingDTO meterReadingDTO = new MeterReadingDTO();
        meterReadingDTO.setYear(meterReading.getYear());
        meterReadingDTO.setMeterReadings(monthElectricityConsumptionMap);
        return meterReadingDTO;
    }

    @Override
    public MeterReadingAggregatedDTO findAllByYearAggregated(Integer year) {
        List<MeterReading> meterReadings = getMeterReadingsForYearIfExists(year);

        Double electricityConsumptionAggregated = meterReadings.stream().mapToDouble(MeterReading::getElectricityConsumption).sum();

        MeterReadingAggregatedDTO meterReadingAggregatedDTO = new MeterReadingAggregatedDTO();
        meterReadingAggregatedDTO.setYear(year);
        meterReadingAggregatedDTO.setElectricityConsumptionAggregated(electricityConsumptionAggregated);

        return meterReadingAggregatedDTO;
    }

    private List<MeterReading> getMeterReadingsForYearIfExists(Integer year) {
        List<MeterReading> meterReadings = meterReadingRepository.findAllByYear(year);

        if (isNull(meterReadings) || meterReadings.isEmpty()) {
            throw new EntityNotFoundException(String.format("There are no meter readings for year: %d", year));
        }
        return meterReadings;
    }

    private Meter getMeterByIdIfExists(Long meterId) {
        Optional<Meter> meter = meterRepository.findById(meterId);

        if (!meter.isPresent()) {
            throw new EntityNotFoundException("Meter doesn't exists for id: " + meterId);
        }

        return meter.get();
    }
}
