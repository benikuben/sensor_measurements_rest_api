package com.example.SensorMeasurements.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "sensor")
@NoArgsConstructor
@Getter
@Setter
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Length(min = 3, max = 30, message = "Name's length should be between 3 and 30")
    private String name;

    @OneToMany(mappedBy = "sensor")
    @JsonManagedReference
    private List<Measurement> measurements;
}
