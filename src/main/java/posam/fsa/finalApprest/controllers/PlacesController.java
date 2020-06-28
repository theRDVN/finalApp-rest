package posam.fsa.finalApprest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posam.fsa.finalApprest.exeption.PlacesExistsExeption;
import posam.fsa.finalApprest.exeption.ResourceNotFoundException;
import posam.fsa.finalApprest.models.Places;
import posam.fsa.finalApprest.repository.PlacesRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public final class PlacesController {

    private final PlacesRepository placesRepository;

    @Autowired
    public PlacesController(final PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    @GetMapping(path = "/places", produces = "application/json")
    public List<Places> getAllPlaces(){
        return placesRepository.findAll();
    }

    @PostMapping(path = "/places")
    public Places createPlaces(@Valid @RequestBody Places places){
        System.out.println("This is places" + places);
        Optional<Places> optionalPlaces = placesRepository.findByName(places.getName());
        optionalPlaces.ifPresent(returnedPlaces -> {throw new PlacesExistsExeption();});
        return placesRepository.save(places);
    }

    /**@GetMapping("/places/{name}")
    public Places getPlacesByName(@PathVariable(value = "name") String name) {
    return placesRepository.findByName(name)
    .orElseThrow(() -> new ResourceNotFoundException("Merchant", "name", name));
    }**/

    @GetMapping(path="/places/{name}")
    public List<Places> getAllPlacesContainingName(@PathVariable(value = "name") String name){
        return placesRepository.findByNameContaining(name);
    }


    @PutMapping("/places")
    public Places updatePlaces(@Valid @RequestBody Places placesDetails) {
        Places places = placesRepository.findById(placesDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Places", "id", placesDetails.getId()));
        places.setName(placesDetails.getName());
        return placesRepository.save(places);
    }

    @DeleteMapping("/places/{id}")
    public ResponseEntity<?> deletePlaces(@PathVariable(value = "id") Long id) {
        Places places = placesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Places", "id", id));
        placesRepository.delete(places);
        return ResponseEntity.ok().build();
    }

}
