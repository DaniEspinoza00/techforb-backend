package com.challenge.techforb.Plants.Plant;

import com.challenge.techforb.Plants.Plant.PlantDTOs.PlantDTO;
import com.challenge.techforb.Plants.Sensor.SensorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/techforb/plant")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PlantController {
    private final PlantService plantService;

    @GetMapping
    public ResponseEntity<List<PlantDashboard>> getAllPlants(){//plantas para el dashboard
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    @PostMapping
    public ResponseEntity<PlantEntity> createPlant(@RequestBody PlantEntity plantEntity){ //crear planta
        return ResponseEntity.ok(plantService.savePlant(plantEntity));
    }

    @DeleteMapping("/{id}") //para eliminar la planta
    public void deletePlant(@PathVariable Long id){
        plantService.deletePlant(id);
    }

    @PutMapping("/{id}/sensor") //para agregar sensores a la planta
    public ResponseEntity<PlantDTO>addSensortoPlant(@PathVariable Long id, @RequestBody SensorEntity sensorEntity){
        return ResponseEntity.ok(plantService.addSensorToPlant(id,sensorEntity));
    }
}
