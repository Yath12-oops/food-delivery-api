package com.example.zomato.repository;

import com.example.zomato.models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepo extends JpaRepository<FoodItem,Integer> {
}
