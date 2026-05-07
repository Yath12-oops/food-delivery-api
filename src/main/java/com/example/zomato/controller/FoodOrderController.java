package com.example.zomato.controller;

import com.example.zomato.dto.request.FoodItemRequest;
import com.example.zomato.dto.request.FoodOrderRequest;
import com.example.zomato.dto.response.FoodOrderResponse;
import com.example.zomato.service.FoodOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class FoodOrderController {

    private final FoodOrderService foodOrderService;

    @PostMapping
    public ResponseEntity placeOrder(@RequestBody FoodOrderRequest foodOrderRequest){
        try{
            FoodOrderResponse response=foodOrderService.placeOrder(foodOrderRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
