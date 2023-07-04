package com.example.SensorMeasurements.services;

import com.example.SensorMeasurements.models.Measurement;
import com.example.SensorMeasurements.models.Sensor;
import com.example.SensorMeasurements.repositories.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MeasurementService {

    private final SensorService sensorService;
    private final MeasurementRepository measurementRepository;

    public MeasurementService(SensorService sensorService, MeasurementRepository measurementRepository) {
        this.sensorService = sensorService;
        this.measurementRepository = measurementRepository;
    }

    public void save(Measurement measurement) {
        Sensor sensorWithId = sensorService.findByName(measurement.getSensor().getName());
        measurement.setSensor(sensorWithId);
        measurement.setTime(new Date());
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAllMeasurementsWithSensors();
    }

    public long countRainyDays(Boolean raining) {
        return measurementRepository.countByRaining(raining);
    }
}

