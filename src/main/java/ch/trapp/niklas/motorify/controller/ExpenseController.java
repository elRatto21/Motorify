package ch.trapp.niklas.motorify.expense;

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
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<List<Expense>> getAllExpense() {
        return new ResponseEntity<>(this.expenseService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        return new ResponseEntity<>(this.expenseService.save(expense), HttpStatus.CREATED);
    }

    @GetMapping("/{bikeId}")
    @RolesAllowed({Roles.Admin, Roles.User})
    public ResponseEntity<List<Expense>> getAllExpenseByBike(@PathVariable long bikeId) {
        return new ResponseEntity<>(this.expenseService.findAllByBikeId(bikeId), HttpStatus.OK);
    }

}
