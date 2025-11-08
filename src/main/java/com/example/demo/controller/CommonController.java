package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {
	
	
	
	/**
	 * 完了後のリダイレクト先
	 * @return complete
	 */
	@GetMapping("/complete")
	private String complete(@RequestParam String from,@RequestParam String msg, Model model) {
	    model.addAttribute("from", from);
	    model.addAttribute("msg", msg);
		return "complete";
	}

}