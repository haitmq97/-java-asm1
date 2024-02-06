package me.haitmq.spring.mvc.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.haitmq.spring.mvc.crud.entity.User;


@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/home")
	public String homepage(@RequestParam()int param1,@RequestParam()int param2) {
		return "/test/home";
	}
	
	@RequestMapping("/page1")
	public String page1(@RequestParam()int param1,@RequestParam()int param2) {
		return "/test/page1";
	}
	
	@RequestMapping("/page2")
	public String page2(@RequestParam()int param1,@RequestParam()int param2) {
		return "/test/page2";
	}
	
	@RequestMapping("/page3")
	public String page3(@RequestParam()int param1,@RequestParam()int param2) {
		return "/test/page3";
	}
	
	@RequestMapping("/form")
	public String form(Model theModel) {
		theModel.addAttribute("user", new User());
		return "/test/form";
	}
}
