package com.example.zomato.controller;

import com.example.zomato.dto.request.RestaurantRequest;
import com.example.zomato.dto.response.FoodItemResponse;
import com.example.zomato.dto.response.RestaurantResponse;
import com.example.zomato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse response=restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/option/{option}")
    public ResponseEntity switchMode(@RequestParam("id") int restaurant_id,@PathVariable("option") String option){
        try{
            List<FoodItemResponse> response=restaurantService.switchMode(restaurant_id,option);
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/min/{min}/max/{max}")
    public ResponseEntity getFoodItems(@RequestParam("id") int id,@PathVariable int min,
                                       @PathVariable int max){
        try{
            List<FoodItemResponse> response=restaurantService.getFoodItems(id,min,max);
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    public ResponseEntity updateRestaurant(@RequestParam("id") int restaurantId,@RequestBody RestaurantRequest restaurantRequest){
        try{
            RestaurantResponse response=restaurantService.updateRestaurant(restaurantId,restaurantRequest);
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
