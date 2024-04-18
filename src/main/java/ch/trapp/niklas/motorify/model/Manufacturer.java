package ch.trapp.niklas.motorify.manufacturer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }

}
