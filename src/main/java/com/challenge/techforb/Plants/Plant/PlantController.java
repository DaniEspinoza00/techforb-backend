package com.challenge.techforb.Plants.Plant;

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
    public List<PlantEntity> getAllPlants(){
        return plantService.getAllPlants();
    }

    @GetMapping("/{id}")
    public Optional<PlantEntity> getPlantById(@PathVariable Long id){
        return  plantService.getPlantById(id);
    }

    @PostMapping
    public PlantEntity createPlant(@RequestBody PlantEntity plantEntity){
        return plantService.savePlant(plantEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantEntity>updatePlant(@PathVariable Long id, @RequestBody PlantEntity plantEntity){
        return ResponseEntity.ok(plantService.editPlant(id,plantEntity));
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id){
        plantService.deletePlant(id);
    }
}
