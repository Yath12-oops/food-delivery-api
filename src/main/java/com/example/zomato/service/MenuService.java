package com.example.zomato.service;

import com.example.zomato.convertors.FoodItemConvertor;
import com.example.zomato.dto.request.FoodItemRequest;
import com.example.zomato.dto.response.FoodItemResponse;
import com.example.zomato.dto.response.MenuResponse;
import com.example.zomato.exceptions.RestaurantNotFound;
import com.example.zomato.models.FoodItem;
import com.example.zomato.models.Menu;
import com.example.zomato.models.Restaurant;
import com.example.zomato.repository.MenuRepo;
import com.example.zomato.repository.RestaurantRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    //constructor Injection
    private final RestaurantRepo restaurantRepo;

    private final MenuRepo menuRepo;

//    public MenuService(MenuRepo menuRepo,RestaurantRepo restaurantRepo){
//
//        this.menuRepo=menuRepo;
//        this.restaurantRepo=restaurantRepo;
//    }


    public MenuResponse registerMenu(int restaurantId, List<FoodItemRequest> foodItemRequests) {

        Optional<Restaurant> restaurantOptional=restaurantRepo.findById(restaurantId);
        if(restaurantOptional.isEmpty()){
        throw new RestaurantNotFound("Invalid Restaurant id "+ restaurantId);
        }

        Restaurant restaurant=restaurantOptional.get();

        Menu menu=new Menu();
        menu.setRestaurant(restaurant);

        List<FoodItem> foodItems=new ArrayList<>();

        for(FoodItemRequest request:foodItemRequests){
            FoodItem item=FoodItemConvertor.foodItemRequestToFoodItem(request);
            foodItems.add(item);
            item.setMenu(menu);
        }
        menu.setFoodItems(foodItems);
        Menu savedMenu=menuRepo.save(menu);

        MenuResponse menuResponse=new MenuResponse();
        menuResponse.setRestaurantName(savedMenu.getRestaurant().getName());

        List<FoodItemResponse> foodItemResponses=new ArrayList<>();
        for(FoodItem item:savedMenu.getFoodItems()){
            foodItemResponses.add(FoodItemConvertor.foodItemtoFoodItemResponse(item));
        }
        menuResponse.setFoodItems(foodItemResponses);
        return menuResponse;
    }

    public MenuResponse deleteMenu(int restaurantId) {

        Optional<Restaurant> optional =restaurantRepo.findById(restaurantId);

        if(optional.isEmpty()) throw new RestaurantNotFound("Invalid Restaurant Id " + restaurantId);

        Restaurant restaurant = optional.get();

        Menu menu = restaurant.getMenu();

        MenuResponse response = new MenuResponse();

        response.setRestaurantName(restaurant.getName());

        List<FoodItemResponse> responses = new ArrayList<>();

        for(FoodItem foodItem : menu.getFoodItems()) {
            responses.add(FoodItemConvertor.foodItemtoFoodItemResponse(foodItem));
        }

        response.setFoodItems(responses);

        return response;
    }
}
