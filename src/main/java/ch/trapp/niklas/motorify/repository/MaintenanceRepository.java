package ch.trapp.niklas.motorify.repository;

import ch.trapp.niklas.motorify.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findAllByBikeId(Long bikeId);

}
