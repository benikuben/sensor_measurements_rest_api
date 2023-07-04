package com.example.SensorMeasurements.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class SensorDto {

    @NotNull(message = "Name should not be empty")
    @Length(min = 3, max = 30, message = "Name's length should be between 3 and 30")
    private String name;
}
