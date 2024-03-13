package me.haitmq.spring.mvc.crud.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

// home controller 

@Controller
@RequestMapping("/v1")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DonateService donateService;
	
	@Autowired
	private DonationService donationService;

	// home page endpoint
	@GetMapping("/home")
	public String homePage(HttpServletRequest request, Model theModel) {
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			System.out.println("====================> current user id: " +currentUserId);
			theModel.addAttribute("userId", currentUserId);
			
			// update all money donation from donate for sure
			donateService.updateAllMoney();
			
			return "public/home";
//			return "user/donationList";
		} catch (Exception e) {
			e.printStackTrace();
			return "common/error-page";
		}
		
	}
	
	
	@GetMapping("/donations")
	public String donationlist(@RequestParam(defaultValue = "1") int page, @RequestParam(name="size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {
		
		System.out.println("size: " + size);
		Page<Donation> donations = donationService.findAll(page, size);
		
		
		if(!searchingValue.equals("")) {
			donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
			theModel.addAttribute("searchingValue", searchingValue);
		}
		
		
		
		theModel.addAttribute("donations", donations);
		
		theModel.addAttribute("currentPage", page);
		theModel.addAttribute("totalPage", donations.getTotalPages());

		theModel.addAttribute("currentSize", size);

		int nextPage = page + 1;
		int prevPage = page - 1;

		if (page <= 1) {
			prevPage = 1;
		}

		theModel.addAttribute("prevPage", prevPage);
		System.out.println("current page" + page);
		System.out.println("total page: " + donations.getTotalPages());
		if (page >= (donations.getTotalPages())) {
			nextPage = donations.getTotalPages();
		}

		theModel.addAttribute("nextPage", nextPage);

//		return "user/donationList";
		return "public/donation-table";
	}
	
	
	@GetMapping("/donations2")
	public String donationlist2(@RequestParam(defaultValue = "1") int page, @RequestParam(name="size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {
		
		System.out.println("size: " + size);
		Page<Donation> donations = donationService.findAll(page, size);
		
		
		if(!searchingValue.equals("")) {
			donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
			theModel.addAttribute("searchingValue", searchingValue);
		}
		
		
		
		theModel.addAttribute("donations", donations);
		
		theModel.addAttribute("currentPage", page);
		theModel.addAttribute("totalPage", donations.getTotalPages());

		theModel.addAttribute("currentSize", size);

		int nextPage = page + 1;
		int prevPage = page - 1;

		if (page <= 1) {
			prevPage = 1;
		}

		theModel.addAttribute("prevPage", prevPage);
		System.out.println("current page" + page);
		System.out.println("total page: " + donations.getTotalPages());
		if (page >= (donations.getTotalPages())) {
			nextPage = donations.getTotalPages();
		}

		theModel.addAttribute("nextPage", nextPage);

//		return "user/donationList";
		return "public/temp/donation-table";
	}

	
	// register
	
	@GetMapping(value = {"/registerForm", "/register"})
	public String showFormForAdd(Model theModel) {
		User newUser = new User();
		theModel.addAttribute("user", newUser);
		theModel.addAttribute("process", "processRegister");
		

		return "common/register-form";
	}
	
	
	@PostMapping("/processRegister")
	public String processRegister(@ModelAttribute("user")User theUser) {
		userService.register(theUser);
		
		return "redirect:/v1/home";
	}
	
	
	// login
	@GetMapping("login")
	public String showLoginForm(Model theModel) {
		
		System.out.println("test hearssssssssssssssssssssss");
		User theLoginUser = new User();
		theModel.addAttribute("user", theLoginUser);
		theModel.addAttribute("process", "processLogin");

		return "common/login-form";
	}

	@PostMapping("processLogin")
	public String processLogin(HttpServletRequest request, @ModelAttribute("user") User loginUser) {
		int userId = userService.getIdIfUserExisted(loginUser);

		if (userId != -1) {
			HttpSession session = request.getSession();

			session.setAttribute("currentUserId", userId);
			

			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			
			System.out.println("=========================>>>>>> user id: " + currentUserId);
		}

		return "redirect:/v1/home";
	}
	
	
	
	@GetMapping("donateForm")
	public String donateForm(HttpServletRequest request,@RequestParam("id") int donationId, Model theModel) {
		try {
			// get current user id and role
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			// if the user has not logged in yet then show login form
			if(currentUserId == null) {
				return "redirect:/v1/home";
			}
			// add new Donate obj to the model
			theModel.addAttribute("donate", new Donate());
			
			// add process form link to model
			theModel.addAttribute("process", "processDonating");
			
			// add donationId to the model (for process form)
			theModel.addAttribute("donationId", donationId);
			
			// return view
			return "user/donate-form";
		} catch (Exception e) {
			//log.error("DonateController ERROR - donateForm(): ", e);
			return "common/error-page";
		}
	}
	
	@PostMapping("/processDonating")
	public String processDonating(HttpServletRequest request, @ModelAttribute("donate")Donate donate, @RequestParam("donationId") int donationId) {
		
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			
			User user = userService.getUser(currentUserId);
			Donation donation = donationService.getDonation(donationId);
			donate.setUser(user);
			donate.setDonation(donation);
			donateService.save(donate);
			return "redirect:/v1/home";
		} catch (Exception e) {
			//log.error("DonateController ERROR - processDonating(): ", e);
			return "common/error-page";
		}
	}
	
	
	@GetMapping("donation-detail")
	public String donationDetails(HttpServletRequest request, @RequestParam("id") int theId, Model theModel) {
		HttpSession session = request.getSession();
		
		Donation donation = donationService.getDonation(theId);

		theModel.addAttribute("donation", donation);

		return "public/donation-detail";
	}
	
}
