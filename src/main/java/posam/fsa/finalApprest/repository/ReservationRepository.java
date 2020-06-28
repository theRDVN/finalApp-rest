package posam.fsa.finalApprest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import posam.fsa.finalApprest.models.Reservation;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAll();
    Reservation save(Reservation created);
    Optional<Reservation> findById(Long id);
}
