package com.example.SensorMeasurements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RainyDaysCountResponse {
    private String message;
    private long rainyDays;
}
