package com.challenge.techforb.Plants.Plant;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {
    private PlantRepository plantRepository;

    public List<PlantEntity> getAllPlants(){
        return plantRepository.findAll();
    }
    public Optional<PlantEntity> getPlantById(Long id){
        return plantRepository.findById(id);
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
}
