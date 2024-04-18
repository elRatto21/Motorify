package ch.trapp.niklas.motorify.manufacturer;

import ch.trapp.niklas.motorify.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/manufacturer")
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping()
    @RolesAllowed({Roles.User, Roles.Admin})
    public ResponseEntity<List<Manufacturer>> getAllManufacturer() {
        return new ResponseEntity<>(this.manufacturerService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(this.manufacturerService.save(manufacturer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<Manufacturer> getManufacturerByName(@PathVariable long id) {
        return new ResponseEntity<>(this.manufacturerService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable long id, @RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(this.manufacturerService.update(manufacturer, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable long id) {
        this.manufacturerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
