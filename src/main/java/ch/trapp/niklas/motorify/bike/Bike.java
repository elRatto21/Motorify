package ch.trapp.niklas.motorify.bike;

import ch.trapp.niklas.motorify.manufacturer.Manufacturer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

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
    private int year;

    @NotNull
    private int horsepower;

    @NotNull
    private int weight;

    @NotNull
    private int mileage;

    @NotEmpty
    private String user;

    public Bike() {}

    public Bike(Manufacturer manufacturer, String model, int year, int horsepower, int weight, int mileage, String user) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.horsepower = horsepower;
        this.weight = weight;
        this.mileage = mileage;
        this.user = user;
    }

}
