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
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;
import me.haitmq.spring.mvc.crud.content_path.ViewConstants;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDonationService userDonationService;

	@Autowired
	private DonationService donationService;

	// donation manager
	@GetMapping("/donations")
	public String donationlist(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "id", defaultValue = "0") int donationId, Model theModel) {

		try {

			// kiểm tra quyền (isLogined, isAdmin) (bao gồm phần header)

			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");

			Boolean isLogined = false;

			Boolean isAdmin = false;

			if (currentUserId != null) {
				isLogined = true;
				isAdmin = userService.isAdmin(currentUserId);
			} else {
				throw new RuntimeException("the error: not admin");
			}

			theModel.addAttribute("isLogined", isLogined);

			theModel.addAttribute("isAdmin", isAdmin);

			// Donations table
			Page<Donation> donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue,
					page, size);

			theModel.addAttribute("searchingValue", searchingValue);

			theModel.addAttribute("donations", donations);

			theModel.addAttribute("currentPage", page);

			theModel.addAttribute("totalPage", donations.getTotalPages());

			theModel.addAttribute("currentSize", size);

			// donation model attribute

			// add or update
			String donationProcess = "processAddDonation";

			Donation donation = new Donation();

			if (donationId != 0) {
				donation = donationService.getDonation(donationId);
				donationProcess = "processUpdateDonation";
			}

			theModel.addAttribute("donation", donation);

			theModel.addAttribute("process", donationProcess);

			return ViewConstants.V_ADMIN_DONATIONS;
		} catch (Exception e) {
			return ViewConstants.V_ERROR;
		}

	}

	@GetMapping("donation-detail")
	public String donationDetails(HttpServletRequest request, @RequestParam("id") int theId, Model theModel) {

		// kiểm tra quyền (isLogined, isAdmin) (bao gồm phần header)

		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");

		Boolean isLogined = false;

		Boolean isAdmin = false;

		if (currentUserId != null) {
			isLogined = true;
			isAdmin = userService.isAdmin(currentUserId);
		}

		theModel.addAttribute("isLogined", isLogined);

		theModel.addAttribute("isAdmin", isAdmin);

		// donation model attribute

		Donation donation = donationService.getDonation(theId);

		theModel.addAttribute("donation", donation);

		// donate form model attributes

		theModel.addAttribute("userDonation", new UserDonation());

		theModel.addAttribute("process", "processDonating");

		theModel.addAttribute("donationId", theId);

		return ViewConstants.V_ADMIN_DONATION_DETAIL;
	}

	@GetMapping("/updateDonation")
	public String updateDonation(@RequestParam("id") int donationId, Model theModel) {
		Donation donation = donationService.getDonation(donationId);

		theModel.addAttribute("process", "processUpdate");

		theModel.addAttribute("donation", donation);

		return "admin/donation-form";
	}

	@GetMapping("/updateDonationStatus")
	public String updateDonationStatus(@RequestParam("id") int donationId,
			@RequestParam(name = "status") DonationStatus status, Model theModel) {

		donationService.changeDonationStatus(status, donationId);

		return ViewConstants.V_REDIRECT_ADMIN_DONATIONS;
	}

	@PostMapping("/processUpdateDonation")
	public String processUpdate(@ModelAttribute("donation") Donation theDonation) {

		donationService.saveOrUpdate(theDonation);

		return ViewConstants.V_ADMIN_DONATIONS;
	}

	@GetMapping("/deleteDonation")
	public String delete(@RequestParam("id") int donationId,
			@RequestParam(name = "currentUrl", defaultValue = "/admin/donations") String currentUrl) {

		donationService.changeDonationShowingStatus(donationId);
		return "redirect:" + currentUrl;

	}

	@GetMapping("/addDonation")
	public String addDonation(Model theModel) {
		Donation donation = new Donation();

		theModel.addAttribute("process", "processAdd");

		theModel.addAttribute("donation", donation);

		return "admin/donation-form-for-add";
	}

	@PostMapping("/processAddDonation")
	public String processAdd(@ModelAttribute("donation") Donation theDonation) {

		donationService.saveOrUpdate(theDonation);

		return "redirect:/admin/donations";
	}

	// user manager

	@GetMapping("/users")
	public String userList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "id", defaultValue = "0") int userId, Model theModel) {
		try {
			// kiểm tra quyền (isLogined, isAdmin) (bao gồm phần header)

			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");

			Boolean isLogined = false;

			Boolean isAdmin = false;

			if (currentUserId != null) {
				isLogined = true;
				isAdmin = userService.isAdmin(currentUserId);
			}

			theModel.addAttribute("isLogined", isLogined);

			theModel.addAttribute("isAdmin", isAdmin);

			// user list

			Page<User> users = userService.findByEmailOrPhoneNumberOrStatus(searchingValue, page, size);

			theModel.addAttribute("searchingValue", searchingValue);

			theModel.addAttribute("users", users);

			theModel.addAttribute("currentPage", page);

			theModel.addAttribute("totalPage", users.getTotalPages());

			theModel.addAttribute("currentSize", size);

			// user model attribute

			// add or update
			String userProcess = "processAddUser";

			User user = new User();

			if (userId != 0) {
				user = userService.getUser(userId);
				userProcess = "processUpdateUser";
			}

			theModel.addAttribute("user", user);

			theModel.addAttribute("process", userProcess);

			return ViewConstants.V_ADMIN_USERS;
		} catch (Exception e) {
			return ViewConstants.V_ERROR;
		}
	}

	@GetMapping("/addUser")
	public String addUser(Model theModel) {
		User user = new User();

		theModel.addAttribute("process", "processUserAdd");

		theModel.addAttribute("user", user);

		return "admin/user-form-for-add";
	}

	@PostMapping("/processAddUser")
	public String processAdd(@ModelAttribute("user") User theUser) {

		userService.saveOrUpdate(theUser);

		return ViewConstants.V_ADMIN_USERS;
	}

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int userId, Model theModel) {
		User theUser = userService.getUser(userId);

		theModel.addAttribute("process", "processUpdate");

		theModel.addAttribute("user", theUser);

		return "admin/user-form";
	}

	@PostMapping("/processUpdateUser")
	public String processUpdate(@ModelAttribute("user") User theUser) {

		userService.saveOrUpdate(theUser);

		return ViewConstants.V_REDIRECT_ADMIN_USERS;
	}

	@GetMapping("/updateUserStatus")
	public String updateUserStatus(@RequestParam("id") int userId, @RequestParam(name = "status") UserStatus status,
			Model theModel) {

		userService.changeUserStatus(status, userId);

		return ViewConstants.V_REDIRECT_ADMIN_USERS;
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") int theId) {
		userService.deleteUser(theId);

		return ViewConstants.V_REDIRECT_ADMIN_USERS;
	}

	@GetMapping("/user-detail")
	public String userDetail(HttpServletRequest request, @RequestParam("id") int theId, Model theModel) {

		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");

		Boolean isLogined = false;

		Boolean isAdmin = false;

		if (currentUserId != null) {
			isLogined = true;
			isAdmin = userService.isAdmin(currentUserId);
		}

		theModel.addAttribute("isLogined", isLogined);

		theModel.addAttribute("isAdmin", isAdmin);

		User user = userService.getUser(theId);

		theModel.addAttribute("user", user);

		return ViewConstants.V_ADMIN_USER_DETAIL;
	}

	// userDonation manager

	@GetMapping("/user_donations")
	public String userDonationlist(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "id", defaultValue = "0") int userdonationId, Model theModel) {

		try {

			// kiểm tra quyền (isLogined, isAdmin) (bao gồm phần header)

			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");

			Boolean isLogined = false;

			Boolean isAdmin = false;

			if (currentUserId != null) {
				isLogined = true;
				isAdmin = userService.isAdmin(currentUserId);
			} else {
				throw new RuntimeException("the error: not admin");
			}

			theModel.addAttribute("isLogined", isLogined);

			theModel.addAttribute("isAdmin", isAdmin);

			// userDonations table
			Page<UserDonation> userDonations = userDonationService
					.findByUserNameOrDonationCodeSortByStatusByCreatedDate(searchingValue, page, size);

			theModel.addAttribute("searchingValue", searchingValue);

			theModel.addAttribute("userDonations", userDonations);

			theModel.addAttribute("currentPage", page);

			theModel.addAttribute("totalPage", userDonations.getTotalPages());

			theModel.addAttribute("currentSize", size);

			UserDonation userDonation = new UserDonation();

			if (userdonationId != 0) {
				userDonation = userDonationService.getUserDonation(userdonationId);
			}

			theModel.addAttribute("userDonation", userDonation);

			return ViewConstants.V_ADMIN_USERDONATION;
		} catch (Exception e) {
			return ViewConstants.V_ERROR;
		}

	}

	@GetMapping("/update_user_donations")
	public String updateUserDonationStatus(HttpServletRequest request,
			@RequestParam(name = "status") UserDonationStatus status,
			@RequestParam(name = "id", defaultValue = "0") int userdonationId) {

		userDonationService.changeUserDonationStatus(userdonationId, status);

		return ViewConstants.V_REDIRECT_ADMIN_USERDONATIONS;

	}


	@GetMapping("/deleteUserDonation")
	public String deleteUserDonation(@RequestParam("id") int userDonationId,
			@RequestParam(name = "currentUrl", defaultValue = "/admin/user_donations") String currentUrl) {

		userDonationService.changeUserDonationStatus(userDonationId, UserDonationStatus.CANCELED);
		return "redirect:" + currentUrl;

	}

}
