package me.haitmq.spring.mvc.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.aop.ThrowsAdvice;
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

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private DonateService donateService;

	@Autowired
	private DonationService donationService;



	@GetMapping("/manager")
	public String managerPage() {

		return "admin/manager";
	}

	@GetMapping("/manager3")
	public String managerPage3(
			@RequestParam(name = "tableOut", defaultValue = "donations", required = false) String tableOut,
			Model theModel) {
		System.out.println("============>" + tableOut);
		System.out.println("hello");
		if (tableOut.equals("donations")) {
			theModel.addAttribute("tableC", "/admin/donations");
		} else if (tableOut.equals("users")) {
			theModel.addAttribute("tableC", "/admin/users");
		} else {
			theModel.addAttribute("tableC", "/admin/donates");
		}

		return "admin/manager2";
	}

	@GetMapping("/donations")
	public String donationlist(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			if (currentUserId == null) {
				throw new RuntimeException("the error");
			}

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

//			return "user/donationList";
			return "admin/donation-table";
		} catch (Exception e) {
			return "common/error-page";
		}

	}

	

	/*
	 * @GetMapping("/donations2") public String
	 * donationlist2(@RequestParam(defaultValue = "1") int page,
	 * 
	 * @RequestParam(name = "size", defaultValue = "5") int size,
	 * 
	 * @RequestParam(name = "searchingValue", defaultValue = "", required = false)
	 * String searchingValue, Model theModel) {
	 * 
	 * System.out.println("size: " + size); Page<Donation> donations =
	 * donationService.findAll(page, size);
	 * 
	 * if (!searchingValue.equals("")) { donations =
	 * donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue,
	 * page, size); theModel.addAttribute("searchingValue", searchingValue); }
	 * 
	 * theModel.addAttribute("donations", donations);
	 * 
	 * theModel.addAttribute("currentPage", page);
	 * theModel.addAttribute("totalPage", donations.getTotalPages());
	 * 
	 * theModel.addAttribute("currentSize", size);
	 * 
	 * int nextPage = page + 1; int prevPage = page - 1;
	 * 
	 * if (page <= 1) { prevPage = 1; }
	 * 
	 * theModel.addAttribute("prevPage", prevPage);
	 * System.out.println("current page" + page); System.out.println("total page: "
	 * + donations.getTotalPages()); if (page >= (donations.getTotalPages())) {
	 * nextPage = donations.getTotalPages(); }
	 * 
	 * theModel.addAttribute("nextPage", nextPage);
	 * 
	 * // return "user/donationList"; return "admin/donation-table2"; }
	 */
	
	
	
	
	@GetMapping("/donations2")
	public String donationlist2(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue, 
			@RequestParam(name="id", defaultValue = "1") int donationId,
			Model theModel) {
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			if (currentUserId == null) {
				throw new RuntimeException("the error");
			}

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
			
			
			
			Donation donation = donationService.getDonation(donationId);
			
			theModel.addAttribute("process", "processUpdate2");
			
			theModel.addAttribute("donation", donation);

//			return "user/donationList";
			return "admin/donation-table2";
		} catch (Exception e) {
			return "common/error-page";
		}

	}
	
	

	
	@PostMapping("/processUpdate2")
	public String processUpdate2(@ModelAttribute("donation") Donation theDonation) {
		
 		donationService.saveOrUpdate(theDonation);
		

		return "redirect:/admin/donations2";
	}
	
	
	

	
	@GetMapping("/updateDonation")
	public String updateDonation(@RequestParam("id") int donationId, Model  theModel) {
		Donation donation = donationService.getDonation(donationId);
		
		theModel.addAttribute("process", "processUpdate");
		
		theModel.addAttribute("donation", donation);

		return "admin/donation-form";
	}
	
	
	@PostMapping("/processUpdate")
	public String processUpdate(@ModelAttribute("donation") Donation theDonation) {
		
 		donationService.saveOrUpdate(theDonation);
		

		return "redirect:/admin/donations2";
	}
	
	
	
	@GetMapping("/addDonation")
	public String addDonation(Model theModel) {
		Donation donation = new Donation();
		
		theModel.addAttribute("process", "processAdd");
		
		theModel.addAttribute("donation", donation);

		return "admin/donation-form-for-add";
	}
	
	@PostMapping("/processAdd")
	public String processAdd(@ModelAttribute("donation") Donation theDonation) {
		
 		donationService.saveOrUpdate(theDonation);
		

		return "redirect:/admin/donations2";
	}
	
	
	
	
	@GetMapping("/deleteDonation")
	public String delete(@RequestParam("id") int donationId) {
		donationService.delete(donationId);
		
		return "redirect:/admin/donations2";
		
	}
	
	
	
	///////////////// user
	
	
	@GetMapping("/users")
	public String userList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name="id", defaultValue = "1") int userId,
			Model theModel) {
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			if (currentUserId == null) {
				throw new RuntimeException("the error");
			}

			System.out.println("size: " + size);

			Page<User> users = userService.findByEmailOrPhoneNumberOrStatus(searchingValue, page, size);
			theModel.addAttribute("searchingValue", searchingValue);

			theModel.addAttribute("users", users);

			theModel.addAttribute("currentPage", page);
			theModel.addAttribute("totalPage", users.getTotalPages());

			theModel.addAttribute("currentSize", size);

			int nextPage = page + 1;
			int prevPage = page - 1;

			if (page <= 1) {
				prevPage = 1;
			}

			theModel.addAttribute("prevPage", prevPage);
			System.out.println("current page" + page);
			System.out.println("total page: " + users.getTotalPages());
			if (page >= (users.getTotalPages())) {
				nextPage = users.getTotalPages();
			}

			theModel.addAttribute("nextPage", nextPage);
			
			User theUser = userService.getUser(userId);
			
			theModel.addAttribute("process", "processUserUpdate");
			
			theModel.addAttribute("user", theUser);

//		return "user/donationList";
			return "admin/user-table";
		} catch (Exception e) {
			return "common/error-page";
		}
	}

	
	
	@GetMapping("/addUser")
	public String addUser(Model theModel) {
		User user = new User();
		
		theModel.addAttribute("process", "processUserAdd");
		
		theModel.addAttribute("user", user);

		return "admin/user-form-for-add";
	}
	
	@PostMapping("/processUserAdd")
	public String processAdd(@ModelAttribute("user") User theUser) {
		
 		userService.saveOrUpdate(theUser);
		

		return "redirect:/admin/users";
	}
	
	
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int userId, Model theModel) {
		User theUser = userService.getUser(userId);
		
		theModel.addAttribute("process", "processUpdate");
		
		theModel.addAttribute("user", theUser);
		
		return "admin/user-form";
	}
	
	@PostMapping("/processUserUpdate")
	public String processUpdate(@ModelAttribute("user") User theUser) {
		
		
		
 		userService.saveOrUpdate(theUser);
		
		return "redirect:/admin/users";
	}
	
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") int theId) {
		userService.deleteUser(theId);

		return "redirect:/admin/users";
	}
	
	
	@GetMapping("/userDetails")
	public String userDetail(@RequestParam("id") int userId, Model theModel) {
		User user = userService.getUser(userId);
 		
		theModel.addAttribute("user", user);

		return "admin/user-detail";
	}
	
	
	
	////// donate
	
	@GetMapping("/donates")
	public String donateList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {

		try {
			
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			if (currentUserId == null) {
				throw new RuntimeException("the error");
			}
			

			Page<Donate> donates = donateService.findAll(page, size);

			if (!searchingValue.equals("")) {
				donates = donateService.findAllSortByStatusByCreatedDate(page, size);
				theModel.addAttribute("searchingValue", searchingValue);
			}

			for (Donate donate : donates) {
				System.out.println(donate);
			}

			theModel.addAttribute("donates", donates);

			theModel.addAttribute("currentPage", page);
			theModel.addAttribute("totalPage", donates.getTotalPages());

			theModel.addAttribute("currentSize", size);

			int nextPage = page + 1;
			int prevPage = page - 1;

			if (page <= 1) {
				prevPage = 1;
			}

			theModel.addAttribute("prevPage", prevPage);
			System.out.println("current page" + page);
			System.out.println("total page: " + donates.getTotalPages());
			if (page >= (donates.getTotalPages())) {
				nextPage = donates.getTotalPages();
			}

			theModel.addAttribute("nextPage", nextPage);

//			return "user/donationList";

			return "admin/donate-table";
		} catch (Exception e) {
			// log.error("DonateController ERROR - list(): ", e);
			return "common/error-page";
		}
	}
	
	@GetMapping("donateStatusComfirm")
	public String statusProcessing(@RequestParam("id")int theId) {
		donateService.donateComfirm(theId);
		return "redirect:/admin/donates";
	}
	

	@GetMapping("/deleteDonate")
	public String deleteDonate(@RequestParam("id") int theId) {
		donateService.delete(theId);
	
	
	
		return "redirect:/admin/donates";
	}
	/*
	@GetMapping("/deleteDonate")
	public String deleteDonate(@RequestParam("id") int theId) {
		donateService.changeDonateShowingStatus(theId);
	
		return "redirect:/admin/donates";
	}
	 */
	
}
