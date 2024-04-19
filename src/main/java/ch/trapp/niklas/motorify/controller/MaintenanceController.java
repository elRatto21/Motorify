package ch.trapp.niklas.motorify.controller;

import ch.trapp.niklas.motorify.dto.MaintenanceDto;
import ch.trapp.niklas.motorify.model.Maintenance;
import ch.trapp.niklas.motorify.security.Roles;
import ch.trapp.niklas.motorify.service.MaintenanceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/maintenance")
@Validated
@SecurityRequirement(name = "bearerAuth")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping()
    @RolesAllowed({Roles.Admin})
    public ResponseEntity<List<Maintenance>> getAllMaintenance() {
        return new ResponseEntity<>(this.maintenanceService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody MaintenanceDto maintenanceDto) {
        return new ResponseEntity<>(this.maintenanceService.save(maintenanceDto), HttpStatus.CREATED);
    }

    @GetMapping(params = {"bike"})
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<List<Maintenance>> getAllMaintenanceByBike(@RequestParam Long bike) {
        return new ResponseEntity<>(this.maintenanceService.findAllByBike(bike), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<Maintenance> getMaintenance(@PathVariable Long id) {
        return new ResponseEntity<>(this.maintenanceService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<Maintenance> updateMaintenance(@RequestBody Maintenance maintenance, @PathVariable Long id) {
        return new ResponseEntity<>(this.maintenanceService.update(maintenance, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({Roles.Admin})
    public ResponseEntity<HttpStatus> deleteMaintenance(@PathVariable Long id) {
        this.maintenanceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
