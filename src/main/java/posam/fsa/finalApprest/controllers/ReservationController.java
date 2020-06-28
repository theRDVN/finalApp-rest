package posam.fsa.finalApprest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posam.fsa.finalApprest.exeption.ResourceNotFoundException;
import posam.fsa.finalApprest.models.Reservation;
import posam.fsa.finalApprest.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    /*@GetMapping(path= "/reservations")
    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }*/

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllTutorials(@RequestParam(required = false) Long id) {
        try {
            List<Reservation> reservations = new ArrayList<Reservation>();

            if (id == null)
                reservationRepository.findAll().forEach(reservations::add);

            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping(path = "/reservations/{id}")
    public Optional<Reservation> getReservationById(@PathVariable(value = "id") Long id){
        return reservationRepository.findById(id);
    }*/

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getTutorialById(@PathVariable("id") long id) {
        Optional<Reservation> tutorialData = reservationRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservationRepository.delete(reservation);
        return ResponseEntity.ok().build();
    }

}
