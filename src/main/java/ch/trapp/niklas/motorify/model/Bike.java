package ch.trapp.niklas.motorify.model;

import ch.trapp.niklas.motorify.type.BikeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Validated
public class Bike {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Size(max = 24)
    @NotEmpty
    private String model;

    @NotNull
    private BikeType bikeType;

    @NotNull
    private int year;

    @NotNull
    private int horsepower;

    @NotNull
    private int weight;

    @NotNull
    private int mileage;

    @NotEmpty
    private String username;

    @NotNull
    private LocalDateTime createdAt;

    public Bike() {}

    public Bike(Manufacturer manufacturer, String model, int year, int horsepower, int weight, int mileage, BikeType bikeType, LocalDateTime createdAt, String username) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.horsepower = horsepower;
        this.weight = weight;
        this.mileage = mileage;
        this.username = username;
        this.bikeType = bikeType;
        this.createdAt = createdAt;
    }

}
