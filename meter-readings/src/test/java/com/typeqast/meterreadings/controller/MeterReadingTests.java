package com.typeqast.meterreadings.controller;

import com.typeqast.meterreadings.MeterReadingsApplication;
import com.typeqast.meterreadings.controller.util.TestUtil;
import com.typeqast.meterreadings.domain.Mapper.MeterReadingMapper;
import com.typeqast.meterreadings.domain.Meter;
import com.typeqast.meterreadings.domain.MeterReading;
import com.typeqast.meterreadings.domain.dto.MeterReadingCreateDTO;
import com.typeqast.meterreadings.domain.enumeration.Month;
import com.typeqast.meterreadings.repository.MeterReadingRepository;
import com.typeqast.meterreadings.repository.MeterRepository;
import com.typeqast.meterreadings.service.MeterReadingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MeterReadingsApplication.class)
public class MeterReadingTests {

    public static final double ELECTRICITY_CONSUMPTION = 22.5;
    public static final Month MONTH = Month.OCTOBER;
    public static final Month EXISTING_MONTH = Month.JANUARY;
    public static final Integer YEAR = 2021;

    @Autowired
    private MeterReadingRepository meterReadingRepository;

    @Autowired
    private MeterReadingMapper meterReadingMapper;

    @Autowired
    MeterReadingService meterReadingService;

    @Autowired
    private MeterRepository meterRepository;

    @Autowired
    private EntityManager em;

    private MockMvc restMockMvc;

    @Autowired
    private MeterReading meterReading;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MeterReadingController meterReadingController = new MeterReadingController(meterReadingService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(meterReadingController).build();
    }

    public static MeterReading createMeterReadingEntity() {
        MeterReading meterReading = new MeterReading();
        meterReading.setElectricityConsumption(ELECTRICITY_CONSUMPTION);
        meterReading.setMonth(MONTH);
        meterReading.setYear(YEAR);

        return meterReading;
    }

    public static Meter createMeterEntity() {
        Meter meter = new Meter();
        meter.setModel("TCZ-45A");

        return meter;
    }

    @BeforeEach
    public void initTest() {
        meterReading = createMeterReadingEntity();
        Meter meter = createMeterEntity();

        meter = meterRepository.save(meter);
        meterReading.setMeter(meter);
    }

    @Test
    @Transactional
    public void  createMeterReading() throws Exception {
        int databaseSizeBeforeCreate = meterReadingRepository.findAll().size();

        // Create the Meter Reading
        MeterReadingCreateDTO meterReadingDTO = meterReadingMapper.entityToCreateDto(meterReading);

        restMockMvc.perform(post("/api/meter-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(meterReadingDTO)))
            .andExpect(status().isCreated());

        // Validate the MeterReading in the database
        List<MeterReading> meterReadings = meterReadingRepository.findAll();
        assertThat(meterReadings).hasSize(databaseSizeBeforeCreate + 1);
        MeterReading testMeterReading = meterReadings.get(meterReadings.size() - 1);
        assertThat(testMeterReading.getElectricityConsumption()).isEqualTo(ELECTRICITY_CONSUMPTION);
        assertThat(testMeterReading.getMonth()).isEqualTo(MONTH);
        assertThat(testMeterReading.getYear()).isEqualTo(YEAR);
    }

    @Test
    @Transactional
    public void getAllMeterReadings() throws Exception {
        // Initialize the database
        meterReadingRepository.saveAndFlush(meterReading);

        // Get all the meter readings
        restMockMvc.perform(get("/api/meter-readings?year=2021"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.year").value(YEAR))
                .andExpect(jsonPath("$.meterReadings").exists());
    }
    @Test
    @Transactional
    public void createExistingMeterReadingShouldThrowError() throws Exception {
        // Create the Meter Reading
        meterReading.setMonth(EXISTING_MONTH);

        MeterReadingCreateDTO meterReadingDTO = meterReadingMapper.entityToCreateDto(meterReading);

        restMockMvc.perform(post("/api/meter-readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(meterReadingDTO)))
                .andExpect(status().is5xxServerError());
    }
}
