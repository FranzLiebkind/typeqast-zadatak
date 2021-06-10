package com.typeqast.meterreadings.service;

import com.typeqast.meterreadings.domain.Meter;
import com.typeqast.meterreadings.repository.MeterRepository;
import com.typeqast.meterreadings.service.contract.IMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MeterService implements IMeterService {

    private final MeterRepository meterRepository;

    @Autowired
    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    @Override
    public List<Meter> findAll() {
        return (List<Meter>) meterRepository.findAll();
    }

    @Override
    public Meter findById(Long id) {
        Optional<Meter> meter = meterRepository.findById(id);

        if (meter.isEmpty())
            throw new EntityNotFoundException("Meter doesn't exist for id: " + id);

        return meter.get();
    }
}
