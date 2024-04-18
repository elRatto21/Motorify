package ch.trapp.niklas.motorify.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> findAll() {
        return this.expenseRepository.findAll();
    }

    public Expense save(Expense expense) {
        return this.expenseRepository.save(expense);
    }

    public List<Expense> findAllByBikeId(long bikeId) {
        return this.expenseRepository.findAllByBikeId(bikeId);
    }

}
