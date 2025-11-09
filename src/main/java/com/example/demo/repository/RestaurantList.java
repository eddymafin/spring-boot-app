package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Restaurant;

public interface RestaurantList {
	List<Restaurant> selectByName(String restaurantName);
}
