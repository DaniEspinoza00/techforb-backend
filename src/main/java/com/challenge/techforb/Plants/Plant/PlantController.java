package com.challenge.techforb.Plants.Plant;

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
    public ResponseEntity<List<PlantDashboard>> getAllPlants(){
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    @GetMapping("/{id}")
    public Optional<PlantDTO> getPlantById(@PathVariable Long id){
        return  plantService.getPlantById(id);
    }

    @PostMapping
    public ResponseEntity<PlantEntity> createPlant(@RequestBody PlantEntity plantEntity){
        return ResponseEntity.ok(plantService.savePlant(plantEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantEntity>updatePlant(@PathVariable Long id, @RequestBody PlantEntity plantEntity){
        return ResponseEntity.ok(plantService.editPlant(id,plantEntity));
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id){
        plantService.deletePlant(id);
    }

    @PutMapping("/{id}/sensor")
    public ResponseEntity<PlantDTO>addSensortoPlant(@PathVariable Long id, @RequestBody SensorEntity sensorEntity){
        return ResponseEntity.ok(plantService.addSensorToPlant(id,sensorEntity));
    }
}
