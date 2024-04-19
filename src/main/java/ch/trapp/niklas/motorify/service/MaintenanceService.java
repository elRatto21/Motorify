package ch.trapp.niklas.motorify.service;

import ch.trapp.niklas.motorify.dto.MaintenanceDto;
import ch.trapp.niklas.motorify.model.Expense;
import ch.trapp.niklas.motorify.model.Maintenance;
import ch.trapp.niklas.motorify.repository.MaintenanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepo;

    @Autowired
    private ExpenseService expenseService;

    public List<Maintenance> findAll() {
        return this.maintenanceRepo.findAll();
    }

    public List<Maintenance> findAllByBike(Long bikeId) {
        return this.maintenanceRepo.findAllByBikeId(bikeId);
    }

    public Maintenance save(MaintenanceDto maintenanceDto) {
        Maintenance maintenance = new Maintenance(maintenanceDto.getBikeId(), maintenanceDto.getName(), maintenanceDto.getMileage(), maintenanceDto.getDate());
        List<Expense> expenses = new ArrayList<>();
        for (Long expenseId : maintenanceDto.getExpenseIds()) {
            expenses.add(this.expenseService.findById(expenseId));
        }
        maintenance.setExpenses(expenses);
        return this.maintenanceRepo.save(maintenance);
    }

    public Maintenance findById(Long id) {
        return this.maintenanceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Maintenance update(Maintenance maintenance, Long id) {
        return maintenanceRepo.findById(id)
                .map(maintenanceOrig -> {
                    maintenanceOrig.setExpenses(maintenance.getExpenses());
                    maintenanceOrig.setMileage(maintenance.getMileage());
                    maintenanceOrig.setName(maintenance.getName());
                    maintenanceOrig.setDate(maintenance.getDate());
                    maintenanceOrig.setBikeId(maintenance.getBikeId());
                    return maintenanceRepo.save(maintenanceOrig);
                })
                .orElseGet(() -> maintenanceRepo.save(maintenance));
    }

    public void deleteById(Long id) {
        this.maintenanceRepo.deleteById(id);
    }

}
