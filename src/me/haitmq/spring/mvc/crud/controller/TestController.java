package me.haitmq.spring.mvc.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/home")
	public String homepage() {
		return "/test/hometest";
	}
	
	@RequestMapping("/test1")
	public String test1() {
		return "/test/test1";
	}
}
