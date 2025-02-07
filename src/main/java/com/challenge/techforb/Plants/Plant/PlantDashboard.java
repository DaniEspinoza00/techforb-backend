package com.challenge.techforb.Plants.Plant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantDashboard {
    private Long id;
    private String country;
    private String name;
    private int sumOk;
    private int sumMediaAlert;
    private int sumRedAlert;
}
