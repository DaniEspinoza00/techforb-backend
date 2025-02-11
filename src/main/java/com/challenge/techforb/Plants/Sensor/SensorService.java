package com.challenge.techforb.Plants.Sensor;

import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorCardDTO;
import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    /*public List<SensorEntity> getAllSensors(){
        return sensorRepository.findAll();
    }
    public Optional<SensorEntity> getSensorById(Long id){
        return sensorRepository.findById(id);
    }
    public SensorEntity saveSensor (SensorEntity plant){
        return sensorRepository.save(plant);
    }*/

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


    public List<SensorDTO> getSensorsByPlantId(Long id){
        return sensorRepository.findByPlantId(id).stream()
                .map(this::convertToSensorDTO)
                .collect(Collectors.toList());
    }

    public SensorCardDTO getSensorCards(){
        List<SensorEntity> sensorList = sensorRepository.findAll();
        return convertToSensorCardDTO(sensorList);
    }


    private SensorDTO convertToSensorDTO (SensorEntity sensor){
        return SensorDTO.builder()
                .idSensor(sensor.getId_sensor())
                .sensorName(sensor.getSensorName())
                .okLectures(sensor.getOkLectures())
                .mediaRangeAlert(sensor.getMediaRangeAlert())
                .redAlert(sensor.getRedAlert())
                .build();
    }

    private SensorCardDTO convertToSensorCardDTO(List<SensorEntity> sensorList){
        int sumOkLectures = sensorList.stream().mapToInt(SensorEntity::getOkLectures).sum();
        int sumMediaAlert = sensorList.stream().mapToInt(SensorEntity::getMediaRangeAlert).sum();
        int sumRedAlert = sensorList.stream().mapToInt(SensorEntity::getRedAlert).sum();

        return SensorCardDTO.builder()
                .sumOkLectures(sumOkLectures)
                .sumMediaAlert(sumMediaAlert)
                .sumRedAlert(sumRedAlert)
                .disabledSensors(sumRedAlert)
                .build();
    }
}
