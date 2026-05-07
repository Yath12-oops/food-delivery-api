package com.example.zomato.dto.response;

import com.example.zomato.models.DeliveryPartner;
import com.example.zomato.models.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodOrderResponse {

    private double totalValue;

    private OrderStatus orderStatus;

    private DeliveryPartnerResponse deliveryPartnerResponse;
}
