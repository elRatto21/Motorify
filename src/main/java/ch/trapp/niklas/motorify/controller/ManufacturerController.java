package ch.trapp.niklas.motorify.controller;

import ch.trapp.niklas.motorify.service.ManufacturerService;
import ch.trapp.niklas.motorify.model.Manufacturer;
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
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable Long id) {
        return new ResponseEntity<>(this.manufacturerService.findById(id), HttpStatus.OK);
    }

    @GetMapping(params = {"name"})
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<List<Manufacturer>> getAllManufacturerByName(@RequestParam String name) {
        return new ResponseEntity<>(this.manufacturerService.findAllByName(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable Long id, @RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(this.manufacturerService.update(manufacturer, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable Long id) {
        this.manufacturerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
