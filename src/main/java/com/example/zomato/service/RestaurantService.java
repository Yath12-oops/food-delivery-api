package com.example.zomato.service;

import com.example.zomato.convertors.FoodItemConvertor;
import com.example.zomato.convertors.RestaurantConvertor;
import com.example.zomato.dto.request.RestaurantRequest;
import com.example.zomato.dto.response.FoodItemResponse;
import com.example.zomato.dto.response.RestaurantResponse;
import com.example.zomato.exceptions.RestaurantNotFound;
import com.example.zomato.models.FoodItem;
import com.example.zomato.models.Restaurant;
import com.example.zomato.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest){
        Restaurant restaurant= RestaurantConvertor.restaurantRequestToRestaurant(restaurantRequest);
        Restaurant savedRestaurant=restaurantRepo.save(restaurant);
        return RestaurantConvertor.restaurantToRestaurantResponse(savedRestaurant);
    }

    public List<FoodItemResponse> switchMode(int restaurantId, String option) {
        Optional<Restaurant> optional=restaurantRepo.findById(restaurantId);

        if(optional.isEmpty()) throw new RestaurantNotFound("Invalid Restaurant id "+ restaurantId);

        Restaurant restaurant=optional.get();
        List<FoodItem> foodItemList=restaurant.getMenu().getFoodItems();

        boolean isVeg=true;
        if(option.equals("NonVeg")) isVeg=false;

        List<FoodItemResponse> responseList=new ArrayList<>();

        for(FoodItem foodItem:foodItemList){
            if(foodItem.isVeg()==isVeg) responseList.add(FoodItemConvertor.foodItemtoFoodItemResponse(foodItem));
        }
        return responseList;
    }
}
