package com.example.zomato.repository;

import com.example.zomato.models.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepo extends JpaRepository<FoodOrder,Integer> {
}
