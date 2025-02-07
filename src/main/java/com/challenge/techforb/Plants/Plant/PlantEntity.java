package com.challenge.techforb.Plants.Plant;

import com.challenge.techforb.Plants.Sensor.SensorEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="plant")
public class PlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Se debe insertar un país")
    private String country;

    @Column(nullable = false)
    @NotBlank(message = "Se debe insertar un nombre")
    private String name;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SensorEntity> sensors = new ArrayList<>();

    public void addSensorToPlant (SensorEntity sensor){
        sensors.add(sensor);
        sensor.setPlant(this);
    }
    public void DeleteSensor (SensorEntity sensor){
        sensors.remove(sensor);
        sensor.setPlant(null);
    }
}
