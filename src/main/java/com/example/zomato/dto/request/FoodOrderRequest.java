package com.example.zomato.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodOrderRequest {

    private int customerId;

    private List<Integer> foodItemIds;
}
