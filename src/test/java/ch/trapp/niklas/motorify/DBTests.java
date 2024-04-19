package ch.trapp.niklas.motorify;

import ch.trapp.niklas.motorify.model.Manufacturer;
import ch.trapp.niklas.motorify.repository.ManufacturerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Test
    void insertManufacturer() {
        Manufacturer manufacturerKtm = this.manufacturerRepository.save(new Manufacturer("KTM", "Austria"));
        Assertions.assertNotNull(manufacturerKtm.getId());
    }

    @Test
    void getManufacturer() {
        Manufacturer savedManufacturer = this.manufacturerRepository.save(new Manufacturer("Yamaha", "Austria"));
        Manufacturer manufacturerFromDb = this.manufacturerRepository.findById(savedManufacturer.getId()).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(savedManufacturer.getName(), manufacturerFromDb.getName());
    }

    @Test
    void updateManufacturer() {
        Manufacturer manufacturer = this.manufacturerRepository.save(new Manufacturer("Honda", "Japan"));
        manufacturer.setName("Kawasaki");
        this.manufacturerRepository.save(manufacturer);
        Assertions.assertEquals(
                "Kawasaki",
                manufacturerRepository
                        .findById(manufacturer.getId())
                        .orElseThrow(() -> new EntityNotFoundException())
                        .getName()
        );
    }

    @Test
    void deleteManufacturer() {
        Manufacturer manufacturer = this.manufacturerRepository.save(new Manufacturer("toBeDeleted", "Germany"));
        this.manufacturerRepository.deleteById(manufacturer.getId());
        Assertions.assertFalse(manufacturerRepository.findById(manufacturer.getId()).isPresent());
    }

}
