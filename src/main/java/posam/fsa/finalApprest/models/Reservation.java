package posam.fsa.finalApprest.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id",unique = true, nullable = false)
    private Long reservation_id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "places_id", nullable = false)
    private Places places_id;

    @Column(name = "timeFrom")
    private LocalDateTime timeFrom;

    @Column(name = "timeTo")
    private LocalDateTime timeTo;

    @Column(name = "amount")
    private Long amount;

    public Reservation() {
    }

    public Reservation(User user_id,Places places_id, LocalDateTime timeFrom, LocalDateTime timeTo, Long amount){
        this.user_id = user_id;
        this.places_id = places_id;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.amount = amount;
    }

    public Long getId(){
        return reservation_id;
    }

    public void setId(Long reservation_id){
        this.reservation_id = reservation_id;
    }

    public User getUserId(){
        return user_id;
    }

    public void setId(User user_id){
        this.user_id = user_id;
    }

    public Places getPlacesId(){
        return places_id;
    }

    public void setId(Places places_id){
      this.places_id = places_id;
    }

    public LocalDateTime getTimeFrom(){
        return timeFrom;
    }

    public void setTimeFrom(LocalDateTime timeFrom){
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeTo(){
        return timeTo;
    }

    public void setTimeTo(LocalDateTime timeTo){
        this.timeTo = timeTo;
    }

    public Long getAmount(){
        return amount;
    }

    public void setAmount(Long amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + reservation_id +
                ", user_id=" + user_id +
                ", places_id=" + places_id +
                ", timeFrom=" + timeFrom +
                ", timeTo=" + timeTo +
                ", amount=" + amount + "}";
    }
}
