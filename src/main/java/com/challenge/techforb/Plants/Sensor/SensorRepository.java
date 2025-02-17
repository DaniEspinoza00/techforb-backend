package com.challenge.techforb.Plants.Sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity,Long> {
    List<SensorEntity> findByPlantId (Long id);
    Optional<SensorEntity> findBySensorName(String name);
}
