package ch.trapp.niklas.motorify.controller;

import ch.trapp.niklas.motorify.service.ExpenseService;
import ch.trapp.niklas.motorify.model.Expense;
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
@RequestMapping("api/expense")
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping()
    @RolesAllowed({Roles.Admin})
    public ResponseEntity<List<Expense>> getAllExpense() {
        return new ResponseEntity<>(this.expenseService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        System.out.println(expense);
        return new ResponseEntity<>(this.expenseService.save(expense), HttpStatus.CREATED);
    }

    @GetMapping(params = {"bike"})
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<List<Expense>> getAllExpenseByBike(@RequestParam Long bike) {
        return new ResponseEntity<>(this.expenseService.findAllByBikeId(bike), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<Expense> getExpense(@PathVariable Long id) {
        return new ResponseEntity<>(this.expenseService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense, @PathVariable Long id) {
        return new ResponseEntity<>(this.expenseService.update(expense, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({Roles.Admin})
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable Long id) {
        this.expenseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
