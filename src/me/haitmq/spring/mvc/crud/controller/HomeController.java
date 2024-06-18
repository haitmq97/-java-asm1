package me.haitmq.spring.mvc.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;
import me.haitmq.spring.mvc.crud.common.InitDonation;
import me.haitmq.spring.mvc.crud.common.InitUser;
import me.haitmq.spring.mvc.crud.common.InitUserDonation;
import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.content_path.ViewConstants;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.BinddingResultsCustomFunction;
import me.haitmq.spring.mvc.crud.utils.SessionUtils;

// home controller 

@Controller
@RequestMapping("/v1")
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	private ModelMapper modelMapper = new ModelMapper();
	
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

		try {
			// check authority (isLogined, isAdmin) (bao gồm phần header)

			HttpSession session = request.getSession();
			
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
			
			InitUserDonation theUserDonation = new InitUserDonation();
			
			String userDonationProcess = "processDonating";
			
			if(theModel.containsAttribute("errorUserDonation")) {
				theUserDonation = (InitUserDonation) theModel.getAttribute("errorUserDonation");
				
				theModel.addAttribute("eDonationId", theModel.getAttribute("eDonationId"));
				
	            theModel.addAttribute("errorProcess",true);
	            
			}else if(theModel.containsAttribute("successDonate")) {
	        	
				theModel.addAttribute("successDonate", true);
	        	
	        } 
			
			
			theModel.addAttribute("userDonation", theUserDonation);

			theModel.addAttribute("processDonating", userDonationProcess);

			// check if privious donate is success then add to model to showing message
			

			// for direct update donation status
			donationService.autoUpdateStatusAll();
			
			
			// set current endpoint to session (user for process method return last url)
			SessionUtils.setCurrentEndpoint(request);
			
			return ViewConstants.V_HOME;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("HomeController - homePage: {}", e);
			return ViewConstants.V_ERROR;
		}	
	}
	
	
	@PostMapping("/processDonating")
	public String processDonating2(HttpServletRequest request, 
			@Valid @ModelAttribute("userDonation") InitUserDonation userDonation,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		

		
		if (theBindingResult.hasErrors()) {

			theBindingResult.getFieldErrors().forEach(error->{
				System.out.println("the Bindding resul errort: " + error);
				System.out.println("the Bindding resul errort (message: " + error.getDefaultMessage());
			});
			

			// pass error data back donate form
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDonation", theBindingResult);
			
			redirectAttributes.addFlashAttribute("errorUserDonation", userDonation);
			
			redirectAttributes.addFlashAttribute("errorDonate", true);
	    } else {
	    	HttpSession session = request.getSession();
	    	
	    	// get current user and donation obj to set attribute for userDonation
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			
			if(currentUserId!= null) {
				User user = userService.getUser(currentUserId);
				
				Donation donation = donationService.getDonation(userDonation.getDonationId());
				
				UserDonation theUserDonation = modelMapper.map(userDonation, UserDonation.class);
				theUserDonation.setUser(user);
				
				theUserDonation.setDonation(donation);
				
				userDonationService.save(theUserDonation);

				redirectAttributes.addFlashAttribute("successDonate", true);
			} else {
				throw new IllegalStateException("HomeController-processDonating: currentUserId equals null.");
			}
		
	    }
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
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
	public String donationDetails(HttpServletRequest request, @RequestParam("id") int theId,
			@RequestParam(defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {
		
		/*
		try {
			HttpSession session = request.getSession();
			
			// add to model loginUser information
			SessionUtils.addLoginUserInfoToModel(session, theModel);
			
			// add donation info to the model
			Donation theDonation = donationService.getDonation(theId);

			// check if there are errors from last login then add to model the logined user
			// with error, if not add new login user
			theModel.addAttribute("loginUser",
					theModel.containsAttribute("loginUser") ? theModel.getAttribute("loginUser") : new LoginUser());
			// get users list to display
			Page<UserDonation> userDonations = userDonationService
					.findByDonationCodeAndStatusSortByCreatedDate(theDonation.getCode(), UserDonationStatus.CONFIRMED, searchingValue, page, size);
			
			// add to model
			theModel.addAttribute("donation", theDonation);

			theModel.addAttribute("userDonations", userDonations);

			theModel.addAttribute("currentPage", page);

			theModel.addAttribute("currentSize", size);

			theModel.addAttribute("searchingValue", searchingValue);

			// donate handle
			// add donate obj for donate form
			InitUserDonation theUserDonation = new InitUserDonation();

			String userDonationProcess = "processDonating";

			if (theModel.containsAttribute("errorUserDonation")) {
				theUserDonation = (InitUserDonation) theModel.getAttribute("errorUserDonation");

				theModel.addAttribute("eDonationId", theModel.getAttribute("eDonationId"));

				theModel.addAttribute("errorProcess", true);

			} else if (theModel.containsAttribute("successDonate")) {

				theModel.addAttribute("successDonate", true);
				//theModel.addAttribute("successDonate", theModel.containsAttribute("successDonate") ? true : false);

			}

			theModel.addAttribute("userDonation", theUserDonation);

			theModel.addAttribute("processDonating", userDonationProcess);

			// check if privious donate is success then add to model to showing message
			
			
			// save current url
			SessionUtils.setCurrentEndpoint(request);

			return ViewConstants.V_PUBLIC_DONATION_DETAILS;

		} catch (Exception e) {
			log.error("HomeController - homePage: {}", e);
			return ViewConstants.V_ERROR;
		}	
		*/
		
		HttpSession session = request.getSession();
		
		// add to model loginUser information
		SessionUtils.addLoginUserInfoToModel(session, theModel);
		
		// add donation info to the model
		Donation theDonation = donationService.getDonation(theId);

		// check if there are errors from last login then add to model the logined user
		// with error, if not add new login user
		theModel.addAttribute("loginUser",
				theModel.containsAttribute("loginUser") ? theModel.getAttribute("loginUser") : new LoginUser());
		// get users list to display
		Page<UserDonation> userDonations = userDonationService
				.findByDonationCodeAndStatusSortByCreatedDate(theDonation.getCode(), UserDonationStatus.CONFIRMED, searchingValue, page, size);
		
		// add to model
		theModel.addAttribute("donation", theDonation);

		theModel.addAttribute("userDonations", userDonations);

		theModel.addAttribute("currentPage", page);

		theModel.addAttribute("currentSize", size);

		theModel.addAttribute("searchingValue", searchingValue);

		// donate handle
		// add donate obj for donate form
		InitUserDonation theUserDonation = new InitUserDonation();

		String userDonationProcess = "processDonating";

		if (theModel.containsAttribute("errorUserDonation")) {
			theUserDonation = (InitUserDonation) theModel.getAttribute("errorUserDonation");

			theModel.addAttribute("eDonationId", theModel.getAttribute("eDonationId"));

			theModel.addAttribute("errorProcess", true);

		} else if (theModel.containsAttribute("successDonate")) {

			theModel.addAttribute("successDonate", true);
			//theModel.addAttribute("successDonate", theModel.containsAttribute("successDonate") ? true : false);

		}

		theModel.addAttribute("userDonation", theUserDonation);

		theModel.addAttribute("processDonating", userDonationProcess);

		// check if privious donate is success then add to model to showing message
		
		
		// save current url
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

		try {
			InitUser newUser = new InitUser();
			
			if (theModel.containsAttribute("errorUser")) {
				
				newUser = (InitUser) theModel.getAttribute("errorUser");

	            BindingResult theBindingResult = 
	            		(BindingResult) theModel.getAttribute("org.springframework.validation.BindingResult.user");
	            theModel.addAttribute("errors", theBindingResult);

	            theModel.addAttribute("errorProcess",true);
	        }

			theModel.addAttribute("user", newUser);
			theModel.addAttribute("process", "processRegister");

			return ViewConstants.V_REGISTER;
			
		} catch (Exception e) {
			log.error("HomeController - showFormForAdd: {}", e);
			return ViewConstants.V_ERROR;
		}	
		
		
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
			@Valid @ModelAttribute("user") InitUser theUser, 
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		try {
			HttpSession session = request.getSession();
			if (BinddingResultsCustomFunction.isErrorForAddUser(theBindingResult)) {
				// get and pass invalid data to register form
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", theBindingResult);
				redirectAttributes.addFlashAttribute("errorUser", theUser);
				
				redirectAttributes.addFlashAttribute("errorForm", true);
		        
				return ViewConstants.V_REDIRECT_REGISTER_FORM;
		    }
			
			userService.add(modelMapper.map(theUser ,User.class), UserRole.USER);
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
			
		} catch (Exception e) {
			log.error("HomeController - showFormForAdd: {}", e);
			return ViewConstants.V_ERROR;
		}	
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
		
		// check if have errror
		if (theModel.containsAttribute("loginUser")) {
			theLoginUser = (LoginUser) theModel.getAttribute("loginUser");
			theModel.addAttribute("errorLogin", true);
        }
		
		theModel.addAttribute("loginUser", theLoginUser);
		theModel.addAttribute("process", "processLogin");

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
		
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
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

		SessionUtils.removeLoginUserInfoFromSesstion(session);

		return ViewConstants.V_REDIRECT_HOME;
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
}
