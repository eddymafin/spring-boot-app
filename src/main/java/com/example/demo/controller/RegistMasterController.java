package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Restaurant;
import com.example.demo.form.RestaurantRegistForm;
import com.example.demo.service.RegistRestaurantService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistMasterController {

	private final RegistRestaurantService restaurantService;

	/**
	 * 管理画面表示
	 * @return
	 */
	@GetMapping("/mt-top")
	public String showMasterPage() {
		return "mt-top";

	}

	/**
	 * 登録画面表示
	 * @param form
	 * @return
	 */
	@GetMapping("/show-restaurant-form")
	public String showRestaurantForm(@ModelAttribute RestaurantRegistForm form) {
		return "regist-restaurant-form";

	}

	/**
	 * 登録リクエスト
	 * @param form　RestaurantRegistForm
	 * @param result　バリデーションの結果
	 * @return
	 */
	@PostMapping("/regist-restaurant")
	public String RegistRestaurant(@Validated @ModelAttribute RestaurantRegistForm form, BindingResult result) {
		//		System.out.println(form);

		if (result.hasErrors()) {
			return "regist-restaurant-form";
		}

		return "confirm-regist-restaurant";
	}

	/**
	 * 確認画面から戻ってきたときの画面表示
	 * @param form
	 * @return
	 */
	@PostMapping("/show-restaurant-form-ret")
	public String showRestaurantFormRet(@ModelAttribute RestaurantRegistForm form) {
		return "regist-restaurant-form";
	}

	/**
	 * 確認画面から、登録の処理
	 * @param model
	 * @return
	 */
	@PostMapping("/confirm-regist-restaurant")
	public String confirmRegistRestaurant(@Validated RestaurantRegistForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "regist-restaurant-form";
		}

		Restaurant restaurant = new Restaurant();

		restaurant.setRestaurantName(form.getRestaurantName());
		restaurant.setCatchPhrase(form.getCatchPhrase());

		//		DBに登録
		restaurantService.regist(restaurant);
		//
		//	    redirectAttributes.addFlashAttribute("msg", "レストラン登録完了しました");
		//	    redirectAttributes.addFlashAttribute("from", "restaurant"); 
		// URLに渡す場合は日本語をエンコード
		String msg = "";
		try {
			//	 例外処理で記述しないと、エンコードされない
			msg = java.net.URLEncoder.encode("レストラン登録が完了しました", "UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {
			// 万が一エンコードに失敗した場合、簡単に fallback
			msg = "レストラン登録完了しました";
		}
		return "redirect:/complete?from=restaurant&msg=" + msg;
	}

}
