package com.challenge.techforb.Plants.Sensor;

import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorCardDTO;
import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorDTO;
import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorListDTO;
import com.challenge.techforb.Plants.Sensor.SensorDTOs.updateSensorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/techforb/sensors")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    public SensorCardDTO getAllSensors(){ //para la tarjetas de arriba del dashboard
        return sensorService.getSensorCards();
    }

    @GetMapping("/all")
    public ResponseEntity<List <SensorListDTO>> getSensorEntityList(){
        return ResponseEntity.ok(sensorService.getAllSensors());
    }

    @GetMapping("/plant/{id}")
    public ResponseEntity<List<SensorDTO>> getSensorById(@PathVariable Long id){
        return ResponseEntity.ok(sensorService.getSensorsByPlantId(id)); //para las cards de abajo del dashboard
    }


    @PutMapping("/{id}")
    public ResponseEntity<updateSensorDTO > updateSensor(@PathVariable Long id, @RequestBody updateSensorDTO sensor ){
        return ResponseEntity.ok(sensorService.editSensor(id,sensor ));
    }

}
