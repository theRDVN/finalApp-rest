package posam.fsa.finalApprest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import posam.fsa.finalApprest.models.Places;

import java.util.List;
import java.util.Optional;

public interface PlacesRepository extends JpaRepository<Places, Long> {
    List<Places> findAll();
    Places save(Places created);
    void deleteByName(String name);
    Optional<Places> findByName(String name);
    Optional<Places> findById(Long id);
    List<Places> findByNameContaining(String name);
}
