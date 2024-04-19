package me.haitmq.spring.mvc.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.Time;

	

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private DonationService donationService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDonationService userDonationService;
	
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
	
	@RequestMapping("/userDonationbtn")
	public String form() {
		
		return "/donation/test123";
	}
	
	
	@GetMapping("/list")
	public String donationlist(@RequestParam(defaultValue = "1") int page, @RequestParam( name="size") int size,
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
//		return "user/donation-table";
		return "donation/donationList";
	}
	
	
	@GetMapping("/userlist")
	public String userList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {

		Page<User> users = userService.findByEmailOrPhoneNumberOrStatus(searchingValue, page, size);
		theModel.addAttribute("searchingValue", searchingValue);

		theModel.addAttribute("users", users);

		theModel.addAttribute("currentPage", page);

		theModel.addAttribute("currentSize", size);

		int nextPage = page + 1;
		int prevPage = page - 1;

		if (page <= 1) {
			prevPage = 1;
		}

		theModel.addAttribute("prevPage", prevPage);

		if (page >= (users.getTotalPages())) {
			nextPage = users.getTotalPages();
		}

		theModel.addAttribute("nextPage", nextPage);
		
		System.out.println("=======================>>>>> test time");
		System.out.println("=======================>>>>> current time: " + Time.getCurrentDateTime());

		return "admin/user-table";
	}
	
	
	
	@GetMapping("/donationlistforadmin")
	public String donationListForAdmin(@RequestParam(defaultValue = "1") int page, @RequestParam(name="size", defaultValue = "5") int size,
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
		return "admin/donation-table";
	}
	
	
	@GetMapping("managerpage")
	public String managerPage() {
		
		
		return "admin/home";
	}
	
	
	
	@GetMapping("/userDonationlistforadmin")
	public String userDonationList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			
			Model theModel) {

		try {
			Page<UserDonation> userDonations = userDonationService.findAllSortByStatusByCreatedDate(page, size);

			theModel.addAttribute("datas", userDonations);

			theModel.addAttribute("currentPage", page);

			theModel.addAttribute("currentSize", size);

			int nextPage = page + 1;
			int prevPage = page - 1;

			if (page <= 0) {
				prevPage = 0;
			}

			theModel.addAttribute("prevPage", prevPage);

			if (page >= (userDonations.getTotalPages() - 1)) {
				nextPage = userDonations.getTotalPages() - 1;
			}

			theModel.addAttribute("nextPage", nextPage);
			
			System.out.println("=======================>>>>> test time");
			System.out.println("=======================>>>>> current time: " + Time.getCurrentDateTime());

			return "admin/userDonation-table";
		} catch (Exception e) {
			//log.error("UserDonationController ERROR - list(): ", e);
			return "common/error-page";
		}
	}
	
	
	
	@GetMapping("/register")
	public String showFormForAdd(Model theModel) {

		User theUser = new User();
		theModel.addAttribute("user", theUser);

		return "common/register-form";
	}
	
}
