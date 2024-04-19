package ch.trapp.niklas.motorify.dto;

import ch.trapp.niklas.motorify.type.BikeType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BikeDto {

    private long manufacturer_id;

    private String model;

    private BikeType bikeType;

    private int year;

    private int horsepower;

    private int weight;

    private int mileage;

    private String username;

    public BikeDto() {}

}
