package ch.trapp.niklas.motorify.bike;

import ch.trapp.niklas.motorify.manufacturer.Manufacturer;
import ch.trapp.niklas.motorify.manufacturer.ManufacturerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BikeService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private BikeRepository bikeRepo;

    public Bike save(BikeDto bikeDto) {
        Manufacturer manufacturer = manufacturerRepository.findById(bikeDto.getManufacturer_id()).orElseThrow(() -> new EntityNotFoundException());
        Bike bike = new Bike(
                manufacturer,
                bikeDto.getModel(),
                bikeDto.getYear(),
                bikeDto.getHorsepower(),
                bikeDto.getWeight(),
                bikeDto.getMileage(),
                BikeType.SUPERMOTO,
                LocalDateTime.now(),
                "test"
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

    public Bike update(Bike bike) {
        return null;
    }

    public void deleteById(long bikeId) {
        this.bikeRepo.deleteById(bikeId);
    }
}
