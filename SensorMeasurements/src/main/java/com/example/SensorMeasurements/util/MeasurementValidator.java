package com.example.SensorMeasurements.util;


import com.example.SensorMeasurements.dto.MeasurementDto;
import com.example.SensorMeasurements.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDto measurementDto = (MeasurementDto) target;

        if (sensorService.findByName(measurementDto.getSensor().getName())==null)
            errors.rejectValue("sensor", "", "Sensor with this name not exists");
    }
}
