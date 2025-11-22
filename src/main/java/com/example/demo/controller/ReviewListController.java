package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Review;
import com.example.demo.form.ReviewSearchForm;

@Controller
public class ReviewListController {
	
	/** 検索リクエスト/
	 * 
	 */
	@PostMapping("/search-review")
	private String searchReview (@ModelAttribute ReviewSearchForm form, Model model) {
		
		List<Review> list = new ArrayList<Review>();
		
		
		Review review = new Review();
		
		review.setReviewId(null);
		review.setRestaurantId(1);
		review.setUserId("user1");
		review.setVisitDate(Date.valueOf("2027-07-01"));
		review.setRating(3);
		review.setComment("comment");
		list.add(review);
		System.out.println(review);
		
		if(list.size() > 0) {
			model.addAttribute("reviewList", list);
		}
		
		return "review-list";
	}

}
