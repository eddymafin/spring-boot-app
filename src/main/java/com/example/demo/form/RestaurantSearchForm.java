package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor すべてのフィールドを初期化するコンストラクタを自動生成
//@NoArgsConstructor　引数を指定しないコンストラクタを自動生成
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantSearchForm {

	private String restaurantName;
}
