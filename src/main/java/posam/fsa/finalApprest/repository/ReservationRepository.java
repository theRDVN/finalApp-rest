package posam.fsa.finalApprest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import posam.fsa.finalApprest.models.Reservation;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAll();

    Reservation save(Reservation created);

    //List<Reservation> findByPlaces_id(Long places_id);

    //List<Reservation> findByUser_id(Long user_id);

    Optional<Reservation> findById(Long id);

   // Optional<Reservation> findByTime(Date timeFrom);
}
