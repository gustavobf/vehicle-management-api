package br.com.batista.dto.ad.request;

import br.com.batista.dto.ad.base.*;
import io.swagger.v3.oas.annotations.media.*;

public class UpdateAdRequest implements AdBase {

    @Schema(description = "Ad ID", example = "123", required = true)
    private Long id;

    private Long carId;
    private String description;
    private double price;
    private Long userId;

    @Schema(description = "Flag indicating if the ad is active", example = "true", required = true)
    private boolean active;

    public UpdateAdRequest () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
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

    public boolean isActive () {
        return active;
    }

    public void setActive (boolean active) {
        this.active = active;
    }
}
