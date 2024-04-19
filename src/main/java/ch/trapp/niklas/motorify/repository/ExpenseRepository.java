package ch.trapp.niklas.motorify.repository;

import ch.trapp.niklas.motorify.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByBikeId(Long bikeId);

}
