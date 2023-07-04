package com.example.SensorMeasurements.util;

import com.example.SensorMeasurements.dto.SensorDto;
import com.example.SensorMeasurements.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDto sensorDto = (SensorDto) target;

        if (sensorService.findByName(sensorDto.getName())!=null)
            errors.rejectValue("name", "", "Sensor with this name already exists");
    }
}
