package posam.fsa.finalApprest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posam.fsa.finalApprest.exeption.ResourceNotFoundException;
import posam.fsa.finalApprest.models.Reservation;
import posam.fsa.finalApprest.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping(path= "/reservations")
    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    @GetMapping(path = "/reservations/{id}")
    public Optional<Reservation> getReservationById(@PathVariable(value = "id") Long id){
        return reservationRepository.findById(id);
    }

    /*@PostMapping(path="/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        System.out.print("This is reservation" + reservation);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservation.getId());
        optionalReservation.ifPresent(returnedMerchant -> {throw new ReservationExistsException();});
        return reservationRepository.save(reservation);
    }*/

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        try {
            Reservation _reservation = reservationRepository
                    .save(new Reservation(reservation.getUserId(), reservation.getPlacesId(), reservation.getTimeFrom(), reservation.getTimeTo(), reservation.getAmount()));
            return new ResponseEntity<>(_reservation, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
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
