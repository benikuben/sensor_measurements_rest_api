package com.example.SensorMeasurements.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeasurementDto {

    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private Double value;

    @NotNull(message = "Indicator of whether it was raining should not be empty")
    private Boolean raining;

    @NotNull(message = "Sensor's name should not be empty")
    private SensorDto sensor;
}
