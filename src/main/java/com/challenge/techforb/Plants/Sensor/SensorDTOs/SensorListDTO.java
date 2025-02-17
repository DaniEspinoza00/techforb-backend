package com.challenge.techforb.Plants.Sensor.SensorDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorListDTO {
    private Long idSensor;
    private String sensorName;
    private int okLectures;
    private int mediaRangeAlert;
    private int redAlert;
    private Long idPlant;
}