package ch.trapp.niklas.motorify;

import ch.trapp.niklas.motorify.model.Manufacturer;
import ch.trapp.niklas.motorify.repository.ManufacturerRepository;
import ch.trapp.niklas.motorify.service.ManufacturerService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class ManufacturerServiceTests {

    private ManufacturerService manufacturerService;
    private final ManufacturerRepository manufacturerRepositoryMock = mock(ManufacturerRepository.class);

    private final Manufacturer manufacturerMock = mock(Manufacturer.class);

    @BeforeEach
    void setUp() {
        manufacturerService = new ManufacturerService(manufacturerRepositoryMock);
    }

    @Test
    void createManufacturer() {
        when(manufacturerRepositoryMock.save(manufacturerMock)).thenReturn(manufacturerMock);
        manufacturerService.save(manufacturerMock);
        verify(manufacturerRepositoryMock, times(1)).save(any());
    }

    @Test
    void findManufacturer() {
        when(manufacturerRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(manufacturerMock));
        Manufacturer manufacturer = manufacturerService.findById(any());
        verify(manufacturerRepositoryMock, times(1)).findById(any());
    }

    @Test
    void deleteManufacturer() {
        manufacturerService.deleteById(any());
        verify(manufacturerRepositoryMock, times(1)).deleteById(any());
    }

    @Test
    void updateManufacturer() {
        Manufacturer manufacturerUpdated = mock(Manufacturer.class);

        when(manufacturerRepositoryMock.save(manufacturerMock)).thenReturn(manufacturerMock);
        manufacturerService.save(manufacturerMock);

        manufacturerUpdated.setId(manufacturerMock.getId());
        manufacturerService.save(manufacturerUpdated);

        verify(manufacturerRepositoryMock, times(2)).save(any());
    }

}
