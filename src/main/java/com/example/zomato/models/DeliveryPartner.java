package com.example.zomato.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private long mobNo;

    private String vehicleNo;

    @Min(1)
    @Max(5)
    private double rating;

    @OneToMany(mappedBy = "deliveryPartner")
    @JsonIgnore
    private List<FoodOrder> foodOrders;
}
