package com.example.zomato.service;

import com.example.zomato.convertors.FoodItemConvertor;
import com.example.zomato.convertors.FoodOrderConvertor;
import com.example.zomato.dto.request.FoodItemRequest;
import com.example.zomato.dto.request.FoodOrderRequest;
import com.example.zomato.dto.response.FoodOrderResponse;
import com.example.zomato.exceptions.CustomerNotFound;
import com.example.zomato.exceptions.DeliveryPartnerNotFound;
import com.example.zomato.exceptions.FoodItemNotFound;
import com.example.zomato.models.Customer;
import com.example.zomato.models.DeliveryPartner;
import com.example.zomato.models.FoodItem;
import com.example.zomato.models.FoodOrder;
import com.example.zomato.models.enums.OrderStatus;
import com.example.zomato.repository.CustomerRepo;
import com.example.zomato.repository.DeliveryPartnerRepo;
import com.example.zomato.repository.FoodItemRepo;
import com.example.zomato.repository.FoodOrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FoodOrderService {
    private final FoodOrderRepo foodOrderRepo;
    private final CustomerRepo customerRepo;
    private final DeliveryPartnerRepo deliveryPartnerRepo;
    private final FoodItemRepo foodItemRepo;

    public FoodOrderResponse placeOrder(FoodOrderRequest foodOrderRequest) {
        Optional<Customer> optional=customerRepo.findById(foodOrderRequest.getCustomerId());

        if(optional.isEmpty()) throw new CustomerNotFound("Invalid Customer id "+ foodOrderRequest.getCustomerId());

        Customer customer=optional.get();

        List<FoodItem> foodItems=new ArrayList<>();

        double totalPrice=0;

        for(int foodItemId: foodOrderRequest.getFoodItemIds()){
           Optional<FoodItem> optionalFoodItem=foodItemRepo.findById(foodItemId);
           if(optionalFoodItem.isEmpty()) throw new FoodItemNotFound("Invalid Food Item");

           FoodItem foodItem=optionalFoodItem.get();
           totalPrice+=foodItem.getPrice();

            foodItems.add(foodItem);

        }
        List<DeliveryPartner> deliveryPartners=deliveryPartnerRepo.findAll();

        if(deliveryPartners.isEmpty()){
            throw new DeliveryPartnerNotFound("No Delivery Partner Available");
        }

        Random random=new Random();
        DeliveryPartner partner=deliveryPartners.get(random.nextInt(deliveryPartners.size()));

        FoodOrder foodOrder=FoodOrder.builder()
                .totalValue(totalPrice)
                .orderStatus(OrderStatus.ACCEPTED)
                .customer(customer)
                .deliveryPartner(partner)
                .foodItems(foodItems)
                .build();

        FoodOrder savedOrder=foodOrderRepo.save(foodOrder);


        if(customer.getFoodOrders()==null){
            customer.setFoodOrders(new ArrayList<>());
        }
        customer.getFoodOrders().add(savedOrder);


        if(partner.getFoodOrders()==null){
            partner.setFoodOrders(new ArrayList<>());
        }
        partner.getFoodOrders().add(savedOrder);

        for(FoodItem foodItem:foodItems){
            if(foodItem.getFoodOrders()==null){
                foodItem.setFoodOrders(new ArrayList<>());
            }
            foodItem.getFoodOrders().add(savedOrder);
        }

        return FoodOrderConvertor.foodOrderToFoodOrderResponse(savedOrder);
    }

    public List<FoodOrderResponse> getOrderHistory(int customerId) {
        Optional<Customer> optionalCustomer=customerRepo.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFound("Invalid customerId "+customerId);
        }
        Customer customer=optionalCustomer.get();
        List<FoodOrder> foodOrders=customer.getFoodOrders();
        List<FoodOrderResponse> responses=new ArrayList<>();
        for(FoodOrder foodOrder:foodOrders){
            responses.add(FoodOrderConvertor.foodOrderToFoodOrderResponse(foodOrder));
        }
        return responses;
    }
}
