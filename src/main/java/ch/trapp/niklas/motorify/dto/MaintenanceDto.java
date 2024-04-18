package ch.trapp.niklas.motorify.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MaintenanceDto {

    private Long bikeId;

    private String name;

    private List<Long> expenseIds;

    private LocalDate date;

    private int mileage;

}
