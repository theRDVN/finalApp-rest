package posam.fsa.finalApprest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posam.fsa.finalApprest.exeption.ReservationExistsException;
import posam.fsa.finalApprest.exeption.ResourceNotFoundException;
import posam.fsa.finalApprest.models.Reservation;
import posam.fsa.finalApprest.repository.ReservationRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ReservationController {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(final ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @GetMapping(path= "/reservations", produces = "application/jason")
    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    @PostMapping(path="/merchants")
    public Reservation createReservation(@Valid @RequestBody Reservation reservation) {
        System.out.print("This is reservation" + reservation);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservation.getId());
        optionalReservation.ifPresent(returnedMerchant -> {throw new ReservationExistsException();});
        return reservationRepository.save(reservation);
    }

   /* @GetMapping(path="/reservations/{user_id}")
    public List<Reservation> getAllReservationContainingUser(@PathVariable(value = "user_id") Long user_id){
        return reservationRepository.findByUser_id(user_id);
    }

    @GetMapping(path="/reservations/{places_id}")
    public List<Reservation> getAllReservationContainingPlace(@PathVariable(value = "places_id") Long place_id){
        return reservationRepository.findByPlaces_id(place_id);
    }*/

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservationRepository.delete(reservation);
        return ResponseEntity.ok().build();
    }

}
