package com.challenge.techforb.Plants.Sensor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDTO {
    private Long idSensor;
    private String sensorName;
    private int okLectures;
    private int mediaRangeAlert;
    private int redAlert;
}
