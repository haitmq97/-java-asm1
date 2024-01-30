package me.haitmq.spring.mvc.crud.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.haitmq.spring.mvc.crud.entity.Donate;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.DonateService;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;

@Controller
@RequestMapping("/donate")
public class DonateController {

	@Autowired
	private DonateService donateService;

	@Autowired
	private DonationService donationService;

	@Autowired
	private UserService userService;

	@GetMapping("donate-form")
	public String donateForm(HttpServletRequest request, @RequestParam("donationId") int donationId, Model theModel) {
		theModel.addAttribute("donate", new Donate());
		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");
		System.out.println("==================>>>> currentUserId: " + currentUserId);
		System.out.println("==================>>>> donationId: " + donationId);
		theModel.addAttribute("donationId", donationId);
		return "donate-form";
	}

	@PostMapping("/donating")
	public String donating(HttpServletRequest request, @ModelAttribute("donate")Donate donate, @RequestParam("donationId")int donationId) {
		System.out.println("=============>>>>>> donationId: " + donationId);
		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");
		Donation currentDonation = donationService.getDonation(donationId);
		User user = userService.getUser(currentUserId);
		donate.setUser(user);
		donate.setDonation(currentDonation);
		donateService.save(donate);
		return "redirect:/v1/home3";
	}
}
