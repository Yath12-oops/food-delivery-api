package com.example.zomato.controller;

import com.example.zomato.dto.request.FoodItemRequest;
import com.example.zomato.dto.response.MenuResponse;
import com.example.zomato.models.FoodItem;
import com.example.zomato.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity registerMenu(@RequestParam("id") int restaurantId,

                                       @RequestBody List<FoodItemRequest> foodItemRequests){

        try {
            MenuResponse response = menuService.registerMenu(restaurantId, foodItemRequests);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteMenu(@RequestParam("id") int restaurantId){

        try{
            MenuResponse response=menuService.deleteMenu(restaurantId);
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
