package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Review;
import com.example.demo.form.ReviewRegistForm;
import com.example.demo.service.RegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistController {

	private final RegistService service;

	//	登録画面表示
	@PostMapping("/show-review-form")
	public String showReviewForm(@ModelAttribute ReviewRegistForm form) {
		return "regist-review";
	}

	//	登録画面から戻ってきたときの画面
	@PostMapping("/show-review-form-ret")
	public String showReviewFormRet(@ModelAttribute ReviewRegistForm form) {
		return "regist-review";
	}

	//	登録リクエスト
	@PostMapping("/regist-review")
	public String registReview(@Validated @ModelAttribute ReviewRegistForm form, BindingResult result) {
		System.out.println(form);

		//		 入力項目にエラーがある場合は、登録画面に遷移する
		if (result.hasErrors()) {
			return "regist-review";
		}

		//		 エラーがなければ、確認画面に遷移
		return "confirm-regist-review";
	}

	@PostMapping("/confirm-regist-review")
	public String confirmRegistReview(@Validated ReviewRegistForm form, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "regist-review";
		}

		//		登録用のDTOを作成
		Review review = new Review();
		//		formのgetterを使って、値を取得、DTOのsetterを使って、値を設定している
		review.setRestaurantId(form.getRestaurantId());
		review.setUserId(form.getUserId());
		review.setVisitDate(form.getVisitDate());
		review.setRating(form.getRating());
		review.setComment(form.getComment());
		service.regist(review);

		//		ここでDBに登録する処理を行う
		//		model.addAttribute("msg", "登録が完了しました");
		//		フラッシュスコープに格納するように修正
		
		String msg = "";
		try {
			//	 例外処理で記述しないと、エンコードされない
			msg = java.net.URLEncoder.encode("レビュー登録が完了しました", "UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {
			// 万が一エンコードに失敗した場合、簡単に fallback
			msg = "レビュー登録が完了しました";
		}
		return "redirect:/complete?from=review&msg=" + msg;
	}

}
