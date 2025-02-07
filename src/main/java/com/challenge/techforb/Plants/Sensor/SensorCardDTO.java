package com.challenge.techforb.Plants.Sensor;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorCardDTO {
    private int sumOkLectures;
    private int sumMediaAlert;
    private int sumRedAlert;
    private int disabledSensors;//misma suma de redAlert
}
