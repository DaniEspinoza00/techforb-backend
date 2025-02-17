package com.challenge.techforb.Plants.Sensor;

import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorCardDTO;
import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorDTO;
import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorListDTO;
import com.challenge.techforb.Plants.Sensor.SensorDTOs.updateSensorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public List<SensorListDTO> getAllSensors(){
        return sensorRepository.findAll().stream()
                .map(this::convertToSensorListDTO)
                .collect(Collectors.toList());
    }

    public updateSensorDTO  editSensor(Long id, updateSensorDTO sensor){
        SensorEntity foundSensor = sensorRepository.findById(id).orElseThrow(()-> new RuntimeException("sensor inexistente"));
        foundSensor.setSensorName(sensor.getSensorName());
        foundSensor.setOkLectures(sensor.getOkLectures());
        foundSensor.setMediaRangeAlert(sensor.getMediaRangeAlert());
        foundSensor.setRedAlert(sensor.getRedAlert());

        SensorEntity updatedSensor = sensorRepository.save(foundSensor);

        return updateSensorDTO.builder()
                .sensorName(updatedSensor.getSensorName())
                .okLectures(updatedSensor.getOkLectures())
                .mediaRangeAlert(updatedSensor.getMediaRangeAlert())
                .redAlert(updatedSensor.getRedAlert())
                .build();
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

    private SensorListDTO convertToSensorListDTO (SensorEntity sensor){
        return SensorListDTO.builder()
                .idSensor(sensor.getId_sensor())
                .sensorName(sensor.getSensorName())
                .okLectures(sensor.getOkLectures())
                .mediaRangeAlert(sensor.getMediaRangeAlert())
                .redAlert(sensor.getRedAlert())
                .idPlant(sensor.getPlant()!=null?sensor.getPlant().getId():null)
                .build();
    }
}
