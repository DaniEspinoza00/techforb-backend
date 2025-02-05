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
    public List<SensorEntity> getAllSensors(){
        return sensorService.getAllSensors();
    }

    @GetMapping("/{id}")
    public Optional<SensorEntity> getSensorById(@PathVariable Long id){
        return  sensorService.getSensorById(id);
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
