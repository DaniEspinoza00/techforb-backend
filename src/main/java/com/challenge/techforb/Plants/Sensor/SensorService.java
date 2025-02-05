package com.challenge.techforb.Plants.Sensor;

import com.challenge.techforb.Plants.Plant.PlantEntity;
import com.challenge.techforb.Plants.Plant.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private SensorRepository sensorRepository;

    public List<SensorEntity> getAllSensors(){
        return sensorRepository.findAll();
    }
    public Optional<SensorEntity> getSensorById(Long id){
        return sensorRepository.findById(id);
    }
    public SensorEntity saveSensor (SensorEntity plant){
        return sensorRepository.save(plant);
    }
    public SensorEntity editSensor(Long id, SensorEntity sensor){
        SensorEntity editedSensor = sensorRepository.findById(id).orElseThrow(()-> new RuntimeException("sensor inexistente"));
        editedSensor.setSensorName(sensor.getSensorName());
        editedSensor.setOkLectures(sensor.getOkLectures());
        editedSensor.setMediaRangeAlert(sensor.getMediaRangeAlert());
        sensor.setRedAlert(sensor.getRedAlert());

        return sensorRepository.save(editedSensor);
    }

    public void deleteSensor(Long id){
        sensorRepository.deleteById(id);
    }
}
