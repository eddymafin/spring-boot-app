package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Review;

@Repository
public class RegistRepositoryImpl implements RegistRepository {


	@Override
	public void add(Review review) {
		System.out.println("登録");
		System.out.println(review);

	}

}
