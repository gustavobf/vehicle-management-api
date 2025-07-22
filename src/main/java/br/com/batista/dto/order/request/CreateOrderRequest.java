package br.com.batista.dto.order.request;

import br.com.batista.dto.order.base.*;

public class CreateOrderRequest implements OrderBase {

    private Long adId;

    public CreateOrderRequest () {
    }

    @Override
    public Long getAdId () {
        return adId;
    }

    public void setAdId (Long adId) {
        this.adId = adId;
    }

}
