package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParamController {

	//	GETリクセルの参考例
	//	@GetMapping("/get-param")
	////	public String getParam(@RequestParam String greeting,
	//	// public String getParam(@RequestParam(name="g") String greeting,
	//	// public String getParam(@RequestParam(required=false) String greeting,		
	//	 public String getParam(@RequestParam(defaultValue="(not)") String greeting,
	//			
	//		Model model) {
	//		model.addAttribute("mGreeting", greeting);
	//		
	//		return "display";
	//		
	//	}

	@GetMapping("/input")
	public String input() {
		return "input";
	}

	@PostMapping("/post-param")
		 public String getParam(@RequestParam(defaultValue="(not)") String greeting,
			Model model) {
		model.addAttribute("mGreeting", greeting);
		return "display";
	}
}
