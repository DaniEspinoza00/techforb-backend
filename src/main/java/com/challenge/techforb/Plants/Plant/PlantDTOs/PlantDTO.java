package com.challenge.techforb.Plants.Plant.PlantDTOs;

import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantDTO {
    private Long id;
    private String country;
    private String name;
    private List<SensorDTO> sensors;
}
