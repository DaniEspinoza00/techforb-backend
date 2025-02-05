package com.challenge.techforb.Plants.Sensor;

import com.challenge.techforb.Plants.Plant.PlantEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
@Builder
@Table(name="sensor")
public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sensor;
    @Column(nullable = false)
    @NotBlank(message = "Se debe insertar el nombre del sensor")
    private String sensorName;
    @Column(nullable = false)
    @NotNull(message = "Se debe insertar lecturas correctas")
    private int okLectures;
    @Column(nullable = false)
    @NotNull(message = "Se debe insertar alertas medias")
    private int mediaRangeAlert;
    @Column(nullable = false)
    @NotNull(message = "Se debe insertar alertas rojas")
    private int redAlert;

    @ManyToOne
    @JoinColumn(name = "id_plant")
    private PlantEntity plant;
}
