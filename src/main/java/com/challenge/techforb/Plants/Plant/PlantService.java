package com.challenge.techforb.Plants.Plant;

import com.challenge.techforb.Plants.Plant.PlantDTOs.PlantDTO;
import com.challenge.techforb.Plants.Sensor.SensorDTOs.SensorDTO;
import com.challenge.techforb.Plants.Sensor.SensorEntity;
import com.challenge.techforb.Plants.Sensor.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantService {
    private final PlantRepository plantRepository;

    public List<PlantDashboard> getAllPlants(){
        return plantRepository.findAll().stream()
                .map(this::convertToPlantDashboard)
                .collect(Collectors.toList());
    }

    public PlantEntity savePlant (PlantEntity plant){
        return plantRepository.save(plant);
    }
    public PlantEntity editPlant(Long id, PlantEntity plant){
        PlantEntity editedPlant = plantRepository.findById(id).orElseThrow(()-> new RuntimeException("Planta inexistente"));
        editedPlant.setCountry(plant.getCountry());
        editedPlant.setName(plant.getName());
        return plantRepository.save(editedPlant);
    }

    public void deletePlant(Long id){
        plantRepository.deleteById(id);
    }

    public PlantDTO addSensorToPlant(Long id, SensorEntity sensor) {
        PlantEntity plant = plantRepository.findById(id).orElseThrow(() -> new RuntimeException("Planta inexistente"));

        Optional<SensorEntity> findSensor = plant.getSensors().stream() //si no funciona SensorEntiyt, cambiar a Optional
                .filter(s -> s.getSensorName().equals(sensor.getSensorName()))
                .findFirst();

        if (findSensor.isPresent()) {
            SensorEntity existingSensor = findSensor.get();
            existingSensor.setOkLectures(sensor.getOkLectures());
            existingSensor.setMediaRangeAlert(sensor.getMediaRangeAlert());
            existingSensor.setRedAlert(sensor.getRedAlert());
        } else {
            plant.addSensorToPlant(sensor);
        }

        plantRepository.save(plant);
        return convertToPlantDTO(plant);
    }

    ///funciones privadas

    private PlantDTO convertToPlantDTO(PlantEntity plantEntity){
        return PlantDTO.builder()
                .id(plantEntity.getId())
                .country(plantEntity.getCountry())
                .name(plantEntity.getName())
                .sensors(convertListSensorToDTOs(plantEntity.getSensors()))
                .build();
    }

    private List<SensorDTO> convertListSensorToDTOs(List<SensorEntity> sensors){
        return sensors.stream()
                .map(this::convertToSensorDTO)
                .collect(Collectors.toList());
    }

    private SensorDTO convertToSensorDTO (SensorEntity sensor){
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setIdSensor(sensor.getId_sensor());
        sensorDTO.setSensorName(sensor.getSensorName());
        sensorDTO.setOkLectures(sensor.getOkLectures());
        sensorDTO.setMediaRangeAlert(sensor.getMediaRangeAlert());
        sensorDTO.setRedAlert(sensor.getRedAlert());

        return sensorDTO;
    }

    private PlantDashboard convertToPlantDashboard (PlantEntity plantEntity){
        int okLectures=sumOkLectures(plantEntity.getSensors());
        int mediaAlert=sumMediaAlert(plantEntity.getSensors());
        int redAlert=sumRedAlert(plantEntity.getSensors());

        return PlantDashboard.builder()
                .id(plantEntity.getId())
                .country(plantEntity.getCountry())
                .name(plantEntity.getName())
                .sumOk(okLectures)
                .sumMediaAlert(mediaAlert)
                .sumRedAlert(redAlert)
                .build();
    }

    private int sumOkLectures (List<SensorEntity> sensorList){
        return sensorList.stream().mapToInt(SensorEntity::getOkLectures).sum();
    }
    private int sumMediaAlert (List<SensorEntity> sensorList){
        return sensorList.stream().mapToInt(SensorEntity::getMediaRangeAlert).sum();
    }
    private int sumRedAlert (List<SensorEntity> sensorList){
        return sensorList.stream().mapToInt(SensorEntity::getRedAlert).sum();
    }


}
