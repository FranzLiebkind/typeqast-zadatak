package com.typeqast.meterreadings.controller;

import com.typeqast.meterreadings.domain.dto.MeterDTO;
import com.typeqast.meterreadings.service.MeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MeterController {

    private final Logger log = LoggerFactory.getLogger(MeterController.class);

    private final MeterService meterService;


    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    @GetMapping("/meters")
    public List<MeterDTO> getMeters() {
        log.debug("REST request to get all Meters");
        return meterService.findAll();
    }

    @GetMapping("/meters/{id}")
    public MeterDTO getMeterReadings(@PathVariable Long id) {
        log.debug("REST request to get Meter by id: " + id);
        return meterService.findById(id);
    }
}
