package com.example.SensorMeasurements.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "measurement")
@NoArgsConstructor
@Getter
@Setter
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Indicator of whether it was raining should not be empty")
    private Boolean raining;

    @NotNull(message = "Sensor's name should not be empty")
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "id")
    @JsonBackReference
    private Sensor sensor;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
}
