package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeMvcController {
	
	//下記は、Modelオブジェクトを使用する方法 こちらのほうが新しい記述方法
	@GetMapping("/welcome-m")
	public String welcomM(Model model) {
	  
		//下記でデータを格納する処理。grettingという属性名で、"Hello"という文字列を格納
		model.addAttribute("greeting", "Hello");
		model.addAttribute("welcome", "Welcome MVC(Model)");
		
		return "welcome-mvc";
	}
	
 //下記が、ModelAndViewを使用する方法
	@GetMapping("/welcome-mv")
	public ModelAndView welcomeMv(ModelAndView mv) {
	//下記でデータを格納する処理。grettingという属性名で、"Hello"という文字列を格納
		mv.addObject("greeting", "Here is Model and View");
		mv.addObject("welcome", "Welcome MVC (ModelAndView)");
		
		//HTMLテンプレート名を指定
		mv.setViewName("welcome-mvc");
		
		return mv;
	}
	

}
