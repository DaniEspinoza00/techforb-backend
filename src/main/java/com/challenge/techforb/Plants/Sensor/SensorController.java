package com.challenge.techforb.Plants.Sensor;

import com.challenge.techforb.Plants.Plant.PlantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/techforb/sensors")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    public SensorCardDTO getAllSensors(){
        return sensorService.getSensorCards();
    }

    @GetMapping("/plant/{id}")
    public ResponseEntity<List<SensorDTO>> getSensorById(@PathVariable Long id){
        return ResponseEntity.ok(sensorService.getSensorsByPlantId(id));
    }

    @PostMapping
    public SensorEntity createSensor(@RequestBody SensorEntity plantEntity){
        return sensorService.saveSensor(plantEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorEntity> updateSensor(@PathVariable Long id, @RequestBody SensorEntity sensorEntity){
        return ResponseEntity.ok(sensorService.editSensor(id,sensorEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable Long id){
        sensorService.deleteSensor(id);
    }

}
