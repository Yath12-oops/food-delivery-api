package com.example.zomato.convertors;

import com.example.zomato.dto.response.FoodOrderResponse;
import com.example.zomato.models.FoodOrder;

public class FoodOrderConvertor {

    public static FoodOrderResponse foodOrderToFoodOrderResponse(FoodOrder foodOrder){
        return FoodOrderResponse.builder()
                .totalValue(foodOrder.getTotalValue())
                .orderStatus(foodOrder.getOrderStatus())
                .deliveryPartnerResponse(DeliveryPartnerConvertor.deliveryPartnerToDeliveryPartnerResponse(foodOrder.getDeliveryPartner()))
                .build();
    }
}
