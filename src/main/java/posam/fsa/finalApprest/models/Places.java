package posam.fsa.finalApprest.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "places")
public class Places {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "places_id", unique = true, nullable = false)
    private Long id;

    @Column(name="places_name")
    private String name;

    @Column(name="places_maxAmount")
    private Long maxAmount;

    @OneToMany(mappedBy = "places_id")
    private Set<Reservation> reservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public String toString() {
        return "Places{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxAmount='" + maxAmount + '\'' +
                '}';
    }
}
