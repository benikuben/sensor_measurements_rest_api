package com.example.SensorMeasurements.repositories;

import com.example.SensorMeasurements.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    long countByRaining(Boolean raining);

    @Query("SELECT m, s FROM Measurement m LEFT JOIN m.sensor s")
    List<Measurement> findAllMeasurementsWithSensors();
}
