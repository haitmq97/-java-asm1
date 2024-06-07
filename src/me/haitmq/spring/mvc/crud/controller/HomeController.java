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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.role.UserRole;
import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.content_path.ViewConstants;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.LoginUserInfomation;
import me.haitmq.spring.mvc.crud.utils.SessionUtils;

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
	 * @RequestParam(name = "errorLoginOrRegister", defaultValue = "false", required
	 * = false) boolean errorLoginOrRegister,
	 */
	
	/*
	 *	What home need to do? 
	 * - request param:
	 * 		+ HttpServletrequest
	 * 		+ size 				\
	 *  	+ page				 } -> to get donation list data
	 *  	+ searching value	/
	 *  	+ donation id (for donate function)
	 * - handle:
	 * 		+ showing donations table (by page, size, searchingValue) also showing page, size, searchingValue
	 * 		+ check authority and add to model( for display content according authority)	 
	 * 		+ showing login form 
	 * 			+ receive attribute from login process
	 * 				+ check if there are error from previous login -> add to model error
	 * 		+ showing donate form for logined user by donationid request param
	 * 			+ can login user donate? (is user locked or donation is able to donate?)
	 * 				+ receive attribute from donate process
	 * 					+ is donate success? -> add messenge
	 * 	
	 * - return view
	 * 
	 * 
	 */
	
	@GetMapping("/home")
	public String homePage(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "donationId", defaultValue = "1") int donationId,
			Model theModel) {

		// check authority (isLogined, isAdmin) (bao gồm phần header)

		HttpSession session = request.getSession();
		Integer currentUserId = SessionUtils.getCurrentUserId(session);
		
		
		System.out.println("currrent User Id ..........................: " + currentUserId);
		
		// add to model loginUser information
		SessionUtils.addLoginUserInfoToModel(session, theModel);
		
		// showing donations table
		// 		+ get donations list table
		Page<Donation> donations = 
				donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
		// 		+ add to model
		theModel.addAttribute("donations", donations);

		theModel.addAttribute("currentPage", page);
		
		theModel.addAttribute("currentSize", size);
		
		theModel.addAttribute("searchingValue", searchingValue);
		
		theModel.addAttribute("totalPage", donations.getTotalPages());
		
		theModel.addAttribute("totalElements", donations.getTotalElements());

		// login form (import showLoginForm function on jsp page)

		// donate form model attributes
		
		// to showing infomation of donation donating
		Donation donation = donationService.getDonation(donationId);

		theModel.addAttribute("donation", donation);
		
		
		// donate handle
		// add donate obj for donate form
		theModel.addAttribute("userDonation", new UserDonation());

		theModel.addAttribute("processDonating", "processDonating");

		// check if privious donate is success then add to model to showing message
		theModel.addAttribute("successDonate", theModel.containsAttribute("successDonate") ? true : false);

				/*
		// check if there are errors from last login then add to model the logined user with error, if not add new login user
		theModel.addAttribute("loginUser", theModel.containsAttribute("loginUser")? theModel.getAttribute("loginUser"): new LoginUser());
		*/
		
		
		// for direct update donation status
		donationService.autoUpdateStatusAll();
		
		
		// set current endpoint to session (user for process method return last url)
		SessionUtils.setCurrentEndpoint(request);
		
		

		System.out.println("current page...................home: " + request.getRequestURL().toString());
		System.out.println("context path..................home:" + request.getContextPath());
		System.out.println("request uri......................home: " + request.getRequestURI());
		System.out.println("totalLink...........................home:" + request.getContextPath() + request.getRequestURI());
		System.out.println("query...........................home:" + request.getQueryString());

		
		System.out.println("page...........................home:" + page);
		System.out.println("size...........................home:" + size);
		System.out.println("searchingValue...........................home:" + searchingValue);
		return ViewConstants.V_HOME;
	}

	
	/*
	 *	What donation-detail need to do? 
	 * - request param:
	 * 		+ HttpServletRequest
	 * 		+ Donation id
	 * - handle:
	 * 		+ display donation info
	 * 		+ check authority and add to model( for display content according authority)	 
	 * 		+ showing login form (like home)
	 * 		+ showing donate form (like home)
	 * 	
	 * - return view
	 * 
	 */
	@GetMapping("donation-detail")
	public String donationDetails(HttpServletRequest request, @RequestParam("id") int theId, Model theModel) {
		
		HttpSession session = request.getSession();
		
		// add to model loginUser information
		SessionUtils.addLoginUserInfoToModel(session, theModel);
		
		// add donation info to the model
		Donation donation = donationService.getDonation(theId);

		theModel.addAttribute("donation", donation);

		// donate handle
		// add donate obj for donate form
		theModel.addAttribute("userDonation", new UserDonation());

		theModel.addAttribute("processDonating", "processDonating");

		// check if privious donate is success then add to model to showing message
		theModel.addAttribute("successDonate", theModel.containsAttribute("successDonate") ? true : false);
		/*
		 * 
		 * theModel.addAttribute("donationId", theId);
		 */
		// check if there are errors from last login then add to model the logined user
		// with error, if not add new login user
		theModel.addAttribute("loginUser",
				theModel.containsAttribute("loginUser") ? theModel.getAttribute("loginUser") : new LoginUser());

		
		System.out.println("current page...................detail: " + request.getRequestURL().toString());
		System.out.println("context path..................detail:" + request.getContextPath());
		System.out.println("request uri......................detail: " + request.getRequestURI());
		System.out.println("totalLink...........................detaildetail:" + request.getContextPath() + request.getRequestURI());
		System.out.println("query...........................details:" + request.getQueryString());
		
		SessionUtils.setCurrentEndpoint(request);

		return ViewConstants.V_PUBLIC_DONATION_DETAILS;
	}

	// register
	/*
	 *	What register need to do? 
	 * - request param:
	 * - handle:
	 * 		+ showing register form
	 * 	
	 * - return view
	 * 
	 */
	@GetMapping(value = { "/registerForm", "/register" })
	public String showFormForAdd(Model theModel) {
		User newUser = new User();
		theModel.addAttribute("user", newUser);
		theModel.addAttribute("process", "processRegister");

		return ViewConstants.V_REGISTER;
	}
	
	/*
	 *	What register need to do? 
	 * - request param:
	 * 		+ HttpServletRequest
	 * 		+ user obj
	 * 		+ BindingResult
	 * 		+ RedirectAttributes (to add success messenge)
	 * - handle:
	 * 		+ handle user register info
	 * 			+ if it has error return register form 
	 * 			+ sucess -> add user info to session and messenge success to model and return home
	 * 	
	 * - return view
	 * 
	 */
	@PostMapping("/processRegister")
	public String processRegister(HttpServletRequest request, 
			@Valid @ModelAttribute("user") User theUser, 
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		HttpSession session = request.getSession();

		if(theBindingResult.hasErrors()) {

			return ViewConstants.V_REGISTER;
		}
		
		userService.add(theUser, UserRole.USER);
		// get id user from database
		int userId = userService.getIdIfUserExisted(new LoginUser(theUser.getUserName(), theUser.getPassword()));
		
		if (userId != -1) {
			
			boolean isActive = false;
			boolean isAdmin = false;
			
			// if user is locked, consider role = user (avoid user performing admin function)
			if(userService.isActive(userId)) {
				isActive = true;
				isAdmin = userService.isAdmin(userId);
			}
			
			// set loign user info to sesion
			SessionUtils.setLoginUserInfoToSesstion(session, true, isActive, isAdmin, userId);
		}
		
		redirectAttributes.addFlashAttribute("sucessRegister", true);

		return ViewConstants.V_REDIRECT_HOME;
	}

	// login
	/*(embed it on other jsp)
	 *	What login form need to do? 
	 * - request param:
	 * 		+ HttpServletRequest
	 * - handle:
	 * 		+ display login form
	 * 			+ if there are errors from last login then add it to model
	 * 	
	 * - return view
	 * 
	 * 
	 * ................................may i addFlash the errror from login process?...........................
	 */ 
	@GetMapping(value = {"login", "login-form"})
	public String showLoginForm(HttpServletRequest request,
			Model theModel) {
		
		
		LoginUser theLoginUser = new LoginUser();
		
		Boolean errorLogin= false;
		
		// check if have errror
		if (theModel.containsAttribute("loginUser")) {
			theLoginUser = (LoginUser) theModel.getAttribute("loginUser");
			errorLogin = true;
        }
		
		
		theModel.addAttribute("loginUser", theLoginUser);
		theModel.addAttribute("process", "processLogin");
		theModel.addAttribute("errorLogin", errorLogin);
		
		System.out.println("current page...................: " + request.getRequestURL().toString());
		System.out.println("context path..................:" + request.getContextPath());
		System.out.println("request uri......................: " + request.getRequestURI());
		System.out.println("totalLink...........................:" + request.getContextPath() + request.getRequestURI());
		
		return ViewConstants.V_LOGIN;
	}
	
	/*
	 *	What loginprocess need to do? 
	 * - request param:
	 * 		+ HttpServletRequest
	 * 		+ BindingResult (check error)
	 * 		+ RedirectAttributes (add error attribute 
	 * - handle:
	 * 		+ process login data
	 * 			+ if has error add the error to model
	 * 	
	 * - return view 
	 * 		+ return the privious page
	 * 		
	 * 
	 */ 
	@PostMapping("processLogin")
	public String processLogin(HttpServletRequest request,
								@Valid @ModelAttribute("loginUser") LoginUser loginUser, 
								BindingResult theBindingResult,
								RedirectAttributes redirectAttributes
			) {
		
		HttpSession session = request.getSession();
		
		if (theBindingResult.hasErrors()) {
			// pass error data back login form
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUser", theBindingResult);
			redirectAttributes.addFlashAttribute("loginUser", loginUser);
			/* redirectAttributes.addFlashAttribute("errorLoginOrRegister", true); */
			redirectAttributes.addFlashAttribute("errorLogin", true);
	            
	    } else {
	    	int userId = userService.getIdIfUserExisted(loginUser);
			
			if (userId != -1) {
				boolean isActive = false;
				boolean isAdmin = false;
				
				// if user is locked, consider role = user (avoid user performing admin function)
				if(userService.isActive(userId)) {
					isActive = true;
					isAdmin = userService.isAdmin(userId);
				}
				
				SessionUtils.setLoginUserInfoToSesstion(session, true, isActive, isAdmin, userId);
			} 
	    }
		
		
		String currentUrl = request.getParameter("currentUrl");
		
		System.out.println("current url: ////////////////////////: " + currentUrl);
		
		/*
		return ViewConstants.V_REDIRECT_HOME;
		
		
		 */
		
		
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	// clear error for click login or donate button again
	@PostMapping("/clearErrors")
    @ResponseBody
    public String clearErrors(HttpSession session) {
        session.removeAttribute("org.springframework.validation.BindingResult.loginUser");
        session.removeAttribute("loginUser");
        session.removeAttribute("errorLogin");
        return "success";
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
	
	/*
	 *	What donateForm need to do? 
	 * - request param:
	 * 		+ HttpServletRequest
	 * 		+ userDonation obj
	 * 		+ donation id
	 * 		+ BindingResult
	 * 		+ RedirectAttributes (add error attribute or success messenge) 
	 * - handle:
	 * 		+ process login data
	 * 			+ if has error add the error to model
	 * 	
	 * - return view 
	 * 		+ return the privious page
	 * 		
	 * 
	 */ 
	@PostMapping("/processDonating")
	public String processDonating(HttpServletRequest request, 
			@ModelAttribute("userDonation") UserDonation userDonation,
			@RequestParam("donationId") int donationId,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		try {
			
			if (theBindingResult.hasErrors()) {
				// pass error data back donate form
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDonation", theBindingResult);
				
				redirectAttributes.addFlashAttribute("loginUser", userDonation);
				
				redirectAttributes.addFlashAttribute("errorDonate", true);
		    } else {
		    	HttpSession session = request.getSession();
		    	
		    	// get current user and donation obj to set attribute for userDonation
				Integer currentUserId = (Integer) session.getAttribute("currentUserId");
				
				User user = userService.getUser(currentUserId);
				
				Donation donation = donationService.getDonation(donationId);
				
				userDonation.setUser(user);
				
				userDonation.setDonation(donation);
				
				userDonationService.save(userDonation);

				redirectAttributes.addFlashAttribute("successDonate", true);
		    }
			
			
	
			
			/*
			return ViewConstants.V_REDIRECT_HOME;
			*/
			
			
			return "redirect:" + SessionUtils.getCurrentEndpoint(request);
			
		} catch (Exception e) {
			// log.error("UserDonationController ERROR - processDonating(): ", e);
			return ViewConstants.V_ERROR;
		}
	}

	// logout
	/*
	 *	What logout need to do? 
	 * - request param:
	 * 		+ HttpServletRequest
	 * - handle:
	 * 		+ remove all login user from session
	 * 	
	 * - return view 
	 * 		
	 * 
	 */ 
	@GetMapping(value = { "processLogout", "/logout" })
	public String processLogout(HttpServletRequest request) {

		HttpSession session = request.getSession();

		LoginUserInfomation.removeLoginUserInfoFromSesstion(session);

		return ViewConstants.V_REDIRECT_HOME;
	}
	

}
