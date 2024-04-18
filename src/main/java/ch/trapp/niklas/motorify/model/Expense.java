package ch.trapp.niklas.motorify.model;

import ch.trapp.niklas.motorify.type.ExpenseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long bikeId;

    @NotEmpty
    private String name;

    @NotNull
    private double amount;

    @NotNull
    private LocalDate date;

    @NotNull
    private ExpenseType expenseType;

    public Expense() {}

    public Expense(String name, double amount, LocalDate date, ExpenseType expenseType) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.expenseType = expenseType;
    }

}
