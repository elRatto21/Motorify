package ch.trapp.niklas.motorify.manufacturer;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    public Manufacturer findById(long id) {
        return this.manufacturerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Manufacturer save(Manufacturer manufacturer) {
        return this.manufacturerRepository.save(manufacturer);
    }

    public Manufacturer update(Manufacturer manufacturer, long id) {
        return manufacturerRepository.findById(id).map(manufacturerOrig -> {
            manufacturerOrig.setName(manufacturer.getName());
            return manufacturerRepository.save(manufacturerOrig);
        }).orElseGet(() -> manufacturerRepository.save(manufacturer));
    }

    public void deleteById(long id) {
        this.manufacturerRepository.deleteById(id);
    }

}
