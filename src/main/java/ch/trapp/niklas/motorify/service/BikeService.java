package ch.trapp.niklas.motorify.service;

import ch.trapp.niklas.motorify.type.BikeType;
import ch.trapp.niklas.motorify.dto.BikeDto;
import ch.trapp.niklas.motorify.model.Bike;
import ch.trapp.niklas.motorify.model.Manufacturer;
import ch.trapp.niklas.motorify.repository.BikeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BikeService {

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private BikeRepository bikeRepo;

    public Bike save(BikeDto bikeDto) {
        Manufacturer manufacturer = manufacturerService.findById(bikeDto.getManufacturer_id());
        Bike bike = new Bike(
                manufacturer,
                bikeDto.getModel(),
                bikeDto.getYear(),
                bikeDto.getHorsepower(),
                bikeDto.getWeight(),
                bikeDto.getMileage(),
                BikeType.SUPERMOTO,
                LocalDateTime.now(),
                bikeDto.getUsername()
        );

        return this.bikeRepo.save(bike);
    }

    public Bike findById(long id) {
        return this.bikeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Bike> findAll() {
        return this.bikeRepo.findAll();
    }

    public List<Bike> findAllByUsername(String username) {
        return this.bikeRepo.findAllByUsername(username);
    }

    public Bike update(BikeDto bike, Long id) {
        return bikeRepo.findById(id)
                .map(bikeOrig -> {
                    bikeOrig.setBikeType(bike.getBikeType());
                    bikeOrig.setMileage(bike.getMileage());
                    bikeOrig.setHorsepower(bike.getHorsepower());
                    bikeOrig.setManufacturer(manufacturerService.findById(bike.getManufacturer_id()));
                    bikeOrig.setWeight(bike.getWeight());
                    bikeOrig.setModel(bike.getModel());
                    bikeOrig.setYear(bike.getYear());
                    bikeOrig.setCreatedAt(LocalDateTime.now());
                    bikeOrig.setUsername(bike.getUsername());
                    return bikeRepo.save(bikeOrig);
                })
                .orElseGet(() -> save(bike));
    }

    public void deleteById(long bikeId) {
        this.bikeRepo.deleteById(bikeId);
    }

}
