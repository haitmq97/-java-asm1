package me.haitmq.spring.mvc.crud.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import me.haitmq.spring.mvc.crud.service.DonateService;

// home controller 

@Controller
@RequestMapping("/v1")
public class HomeController {

	
	@Autowired
	private DonateService donateService;

	// home page endpoint
	@GetMapping("/home")
	public String homePage(HttpServletRequest request, Model theModel) {
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			theModel.addAttribute("userId", currentUserId);
			
			// update all money donation from donate for sure
			donateService.updateAllMoney();
			
			return "donation/home-page";
		} catch (Exception e) {
			e.printStackTrace();
			return "donation/error-page";
		}
		
	}
}
