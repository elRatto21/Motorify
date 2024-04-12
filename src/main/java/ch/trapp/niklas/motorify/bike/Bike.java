package ch.trapp.niklas.motorify.bike;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bike {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long  id;

}
