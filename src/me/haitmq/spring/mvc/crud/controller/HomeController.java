package me.haitmq.spring.mvc.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class HomeController {

	@RequestMapping("/home")
	public String homePage() {
		return "home";
	}
	
	@RequestMapping("/home2")
	public String homePage2() {
		
	}
}
