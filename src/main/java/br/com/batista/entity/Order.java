package br.com.batista.entity;

import br.com.batista.dto.order.request.*;
import br.com.batista.utils.*;
import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "app_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    private LocalDateTime orderDate;
    private String status;
    private Double totalPrice;

    public Order () {
    }

    public static Order create (Ad ad, User buyer) {
        Order order = new Order();
        order.setAd(ad);
        order.setBuyer(buyer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalPrice(PriceUtils.applyFee(ad.getPrice()));
        return order;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public User getBuyer () {
        return buyer;
    }

    public void setBuyer (User buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getOrderDate () {
        return orderDate;
    }

    public void setOrderDate (LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public Double getTotalPrice () {
        return totalPrice;
    }

    public void setTotalPrice (Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Ad getAd () {
        return ad;
    }

    public void setAd (Ad ad) {
        this.ad = ad;
    }
}