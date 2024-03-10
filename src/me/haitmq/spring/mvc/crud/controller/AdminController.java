package me.haitmq.spring.mvc.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	
	
	
	@GetMapping("/manager")
	public String managerPage() {
		
		
		return "admin/home";
	}
	
	
	
}
