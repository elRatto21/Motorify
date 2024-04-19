package ch.trapp.niklas.motorify.service;

import ch.trapp.niklas.motorify.model.Manufacturer;
import ch.trapp.niklas.motorify.repository.BikeRepository;
import ch.trapp.niklas.motorify.repository.ManufacturerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepo;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepo = manufacturerRepository;
    }

    public ManufacturerService() {}

    public List<Manufacturer> findAll() {
        return this.manufacturerRepo.findAll();
    }

    public Manufacturer findById(Long id) {
        return this.manufacturerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Manufacturer> findAllByName(String name) {
        return this.manufacturerRepo.findByNameContainingIgnoreCase(name);
    }

    public Manufacturer save(Manufacturer manufacturer) {
        return this.manufacturerRepo.save(manufacturer);
    }

    public Manufacturer update(Manufacturer manufacturer, Long id) {
        return manufacturerRepo.findById(id).map(manufacturerOrig -> {
            manufacturerOrig.setName(manufacturer.getName());
            manufacturerOrig.setCountry(manufacturer.getCountry());
            return manufacturerRepo.save(manufacturerOrig);
        }).orElseGet(() -> manufacturerRepo.save(manufacturer));
    }

    public void deleteById(Long id) {
        this.manufacturerRepo.deleteById(id);
    }

}
