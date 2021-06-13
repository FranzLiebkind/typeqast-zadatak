package com.typeqast.meterreadings.controller;

import com.typeqast.meterreadings.domain.dto.MeterReadingAggregatedDTO;
import com.typeqast.meterreadings.domain.dto.MeterReadingCreateDTO;
import com.typeqast.meterreadings.domain.dto.MeterReadingDTO;
import com.typeqast.meterreadings.domain.enumeration.Month;
import com.typeqast.meterreadings.service.MeterReadingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class MeterReadingController {

    private final Logger log = LoggerFactory.getLogger(MeterReadingController.class);

    private final MeterReadingService meterReadingService;

    public MeterReadingController(MeterReadingService meterReadingService) {
        this.meterReadingService = meterReadingService;
    }

    @GetMapping("/meter-readings")
    public MeterReadingDTO getMeterReadingsByMonthOrYearOrBoth(@RequestParam(name = "month", required = false) Month month,
                                                               @RequestParam(name = "year") Integer year) {
        log.debug("REST request to get all Meter Readings by year and by month");
        return meterReadingService.findByYearOrMonthAndYear(month, year);
    }

    @GetMapping("/meter-readings/aggregated")
    public MeterReadingAggregatedDTO getMeterReadingsByYearAggregated(@RequestParam(name = "year") Integer year) {
        log.debug("REST request to get all Meter Readings by year aggregated");
        return meterReadingService.findAllByYearAggregated(year);
    }

    @PostMapping("/meter-readings")
    public ResponseEntity<MeterReadingDTO> saveMeterReading(@Valid @RequestBody MeterReadingCreateDTO meterReadingCreateDTO) {
        log.debug("REST request to save Meter Reading");

        MeterReadingDTO meterReadingDTO = meterReadingService.save(meterReadingCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(meterReadingDTO);
    }
}
