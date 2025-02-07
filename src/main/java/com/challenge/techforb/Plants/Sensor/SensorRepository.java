package com.challenge.techforb.Plants.Sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity,Long> {
    List<SensorEntity> findByPlantId (Long id);
}
