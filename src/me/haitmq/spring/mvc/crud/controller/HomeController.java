package me.haitmq.spring.mvc.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.content_path.ViewConstants;
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
	 * trong phương thức home cần đưa ra view: + isLogined (kiểm tra đã đăng nhập
	 * chưa) => donate form + isAdmin => manager
	 * 
	 * + các donation + current page (trang hiện tại mặc định là 1) + số entries mỗi
	 * page (current size) + searchingValue
	 * 
	 * + donate form <= donationId tương ứng
	 */
	
	/*
	 * @RequestParam(name = "errorLoginOrRegister", defaultValue = "false", required
	 * = false) boolean errorLoginOrRegister,
	 */
	@GetMapping("/home")
	public String donationlist4(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "donationId", defaultValue = "1") int donationId,
			
			Model theModel) {

		// chỉnh lại default
		// donationid????????????????????????????????????????????????????????????

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

		Page<Donation> donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page,
				size);

		theModel.addAttribute("searchingValue", searchingValue);
		
		theModel.addAttribute("donations", donations);

		theModel.addAttribute("currentPage", page);

		theModel.addAttribute("totalPage", donations.getTotalPages());

		theModel.addAttribute("currentSize", size);

		// donate form model attributes

		theModel.addAttribute("userDonation", new UserDonation());

		theModel.addAttribute("process", "processDonating");

		// add donationId to the model (for process form)
		Donation donation = donationService.getDonation(donationId);

		// ??????????????????????????????????????????????????????
		theModel.addAttribute("donation", donation);
		/*
		 * theModel.addAttribute("donationId", donationId);
		 */

		theModel.addAttribute("totalElements", donations.getTotalElements());
		
		Boolean errorLogin = false;
		
		
		if (theModel.containsAttribute("errorLoginOrRegister")) {
            errorLogin = (Boolean) theModel.getAttribute("errorLoginOrRegister");
            // Xử lý logic với errorLoginOrRegister ở đây
        }
		
		theModel.addAttribute("errorLoginOrRegister", errorLogin);
		
		
		System.out.println("?>?>?>>?>?>??>?>??>??>??>?>????> errorLoginOrRegister: " + errorLogin);
		
		if (theModel.containsAttribute("loginUser")) {
            theModel.addAttribute("loginUser", theModel.getAttribute("loginUser"));
        } else {
            theModel.addAttribute("loginUser", new LoginUser());
        }
		

		return ViewConstants.V_HOME;
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

		return ViewConstants.V_PUBLIC_DONATION_DETAILS;
	}

	// register

	@GetMapping(value = { "/registerForm", "/register" })
	public String showFormForAdd(Model theModel) {
		User newUser = new User();
		theModel.addAttribute("user", newUser);
		theModel.addAttribute("process", "processRegister");

		return ViewConstants.V_REGISTER;
	}

	@PostMapping("/processRegister")
	public String processRegister(@Valid @ModelAttribute("user") User theUser, 
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		

		if(theBindingResult.hasErrors()) {

			return ViewConstants.V_REGISTER;
		} else {
			
			userService.register(theUser);
			
			return ViewConstants.V_REDIRECT_HOME;
		}
	}

	// login
	@GetMapping("login")
	public String showLoginForm(Model theModel) {
		LoginUser theLoginUser = new LoginUser();
		theModel.addAttribute("loginUser", theLoginUser);
		theModel.addAttribute("process", "processLogin");

		return ViewConstants.V_LOGIN;
	}

	@PostMapping("processLogin")
	public String processLogin(HttpServletRequest request,
								@Valid @ModelAttribute("loginUser") LoginUser loginUser, 
								BindingResult theBindingResult,
								RedirectAttributes redirectAttributes) {
		
		HttpSession session = request.getSession();
		
		Boolean isLogined = false;
		
		Boolean isActive = false;
		
		Boolean isAdmin = false;
		
		if (theBindingResult.hasErrors()) {
	            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUser", theBindingResult);
	            redirectAttributes.addFlashAttribute("loginUser", loginUser);
				/* redirectAttributes.addFlashAttribute("errorLoginOrRegister", true); */
	            redirectAttributes.addFlashAttribute("errorLogin", true);
	            
	    } else {
	    	int userId = userService.getIdIfUserExisted(loginUser);
			
			if (userId != -1) {
				
				User user = userService.getUser(userId);

				session.setAttribute("currentUserId", userId);
				
				isLogined = true;
				
				if(userService.isActive(userId)) {
					isActive = true;
					isAdmin = userService.isAdmin(userId);
					
				}
			} 
	    }
		
		session.setAttribute("isLogined", isLogined);
		session.setAttribute("isActive", isActive);
		session.setAttribute("isAdmin", isAdmin);
		
		return ViewConstants.V_REDIRECT_HOME;
		
		
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
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			User user = userService.getUser(currentUserId);
			Donation donation = donationService.getDonation(donationId);
			userDonation.setUser(user);
			userDonation.setDonation(donation);
			userDonationService.save(userDonation);
			return ViewConstants.V_REDIRECT_HOME;
		} catch (Exception e) {
			// log.error("UserDonationController ERROR - processDonating(): ", e);
			return ViewConstants.V_ERROR;
		}
	}

	// logout
	@GetMapping(value = { "processLogout", "/logout" })
	public String processLogout(HttpServletRequest request, @ModelAttribute("user") User loginUser) {

		HttpSession session = request.getSession();

		session.removeAttribute("currentUserId");

		return ViewConstants.V_REDIRECT_HOME;
	}

}
