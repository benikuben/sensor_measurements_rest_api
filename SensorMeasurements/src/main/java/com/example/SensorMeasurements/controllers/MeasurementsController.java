package com.example.SensorMeasurements.controllers;

import com.example.SensorMeasurements.dto.MeasurementDto;
import com.example.SensorMeasurements.dto.RainyDaysCountResponse;
import com.example.SensorMeasurements.models.Measurement;
import com.example.SensorMeasurements.services.MeasurementService;
import com.example.SensorMeasurements.util.ErrorMessage;
import com.example.SensorMeasurements.util.MeasurementValidator;
import com.example.SensorMeasurements.util.ValidationErrorResponse;
import com.example.SensorMeasurements.util.ValidationException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;

    public MeasurementsController(ModelMapper modelMapper, MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid MeasurementDto measurementDto,
                                           BindingResult bindingResult) {
        measurementValidator.validate(measurementDto, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(ErrorMessage.createErrorMessage(bindingResult));
        }

        measurementService.save(convertToMeasurement(measurementDto));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementDto> index() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDto).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public RainyDaysCountResponse countRainyDays() {
        return new RainyDaysCountResponse("Количество дождливых дней", measurementService.countRainyDays(true));
    }

    private Measurement convertToMeasurement(MeasurementDto measurementDto) {
        return modelMapper.map(measurementDto, Measurement.class);
    }

    private MeasurementDto convertToMeasurementDto(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDto.class);
    }

    @ExceptionHandler
    private ResponseEntity<ValidationErrorResponse> handleException(ValidationException e) {
        return new ResponseEntity<>(new ValidationErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
