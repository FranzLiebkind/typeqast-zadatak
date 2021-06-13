package com.typeqast.meterreadings.service;

import com.typeqast.meterreadings.domain.Mapper.MeterMapper;
import com.typeqast.meterreadings.domain.Meter;
import com.typeqast.meterreadings.domain.dto.MeterDTO;
import com.typeqast.meterreadings.exceptions.EntityNotFoundException;
import com.typeqast.meterreadings.repository.MeterRepository;
import com.typeqast.meterreadings.service.contract.IMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeterService implements IMeterService {

    private final MeterRepository meterRepository;
    private final MeterMapper meterMapper;

    @Autowired
    public MeterService(MeterRepository meterRepository, MeterMapper meterMapper) {
        this.meterRepository = meterRepository;
        this.meterMapper = meterMapper;
    }


    @Override
    public List<MeterDTO> findAll() {
        return meterRepository.findAll().stream().map(meterMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public MeterDTO findById(Long id) {
        Optional<Meter> meter = meterRepository.findById(id);

        if (!meter.isPresent())
            throw new EntityNotFoundException("Meter doesn't exist for id: " + id);

        return meterMapper.toDto(meter.get());
    }
}
