package ch.trapp.niklas.motorify.repository;

import ch.trapp.niklas.motorify.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    List<Bike> findAllByUsername(String username);

    void deleteAllByManufacturer_Id(Long manufacturerId);
}
