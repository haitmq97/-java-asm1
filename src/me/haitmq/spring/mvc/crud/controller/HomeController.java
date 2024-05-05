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

import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;

// home controller 

@Controller
@RequestMapping("/v1")
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDonationService userDonationService;

	@Autowired
	private DonationService donationService;

	/*
	 * // home page endpoint
	 * 
	 * @GetMapping("/home") public String homePage(HttpServletRequest request, Model
	 * theModel) { try { HttpSession session = request.getSession(); Integer
	 * currentUserId = (Integer) session.getAttribute("currentUserId");
	 * System.out.println("====================> current user id: " +currentUserId);
	 * theModel.addAttribute("userId", currentUserId);
	 * 
	 * // update all money donation from userDonation for sure
	 * userDonationService.updateAllMoney();
	 * 
	 * return "public/home"; // return "user/donationList"; } catch (Exception e) {
	 * e.printStackTrace(); return "common/error-page"; }
	 * 
	 * }
	 * 
	 */

	@GetMapping("/donations3")
	public String donationlist(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {

		System.out.println("size: " + size);
		Page<Donation> donations = donationService.findAll(page, size);

		if (!searchingValue.equals("")) {
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
		theModel.addAttribute("authorities", "admin");

		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");

		System.out.println("=======================>>>>>>>>>>>>>>> home page Current userId :" + currentUserId);
		if (currentUserId != null) {

			if (userService.isAdmin((int) currentUserId)) {
				theModel.addAttribute("authorities", "admin");

				System.out.println("=============>>>> test 1");
			} else {
				theModel.addAttribute("authorities", "user");
				System.out.println("=============>>>> test 2");
			}

		} else {

			theModel.addAttribute("authorities", "none");
			System.out.println("=============>>>> test 3");
		}

		System.out.println("=============>>>> test 4");

//		return "user/donationList";
		return "public/donation-table3";
	}

	@GetMapping("/donations2")
	public String donationlist2(@RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {

		System.out.println("size: " + size);
		Page<Donation> donations = donationService.findAll(page, size);

		if (!searchingValue.equals("")) {
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
		return "public/donation-table2";
	}

	// register

	@GetMapping(value = { "/registerForm", "/register" })
	public String showFormForAdd(Model theModel) {
		User newUser = new User();
		theModel.addAttribute("user", newUser);
		theModel.addAttribute("process", "processRegister");

		return "common/register-form";
	}

	@PostMapping("/processRegister")
	public String processRegister(@ModelAttribute("user") User theUser) {
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

	@GetMapping("/home2")
	public String donationlist3(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "donationId", defaultValue = "1") int donationId,

			Model theModel) {

		
		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");
		
		// authorities model
		
		
	
		
		theModel.addAttribute("authorities", "admin");

		

		System.out.println("=======================>>>>>>>>>>>>>>> home page Current userId :" + currentUserId);
		
		
		
		if (currentUserId != null) {

			if (userService.isAdmin((int) currentUserId)) {
				theModel.addAttribute("authorities", "admin");

				System.out.println("=============>>>> test 1");
			} else {
				theModel.addAttribute("authorities", "user");
				System.out.println("=============>>>> test 2");
			}

		} else {

			theModel.addAttribute("authorities", "none");
			System.out.println("=============>>>> test 3");
		}
		
		
		// nav model
		
		
		
		// donation modal
		
		System.out.println("size: " + size);
		Page<Donation> donations = donationService.findAll(page, size);

		if (!searchingValue.equals("")) {
			donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
			theModel.addAttribute("searchingValue", searchingValue);
			
			System.out.println("=======================>>>>>>> ");
			System.out.println("=======================>>>>>>> donations: " + donations);
		}
		
		System.out.println("=======================>>>>>>> 2222222222222222222");
		System.out.println("=======================>>>>>>> 2222222222222 donations: " + donations);
		System.out.println("=======================>>>>>>> 2222222222222 donations size: " + donations.getSize());
		System.out.println("=======================>>>>>>> 2222222222222 donations totalElement: " + donations.getTotalElements());
		System.out.println("=======================>>>>>>> 2222222222222 donations content: " + donations.getContent());
		
		

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
		
		
		// donate form model
		

		System.out.println("=============>>>> test 4");
		theModel.addAttribute("userDonation", new UserDonation());

		// add process form link to model
		theModel.addAttribute("process", "processDonating");

		// add donationId to the model (for process form)
		theModel.addAttribute("donationId", donationId);
		
		
		// other
		
		
		theModel.addAttribute("totalElements", donations.getTotalElements());

//		return "user/donationList";
		return "public/donation-table";
	}
	
	
	
	@GetMapping("/home")
	public String donationlist4(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "donationId", defaultValue = "1") int donationId,

			Model theModel) {
		
		
		HttpSession session = request.getSession();
		
		// nav and authorites model attributes
		
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");


		System.out.println("=======================>>>>>>>>>>>>>>> home page Current userId :" + currentUserId);
		
		
		Boolean isLogined = false;
		Boolean isAuthorities = false; 
		
		if(currentUserId != null) {
			isLogined = true;
			if (userService.isAdmin((int) currentUserId)) {
				isAuthorities = true;
			}
		}
		
		theModel.addAttribute("isLogined", isLogined);
		theModel.addAttribute("authorities", isAuthorities);
		

		
		// donation table model attributes
		Page<Donation> donations = donationService.findAll(page, size);

		if (!searchingValue.equals("")) {
			donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
			theModel.addAttribute("searchingValue", searchingValue);
			
			System.out.println("=======================>>>>>>> ");
			System.out.println("=======================>>>>>>> donations: " + donations);
		}
		
		System.out.println("=======================>>>>>>> 2222222222222222222");
		System.out.println("=======================>>>>>>> 2222222222222 donations: " + donations);
		System.out.println("=======================>>>>>>> 2222222222222 donations size: " + donations.getSize());
		System.out.println("=======================>>>>>>> 2222222222222 donations totalElement: " + donations.getTotalElements());
		System.out.println("=======================>>>>>>> 2222222222222 donations content: " + donations.getContent());
		
		
		
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

		
		
		// donate form model attributes
		
		
		System.out.println("=============>>>> test 4");
		theModel.addAttribute("userDonation", new UserDonation());

		// add process form link to model
		theModel.addAttribute("process", "processDonating");

		// add donationId to the model (for process form)
		Donation donation = donationService.getDonation(donationId);
		theModel.addAttribute("donationId", donationId);
		theModel.addAttribute("donationName", donation.getName());
		theModel.addAttribute("donationCode", donation.getCode());
		
		// other model attributes
		
		

		System.out.println("size: " + size);
		

		
		
		
		
		theModel.addAttribute("totalElements", donations.getTotalElements());

//		return "user/donationList";
		return "public/donation-table-test";
	}
	

	@GetMapping("donateForm")
	public String userDonationForm(HttpServletRequest request, @RequestParam("id") int donationId, Model theModel) {
		try {
			// get current user id and role
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			// if the user has not logged in yet then show login form
			if (currentUserId == null) {
				return "redirect:/v1/home";
			}
			
			Donation donation = donationService.getDonation(donationId);
			// add new UserDonation obj to the model
			theModel.addAttribute("userDonation", new UserDonation());

			// add process form link to model
			theModel.addAttribute("process", "processDonating");

			// add donationId to the model (for process form)
			theModel.addAttribute("donationId", donationId);
			theModel.addAttribute("donationName", donation.getName());
			theModel.addAttribute("donationId", donation.getCode());
			

			// return view
			return "user/donate-form";
		} catch (Exception e) {
			// log.error("UserDonationController ERROR - userDonationForm(): ", e);
			return "common/error-page";
		}
	}

	@PostMapping("/processDonating")
	public String processDonating(HttpServletRequest request, @ModelAttribute("userDonation") UserDonation userDonation,
			@RequestParam("donationId") int donationId) {
		System.out.println("=================>>>>>>>>>>>>>>>>>>>>>> in processDonating");
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			System.out.println("=================>>>>>>>>>>>>>>>>>>>>>> in processDonating: currentUserid: " +currentUserId);
			User user = userService.getUser(currentUserId);
			System.out.println("=================>>>>>>>>>>>>>>>>>>>>>> in processDonating: currentUser: " +user);
			Donation donation = donationService.getDonation(donationId);
			System.out.println("=================>>>>>>>>>>>>>>>>>>>>>> in processDonating: donation: " +donation);
			userDonation.setUser(user);
			userDonation.setDonation(donation);
			
			System.out.println("=================>>>>>>>>>>>>>>>>>>>>>> in processDonating: userDonation: " +userDonation);
			userDonationService.save(userDonation);
			return "redirect:/v1/home";
		} catch (Exception e) {
			// log.error("UserDonationController ERROR - processDonating(): ", e);
			return "common/error-page";
		}
	}

	@GetMapping("donation-detail")
	public String donationDetails(HttpServletRequest request, @RequestParam("id") int theId, Model theModel) {
		HttpSession session = request.getSession();

		Donation donation = donationService.getDonation(theId);

		theModel.addAttribute("donation", donation);
		


		Integer currentUserId = (Integer) session.getAttribute("currentUserId");
		
		

		Boolean isLogined = false;
		Boolean isAuthorities = false; 
		
		if(currentUserId != null) {
			isLogined = true;
			if (userService.isAdmin((int) currentUserId)) {
				isAuthorities = true;
			}
		}
		
		theModel.addAttribute("isLogined", isLogined);
		theModel.addAttribute("authorities", isAuthorities);
		
		
		theModel.addAttribute("userDonation", new UserDonation());

		// add process form link to model
		theModel.addAttribute("process", "processDonating");
		
		
		
		theModel.addAttribute("donationId", theId);
		theModel.addAttribute("donationName", donation.getName());
		theModel.addAttribute("donationCode", donation.getCode());
		

		return "public/donation-detail";
	}

	// logout
	@GetMapping(value = { "processLogout", "/logout" })
	public String processLogout(HttpServletRequest request, @ModelAttribute("user") User loginUser) {
		int userId = userService.getIdIfUserExisted(loginUser);

		HttpSession session = request.getSession();

		session.removeAttribute("currentUserId");
		try {

			Integer currentUserId = (Integer) session.getAttribute("currentUserId");

			System.out.println("=========================>>>>>> user id: " + currentUserId);
		} catch (Exception e) {
			System.out.println(
					"==========================================================>>>>>>>>>>>>>>>no user login currently");
		}
		System.out.println("============>>>>test");

		return "redirect:/v1/home";
	}
	
	
	@GetMapping("/myPage")
    public String myPage(Model model) {
        
		/*
		boolean showDiv = myService.shouldShowDiv(); // Assume MyService has a method to determine if the div should be shown
		*/
		
		boolean showDiv = false;
		
        model.addAttribute("showDiv", showDiv);
        return "public/test";
    }
	

}
