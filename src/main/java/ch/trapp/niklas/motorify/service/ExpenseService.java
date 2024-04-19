package ch.trapp.niklas.motorify.service;

import ch.trapp.niklas.motorify.model.Expense;
import ch.trapp.niklas.motorify.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Expense> findAllByBikeId(Long bikeId) {
        return this.expenseRepository.findAllByBikeId(bikeId);
    }

    public Expense findById(Long expenseId) {
        return this.expenseRepository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException());
    }

    public Expense update(Expense expense, Long id) {
        return expenseRepository.findById(id)
                .map(expenseOrig -> {
                    expenseOrig.setExpenseType(expense.getExpenseType());
                    expenseOrig.setName(expense.getName());
                    expenseOrig.setDate(expense.getDate());
                    expenseOrig.setAmount(expense.getAmount());
                    expenseOrig.setBikeId(expense.getBikeId());
                    return expenseRepository.save(expenseOrig);
                })
                .orElseGet(() -> expenseRepository.save(expense));
    }

    public void deleteById(Long id) {
        this.expenseRepository.deleteById(id);
    }

}
