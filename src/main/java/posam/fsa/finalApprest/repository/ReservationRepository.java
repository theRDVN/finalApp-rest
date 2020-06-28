package posam.fsa.finalApprest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import posam.fsa.finalApprest.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
