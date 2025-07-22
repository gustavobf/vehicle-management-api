package br.com.batista.dto.ad.request;

import br.com.batista.dto.ad.base.*;

public class CreateAdRequest implements AdBase {

    private Long carId;
    private String description;
    private double price;
    private Long userId;

    public CreateAdRequest () {
    }

    @Override
    public Long getCarId () {
        return carId;
    }

    public void setCarId (Long carId) {
        this.carId = carId;
    }

    @Override
    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    @Override
    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    @Override
    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }
}
