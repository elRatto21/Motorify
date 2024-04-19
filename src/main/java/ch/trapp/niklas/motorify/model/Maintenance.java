package ch.trapp.niklas.motorify.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bikeId;

    private String name;

    private int mileage;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "expenses")
    private List<Expense> expenses;

    public Maintenance() {}

    public Maintenance(Long bikeId, String name, int mileage, LocalDate date) {
        this.bikeId = bikeId;
        this.name = name;
        this.mileage = mileage;
        this.date = date;
    }

}
