package co.simplon.spotmebuisness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.spotmebuisness.entities.Spot;

@Repository // Optional
public interface SpotRepository extends JpaRepository<Spot, Long> {

}
