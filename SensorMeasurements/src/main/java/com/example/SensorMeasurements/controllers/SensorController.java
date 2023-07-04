package com.example.SensorMeasurements.controllers;

import com.example.SensorMeasurements.dto.SensorDto;
import com.example.SensorMeasurements.models.Sensor;
import com.example.SensorMeasurements.services.SensorService;
import com.example.SensorMeasurements.util.ErrorMessage;
import com.example.SensorMeasurements.util.ValidationException;
import com.example.SensorMeasurements.util.ValidationErrorResponse;
import com.example.SensorMeasurements.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDto sensorDto,
                                               BindingResult bindingResult) {
        sensorValidator.validate(sensorDto, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(ErrorMessage.createErrorMessage(bindingResult));
        }

        sensorService.save(convertToSensor(sensorDto));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDto sensorDto) {
        return modelMapper.map(sensorDto, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<ValidationErrorResponse> handleException(ValidationException e) {
        return new ResponseEntity<>(new ValidationErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
