package ch.trapp.niklas.motorify.bike;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepo;

    public Bike save(Bike bike) {
        return this.bikeRepo.save(bike);
    }

    public Bike findById(long id) {
        return this.bikeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Bike> findAll() {
        return this.bikeRepo.findAll();
    }

    public List<Bike> findAllByUser(String user) {
        return this.bikeRepo.findAllByUser(user);
    }

    public Bike update(Bike bike) {
        return null;
    }

    public void deleteById(long bikeId) {
        this.bikeRepo.deleteById(bikeId);
    }
}
