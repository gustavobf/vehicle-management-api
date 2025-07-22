package br.com.batista.entity;

import br.com.batista.dto.ad.base.*;
import br.com.batista.dto.ad.request.*;
import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private boolean active;

    public Ad () {
    }

    public static Ad create (AdBase adBase, Car car, User user) {
        if (adBase == null) {
            return null;
        }

        Ad ad = new Ad();
        ad.setActive(true);
        ad.setPrice(adBase.getPrice());
        ad.setDescription(adBase.getDescription());
        ad.setCar(car);
        ad.setUser(user);

        if (adBase instanceof UpdateAdRequest dto) {
            ad.setId(dto.getId());
        }

        return ad;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Car getCar () {
        return car;
    }

    public void setCar (Car car) {
        this.car = car;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt () {
        return createdAt;
    }

    public void setCreatedAt (LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public boolean isActive () {
        return active;
    }

    public void setActive (boolean active) {
        this.active = active;
    }
}
