package com.example.zomato.repository;

import com.example.zomato.models.Menu;
import com.example.zomato.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {
}
