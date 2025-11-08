package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistRestaurantServiceImpl implements RegistRestaurantService{

	private final RestaurantRepository restaurantRepository;
	
	@Override
	public void regist(Restaurant restaurant) {
		System.out.println(restaurant);
		restaurantRepository.add(restaurant);
	}
	
}
