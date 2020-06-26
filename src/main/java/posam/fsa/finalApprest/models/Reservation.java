package posam.fsa.finalApprest.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id",unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "places_id", nullable = false)
    private Places places_id;

    @Column(name = "timeFrom")
    private Date timeFrom;

    @Column(name = "timeTo")
    private Date timeTo;

    @Column(name = "amount")
    private Long amount;

    public Reservation() {
    }

    public Reservation(Long id, User user_id, Date timeFrom, Date timeTo, Long amount){
        this.id = id;
        this.user_id = user_id;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.amount = amount;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
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

    public Date getTimeFrom(){
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom){
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo(){
        return timeTo;
    }

    public void setTimeTo(Date timeTo){
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
                "id=" + id +
                ", user_id=" + user_id +
                ", places_id=" + places_id +
                ", timeFrom=" + timeFrom +
                ", timeTo=" + timeTo +
                ", amount=" + amount + "}";
    }
}
