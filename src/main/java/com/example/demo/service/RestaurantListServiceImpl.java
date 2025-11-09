package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantListServiceImpl implements RestaurantListService {

	private final RestaurantList repository;
	@Override
	public List<Restaurant> findByName(String restaurantName) {
		List<Restaurant> list = repository.selectByName(restaurantName);
		return list;
	}

}
