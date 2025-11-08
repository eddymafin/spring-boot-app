package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class RestaurantRegistForm {

	@NotNull(message="入力してください")
	@Size(min=1, max=32, message="1〜32文字の間で入力してください。")
	private String restaurantName;
	
	@NotNull(message="入力してください")
	@Size(min=1, max=64, message="1〜64文字の間で入力してください。")
	private String catchPhrase;
}
