package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Restaurant;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RestaurantListImpl implements RestaurantList {

	private final JdbcTemplate jdbcTemplate;

	/**
	 *レストラン名で検索　
	 */
	@Override
	public List<Restaurant> selectByName(String restaurantName) {

		String sql = ""
			    + "SELECT "
			    + "  restaurant.restaurant_id, "
			    + "  restaurant.restaurant_name, "
			    + "  restaurant.catch_phrase, "
			    + "  COALESCE(AVG(review.rating), 0.0) AS average_rating "
			    + "FROM m_restaurant restaurant "
			    + "LEFT OUTER JOIN t_review review "
			    + "  ON restaurant.restaurant_id = review.restaurant_id "
			    + "WHERE restaurant.restaurant_name LIKE ? "
			    + "GROUP BY restaurant.restaurant_id, restaurant.restaurant_name, restaurant.catch_phrase "
			    + "ORDER BY restaurant.restaurant_id";

		
		String placeholder = "%" + restaurantName + "%";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, placeholder);
		
//		値を取得して、結果を格納
		List<Restaurant> result = new ArrayList<Restaurant>();
		for(Map<String, Object> data: list) {
			Restaurant restaurant = new Restaurant();
			restaurant.setRestaurantId((int)data.get("restaurant_id"));
			restaurant.setRestaurantName((String)data.get("restaurant_name"));
			restaurant.setCatchPhrase((String)data.get("catch_phrase"));
			double value = ((BigDecimal)data.get("average_rating")).doubleValue();
			restaurant.setAverageRating(value);
			result.add(restaurant);
		}
		
		return result;
	}

}
