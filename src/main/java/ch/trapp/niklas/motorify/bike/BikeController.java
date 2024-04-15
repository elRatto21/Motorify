package ch.trapp.niklas.motorify.bike;

import ch.trapp.niklas.motorify.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/bike")
@SecurityRequirement(name = "bearerAuth")
@Validated
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @PostMapping()
    @RolesAllowed({Roles.User, Roles.Admin})
    public ResponseEntity<Bike> postBike(@RequestBody Bike bike) {
        return new ResponseEntity<>(this.bikeService.save(bike), HttpStatus.CREATED);
    }

    @GetMapping()
    @RolesAllowed({Roles.Admin})
    public ResponseEntity<List<Bike>> getAllBike() {
        return new ResponseEntity<>(this.bikeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{user}")
    @RolesAllowed({Roles.User, Roles.Admin})
    public ResponseEntity<List<Bike>> getAllBikeByUser(@PathVariable String user) {
        return new ResponseEntity<>(this.bikeService.findAllByUser(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({Roles.User, Roles.Admin})
    public ResponseEntity<Bike> getBike(@PathVariable long id) {
        return new ResponseEntity<>(this.bikeService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @RolesAllowed({Roles.User, Roles.Admin})
    public ResponseEntity<Bike> updateBike(@PathVariable long id, @RequestBody Bike bike) {
        return new ResponseEntity<>(this.bikeService.update(bike), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({Roles.User, Roles.Admin})
    public ResponseEntity<HttpStatus> deleteBike(@PathVariable long id) {
        this.bikeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
