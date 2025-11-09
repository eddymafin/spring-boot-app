package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Restaurant;
import com.example.demo.form.RestaurantSearchForm;
import com.example.demo.service.RestaurantListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RestaurantListController {
	
	private final RestaurantListService service;

	/**
	 * レストラン検索一覧画面を表示
	 * @param form
	 * @return
	 */
	@GetMapping("/top")
	private String restaurantList(@ModelAttribute RestaurantSearchForm form) {
		return "restaurant-list";
	}
	
	
	/**
	 * 検索リクエスト
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/restaurant-search")
	private String restaurantSearch(@ModelAttribute RestaurantSearchForm form, Model model) {
		
//		テストコード
//		List<Restaurant> list = new ArrayList<Restaurant>();

//		list.add(new Restaurant(1, "店舗1", "キャッチ", 3.5));
//		list.add(new Restaurant(2, "店舗2", "キャッチ", 4.0));
//		list.add(new Restaurant(3, "店舗3", "キャッチ", 4.444));
		
		
		List<Restaurant> list = service.findByName(form.getRestaurantName());
		
		model.addAttribute("restaurantList",list);
		return "restaurant-list";
	}

}
