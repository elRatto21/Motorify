package ch.trapp.niklas.motorify.expense;

import ch.trapp.niklas.motorify.bike.Bike;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @NotNull
    private double amount;

    @NotNull
    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bike_id")
    private Bike bike;

    @NotNull
    private ExpenseType expenseType;

}
