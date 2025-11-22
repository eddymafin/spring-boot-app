package com.example.demo.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Review;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void add(Review review) {
		String sql = "INSERT INTO t_review" + "(restaurant_id, user_id, visit_date, rating, comment)" + "VALUES (?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, review.getRestaurantId(),
				review.getUserId(),
				review.getVisitDate(),
				review.getRating(),
				review.getComment()
				);
	             
		System.out.println(review);

	}

	@Override
	public List<Review> selectByRestaurantId(int restaurantId) {

		
    String sql = """
        SELECT
            review_id,
            restaurant_id,
            user_id,
            visit_date,
            rating,
            comment
        FROM
            t_review
        WHERE
            restaurant_id = ?
        ORDER BY
            visit_date DESC,
            review_id ASC;
        """;

				// sqlで検索　引数で受け取ったrestaurantIdを渡す
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, restaurantId);

				List<Review> result = new ArrayList<Review>();
				for(Map<String, Object> item : list){

					Review review = new Review();
					review.setReviewId((int)item.get("review_id"));
					review.setRestaurantId((int)item.get("restaurant_id"));
					review.setUserId((String)item.get("user_id"));
					review.setVisitDate((Date)item.get("visit_date"));
					review.setRating((int)item.get("rating"));
					review.setComment((String)item.get("comment"));
					result.add(review);
				}
		return result;
	}

}
