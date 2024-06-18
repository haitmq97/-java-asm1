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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.expr.Instanceof;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.role.UserRole;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;
import me.haitmq.spring.mvc.crud.common.InitDonation;
import me.haitmq.spring.mvc.crud.common.InitUser;
import me.haitmq.spring.mvc.crud.content_path.ViewConstants;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.BinddingResultsCustomFunction;
import me.haitmq.spring.mvc.crud.utils.CustomValidator;
import me.haitmq.spring.mvc.crud.utils.SessionUtils;
import me.haitmq.spring.mvc.crud.utils.Time;
import me.haitmq.spring.mvc.crud.validation.UniquedDonationCode;
import me.haitmq.spring.mvc.crud.validation.ValidDonationPeriod;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	
	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private UserService userService;

	@Autowired
	private UserDonationService userDonationService;

	@Autowired
	private DonationService donationService;

	// donation manager
	
	
	/*
	 *	What donations need to do? 
	 * - request param:
	 * 		+ HttpServletRequest
	 * 		+ size 				\
	 *  	+ page				 } -> to get donation list data
	 *  	+ searching value	/
	 *  	+ donation id (for add or update function)
	 * - handle:
	 * 		+ Check current user is admin, if not throw exception
	 * 		+ showing donations table (by page, size, searchingValue) also showing page, size, searchingValue	 
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
	 
	@GetMapping("/donations1")
	public String donationlist1(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "id", defaultValue = "0") int donationId, 
			Model theModel) {

		try {

			// check authority (isLogined, isAdmin) (bao gồm phần header)
			HttpSession session = request.getSession();
			
			// check current user is admin
			if(!userService.isAdmin(SessionUtils.getCurrentUserId(session))) {
				throw new IllegalStateException("AdminController-donationList: User is not an admin.");
			}
			// use for nav bar and modals
			SessionUtils.addLoginUserInfoToModel(session, theModel);
			
			
			// Donations table
			Page<Donation> donations = 
					donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
			// 		+ add to model
			theModel.addAttribute("donations", donations);

			theModel.addAttribute("currentPage", page);
			
			theModel.addAttribute("currentSize", size);
			
			theModel.addAttribute("searchingValue", searchingValue);
			
			theModel.addAttribute("totalPage", donations.getTotalPages());

			// donation model attribute
			// default is add donation
			String donationProcess = "processAddDonation";

			Donation donation = new Donation();
			
			// if donation id !=0 then it is update
			if (donationId != 0) {
				donation = donationService.getDonation(donationId);
				donationProcess = "processUpdateDonation";
			}
			
			// if there are donaion error then get donation obj and add to model 
			if (theModel.containsAttribute("donation")) {
	            donation = (Donation) theModel.getAttribute("donation");
	        }
			
			// check if the last add donation is success
			if (theModel.containsAttribute("successAdd")) {
	            theModel.addAttribute("successAdd", true);
	        } else {
	            theModel.addAttribute("successAdd", false);
	        }
			
			
			theModel.addAttribute("donation", donation);

			theModel.addAttribute("process", donationProcess);
			
			// update donation status directly
			donationService.autoUpdateStatusAll();
			
			// set current endpoint for view return in process function
			SessionUtils.setCurrentEndpoint(request);

			return ViewConstants.V_ADMIN_DONATIONS;
		} catch (Exception e) {
			return ViewConstants.V_ERROR;
		}
	}
	*/
	
	@GetMapping("/donations")
	public String donationlist(HttpServletRequest request, 
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "id", defaultValue = "0") int donationId, 
		
			Model theModel) {

		try {
			// update donation status directly
			donationService.autoUpdateStatusAll();

			// check authority (isLogined, isAdmin) (bao gồm phần header)
			HttpSession session = request.getSession();
			
			// check current user is admin
			if(!userService.isAdmin(SessionUtils.getCurrentUserId(session))) {
				throw new IllegalStateException("AdminController-donationList: User is not an admin.");
			}
			// use for nav bar and modals
			SessionUtils.addLoginUserInfoToModel(session, theModel);
			
			
			// Donations table
			Page<Donation> donations = 
					donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
			// 		+ add to model
			theModel.addAttribute("donations", donations);

			theModel.addAttribute("currentPage", page);
			
			theModel.addAttribute("currentSize", size);
			
			theModel.addAttribute("searchingValue", searchingValue);
			
			theModel.addAttribute("totalPage", donations.getTotalPages());

			// donation model attribute
			// default is add donation
			String donationProcess = "processAddDonation";

			InitDonation donation = new InitDonation();
			
			
			// if donation id !=0 then it is update
			if (donationId != 0) {
				
				donation.getPropertiesFromDonationObj(donationService.getDonation(donationId));
				donationProcess = "processUpdateDonation";
			} 
			
			// if there are donaion error then get donation obj and add to model 
			if (theModel.containsAttribute("errorDonation")) {
	            donation = (InitDonation) theModel.getAttribute("errorDonation");
	            
	            BindingResult theBindingResult = (BindingResult) theModel.getAttribute("org.springframework.validation.BindingResult.donation");
	            theModel.addAttribute("errors", theBindingResult);
	            
	            // use for after get validator error ( because when return /donations the "donationId" is 0)
	            if(donation.getId()!=0) {
	            	donationProcess = "processUpdateDonation";
	            }
	            
	            theModel.addAttribute("errorProcess",true);
	            

	        } else if(theModel.containsAttribute("successAdd")) {
	        	
	        	theModel.addAttribute("successAdd", true);
	        	
	        } else if(theModel.containsAttribute("successUpdate")) {
	        
	        	theModel.addAttribute("successUpdate", true);
	        	
	        } else if(theModel.containsAttribute("successChangeStatus")) {
	        
	        	theModel.addAttribute("successChangeStatus", true);
	        	
	        } else if(theModel.containsAttribute("successDelete")) {
	        
	        	theModel.addAttribute("successDelete", true);
	        	
	        } 

			theModel.addAttribute("donation", donation);

			theModel.addAttribute("process", donationProcess);
			
			// set current endpoint for view return in process function
			SessionUtils.setCurrentEndpoint(request);

			return ViewConstants.V_ADMIN_DONATIONS;
			
		} catch (IllegalStateException e) {

			log.error("AdminController - donationlist - NO PERMISSION: {}", e);

			return ViewConstants.V_ERROR_PERMISSION;

		} catch (Exception e) {

			log.error("AdminController - donationlist - ERROR FUNCTIONNAL: {}", e);

			return ViewConstants.V_ERROR;
		}
	}
	
	@PostMapping("/processAddDonation")
	public String processAdd(HttpServletRequest request,
			@Valid @ModelAttribute("donation") InitDonation theDonation,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (BinddingResultsCustomFunction.isErrorForAddDonation(theBindingResult)) {
			
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.donation", theBindingResult);
			redirectAttributes.addFlashAttribute("errorDonation", theDonation);
			//redirectAttributes.addFlashAttribute("errorLoginOrRegister", true);
			redirectAttributes.addFlashAttribute("errorForm", true);
			
			return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	
	    }
		
		donationService.add(modelMapper.map(theDonation,Donation.class));
    	redirectAttributes.addFlashAttribute("successAdd", true);

		
		//return "redirect:" + SessionUtils.getCurrentEndpoint(request);
		
		//return ViewConstants.V_REDIRECT_ADMIN_DONATIONS;
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	
	@PostMapping("/processUpdateDonation")
	public String processUpdate(HttpServletRequest request, 
			@Valid @ModelAttribute("donation") InitDonation theDonation,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if(BinddingResultsCustomFunction.isErrorForUpdateDonation(theBindingResult, donationService, theDonation)) {
			
			// check if code, startdate, enddate change is chnage or not
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.donation",
					theBindingResult);

			redirectAttributes.addFlashAttribute("errorDonation", theDonation);

			redirectAttributes.addFlashAttribute("errorForm", true);
			
			return "redirect:" + SessionUtils.getCurrentEndpoint(request);
		}
		
		donationService.update(theDonation.copyPropertiesToDonationObj(donationService.getDonation(theDonation.getId())));
		redirectAttributes.addFlashAttribute("successUpdate", true);

		//return ViewConstants.V_REDIRECT_ADMIN_DONATIONS;
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	
	/*
	 * What donations need to do? - request param: + HttpServletRequest + donation
	 * id (for display detail) + page + size + searchingValue - handle: + check
	 * authority + show donation detail + show userDonatoin List (pageable with
	 * confirm btn) ------------------------------------- +
	 * 
	 * - return view
	 * 
	 * 
	 */
	@GetMapping("donation-detail")
	public String donationDetails(HttpServletRequest request, @RequestParam("id") int theId,
			@RequestParam(defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "userDonationId", defaultValue = "0") int userDonationId,
			Model theModel) {

		try {
			
			// check authority (isLogined, isAdmin) (bao gồm phần header)
			HttpSession session = request.getSession();

			// check current user is admin
			if (!userService.isAdmin(SessionUtils.getCurrentUserId(session))) {
				throw new IllegalStateException("AdminController-donationDetail: User is not an admin.");
			}
			// use for nav bar and modals
			SessionUtils.addLoginUserInfoToModel(session, theModel);

			// get the donation for display detail
			Donation theDonation = donationService.getDonation(theId);

			Page<UserDonation> userDonations = userDonationService
					.findByDonationCodeSortByCreatedDate(theDonation.getCode(), searchingValue, page, size);
			
			
			donationService.setTotalConfirmedDonate(theId);
			
			// add to model
			theModel.addAttribute("donation", theDonation);

			theModel.addAttribute("userDonations", userDonations);

			theModel.addAttribute("currentPage", page);

			theModel.addAttribute("currentSize", size);

			theModel.addAttribute("searchingValue", searchingValue);
			
			// for cancel userDonation model
			UserDonation userDonation = new UserDonation();
			
			// if donation id !=0 then it is update
			if (userDonationId != 0) {
				userDonation = userDonationService.getUserDonation(userDonationId);
				
			} 

			theModel.addAttribute("userDonation", userDonation);

			SessionUtils.setCurrentEndpoint(request);
			return ViewConstants.V_ADMIN_DONATION_DETAIL;
			
		} catch (IllegalStateException e) {

			log.error("AdminController - donationlist - NO PERMISSION: {}", e);

			return ViewConstants.V_ERROR_PERMISSION;

		} catch (Exception e) {

			log.error("AdminController - donationlist - ERROR FUNCTIONNAL: {}", e);

			return ViewConstants.V_ERROR;
		}
	}
	
	
	// no need
	@GetMapping("/updateDonation")
	public String updateDonation(@RequestParam("id") int donationId, Model theModel) {
		Donation donation = donationService.getDonation(donationId);

		theModel.addAttribute("process", "processUpdate");

		theModel.addAttribute("donation", donation);

		return "admin/donation-form";
	}
	
	
	// ? -------------------------------------------------------------------
	@GetMapping("/updateDonationStatus")
	public String updateDonationStatus(HttpServletRequest request,
			@RequestParam("id") int donationId,
			@RequestParam(name = "status") DonationStatus status,
			RedirectAttributes redirectAttributes) {

		donationService.changeDonationStatus(status, donationId);
		
		redirectAttributes.addFlashAttribute("successChangeStatus", true);
		//return ViewConstants.V_REDIRECT_ADMIN_DONATIONS;
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	
	// ? -------------------------------------------------------------------
	/*
	@PostMapping("/processUpdateDonation")
	public String processUpdate(HttpServletRequest request, @ModelAttribute("donation") Donation theDonation) {

		donationService.update(theDonation);

		return ViewConstants.V_ADMIN_DONATIONS;
	}
	*/
	
	/*
	@PostMapping("/processUpdateDonation")
	public String processUpdate(HttpServletRequest request, 
			@Valid @ModelAttribute("donation") InitDonation theDonation,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		System.out.println("....................................in admincontroller- processUpdateDonation the id: " + theDonation.getId());
		
		Donation theExistingDonation = donationService.getDonation(theDonation.getId());
		if(theExistingDonation!= null) {
			boolean codeChanged = !theDonation.getCode().equals(theExistingDonation.getCode());
	        boolean startDateChanged = !theDonation.getStartDate().equals(theExistingDonation.getStartDate());
	        boolean endDateChanged = !theDonation.getEndDate().equals(theExistingDonation.getEndDate());

		}
		
		if(theBindingResult.hasErrors()) {
			
			theBindingResult.getFieldErrors().forEach(fieldError ->  {
				log.error("AdminController - processAdd - error field (validator) {}", fieldError);
				log.error("AdminController - processAdd - error field (validator) {}", fieldError.getDefaultMessage());
			});
			theBindingResult.getGlobalErrors().forEach(globalError ->  {
				log.error("AdminController - processAdd - error field (validator) {}", globalError);
				System.out.println("test123................is global error from @UniquedDonationCode:" + globalError.getCode().equals("UniquedDonationCode"));
				log.error("AdminController - processAdd - error field (validator) {}", globalError.getDefaultMessage());
			});
			
			
			// check if code, startdate, enddate change is chnage or not
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.donation", theBindingResult);
			
			redirectAttributes.addFlashAttribute("errorDonation", theDonation);
			
			redirectAttributes.addFlashAttribute("errorForm", true);
			
			
			return "redirect:" + SessionUtils.getCurrentEndpoint(request);
		}
		
		donationService.update(theDonation.copyPropertiesToDonationObj(donationService.getDonation(theDonation.getId())));
		redirectAttributes.addFlashAttribute("successProcess", true);

		//return ViewConstants.V_REDIRECT_ADMIN_DONATIONS;
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	*/
	
	
	
	
	
	
	@GetMapping("/deleteDonation")
	public String delete(HttpServletRequest request, 
			@RequestParam("id") int donationId,
			RedirectAttributes redirectAttributes) {

		donationService.changeDonationShowingStatus(donationId);
		
		redirectAttributes.addFlashAttribute("successDelete", true);
		
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
		//return ViewConstants.V_REDIRECT_ADMIN_DONATIONS;

	}

	@GetMapping("/addDonation")
	public String addDonation(Model theModel) {
		Donation donation = new Donation();

		theModel.addAttribute("process", "processAdd");

		theModel.addAttribute("donation", donation);

		return "admin/donation-form-for-add";
	}
	
	/*
	@PostMapping("/processAddDonation")
	public String processAdd(HttpServletRequest request, @ModelAttribute("donation") Donation theDonation,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (theBindingResult.hasErrors()) {
			// truyền dữ liệu lỗi mà người dùng đăng nhập về lại trang đăng nhập
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.donation", theBindingResult);
			redirectAttributes.addFlashAttribute("donation", theDonation);
			//redirectAttributes.addFlashAttribute("errorLoginOrRegister", true); 
			redirectAttributes.addFlashAttribute("errorForm", true);
	            
	    } else {
	    	donationService.add(theDonation);
	    	redirectAttributes.addFlashAttribute("successAdd", true);
	    }
		
		

		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	*/
	
	
	
	

	// user manager
	
	
	

	@GetMapping("/users")
	public String userList(HttpServletRequest request, 
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "id", defaultValue = "0") int userId, Model theModel) {
		
		try {
			
			HttpSession session = request.getSession();
			
			// check current user is admin
			if(!userService.isAdmin(SessionUtils.getCurrentUserId(session))) {
				throw new IllegalStateException("AdminController-donationList: User is not an admin.");
			}
			// use for nav bar and modals
			SessionUtils.addLoginUserInfoToModel(session, theModel);
			
			// user list

			Page<User> users = userService.findByEmailOrUserNameOrPhoneNumberOrStatus(page, size,searchingValue);

			theModel.addAttribute("users", users);
			
			theModel.addAttribute("currentPage", page);
			
			theModel.addAttribute("currentSize", size);
			
			theModel.addAttribute("searchingValue", searchingValue);

			theModel.addAttribute("totalPage", users.getTotalPages());

			// user model attribute

			// add or update
			
			String userProcess = "processAddUser";

			InitUser user = new InitUser();
			
			
			// if donation id !=0 then it is update
			if (userId != 0) {
				
				user.getPropertiesFromDonationObj(userService.getUser(userId));
				userProcess = "processUpdateUser";
			} 
			
			// if there are donaion error then get donation obj and add to model 
			if (theModel.containsAttribute("errorUser")) {
				
				user = (InitUser) theModel.getAttribute("errorUser");

	            BindingResult theBindingResult = (BindingResult) theModel.getAttribute("org.springframework.validation.BindingResult.user");
	            theModel.addAttribute("errors", theBindingResult);
	            
	            // use for after get validator error ( because when return /donations the "donationId" is 0)
	            if(user.getId()!=0) {
	            	userProcess = "processUpdateUser";
	            }
				
	            theModel.addAttribute("errorProcess",true);
	            
	        } else if(theModel.containsAttribute("successAdd")) {
	        	
	        	theModel.addAttribute("successAdd", true);
	        	
	        } else if(theModel.containsAttribute("successUpdate")) {
	        
	        	theModel.addAttribute("successUpdate", true);
	        	
	        } else if(theModel.containsAttribute("successChangeStatus")) {
	        
	        	theModel.addAttribute("successChangeStatus", true);
	        	
	        } else if(theModel.containsAttribute("successDelete")) {
	        
	        	theModel.addAttribute("successDelete", true);
	        	
	        } 
			
			theModel.addAttribute("user", user);

			theModel.addAttribute("process", userProcess);

			// set current endpoint for view return in process function
			SessionUtils.setCurrentEndpoint(request);

			return ViewConstants.V_ADMIN_USERS;
			
		} catch (IllegalStateException e) {
			
			log.error("Admincontroller - userList - NO PERMISSION: {}", e);
			
			return ViewConstants.V_ERROR_PERMISSION;
			
		} catch (Exception e) {
			
			log.error("Admincontroller - userList - ERROR FUNCTIONNAL: {}", e);
			
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
	public String processAdd(HttpServletRequest request,
			@Valid @ModelAttribute("user") InitUser theUser,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (BinddingResultsCustomFunction.isErrorForAddUser(theBindingResult)) {
			// truyền dữ liệu lỗi mà người dùng đăng nhập về lại trang đăng nhập
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", theBindingResult);
			redirectAttributes.addFlashAttribute("errorUser", theUser);
			/* redirectAttributes.addFlashAttribute("errorLoginOrRegister", true); */
			redirectAttributes.addFlashAttribute("errorForm", true);
	            
	    } else {
	    	
	    	userService.add(modelMapper.map(theUser ,User.class), UserRole.ADMIN);
	    	redirectAttributes.addFlashAttribute("successAdd", true);
	    }
		
		
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	
	
	
	@PostMapping("/processUpdateUser")
	public String processUpdate(HttpServletRequest request, 
			@Valid @ModelAttribute("user") InitUser theUser,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if(BinddingResultsCustomFunction.isErrorForUpdateUser(theBindingResult, userService, theUser)) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", theBindingResult);
			
			redirectAttributes.addFlashAttribute("errorUser", theUser);
			
			redirectAttributes.addFlashAttribute("errorForm", true);
			
		} else {
	    	
	    	userService.update(theUser.copyPropertiesToUserObj(userService.getUser(theUser.getId())), UserRole.ADMIN);
			redirectAttributes.addFlashAttribute("successUpdate", true);
	    }

		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	


	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int userId, Model theModel) {
		User theUser = userService.getUser(userId);

		theModel.addAttribute("process", "processUpdate");

		theModel.addAttribute("user", theUser);

		return "admin/user-form";
	}


	
	
	

	@GetMapping("/updateUserStatus")
	public String updateUserStatus(HttpServletRequest request,
			@RequestParam("id") int userId, 
			@RequestParam(name = "status") UserStatus status,
			RedirectAttributes redirectAttributes) {

		userService.changeUserStatus(status, userId);
		
		redirectAttributes.addFlashAttribute("successChangeStatus", true);
		
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	

	

	
	
	@GetMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request, @RequestParam("id") int theId,
			@RequestParam(name = "currentUrl", defaultValue = "/admin/users") String currentUrl,
			RedirectAttributes redirectAttributes) {
		userService.changeUserShowingStatus(theId);
		
		redirectAttributes.addFlashAttribute("successDelete", true);
		
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
	

	@GetMapping("/user-detail")
	public String userDetail(HttpServletRequest request, @RequestParam("id") int theId,
			@RequestParam(defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			@RequestParam(name = "userDonationId", defaultValue = "0") int userDonationId,
			Model theModel) {

		HttpSession session = request.getSession();

		// check current user is admin
		if (!userService.isAdmin(SessionUtils.getCurrentUserId(session))) {
			throw new IllegalStateException("AdminController-donationDetail: User is not an admin.");
		}

		SessionUtils.addLoginUserInfoToModel(session, theModel);

		// get the user for display detail
		User theUser = userService.getUser(theId);

		Page<UserDonation> userDonations = userDonationService.findByUserNameSortByCreatedDate(theUser.getUserName(),
				searchingValue, page, size);


		// add to model
		theModel.addAttribute("user", theUser);

		theModel.addAttribute("userDonations", userDonations);

		theModel.addAttribute("currentPage", page);

		theModel.addAttribute("currentSize", size);

		theModel.addAttribute("searchingValue", searchingValue);

		UserDonation userDonation = new UserDonation();

		// if donation id !=0 then it is update
		if (userDonationId != 0) {
			userDonation = userDonationService.getUserDonation(userDonationId);

		}

		theModel.addAttribute("userDonation", userDonation);
		
		System.out.println(".............dsfdsf.................. test(userDonatitonId): " + userDonationId );

		SessionUtils.setCurrentEndpoint(request);

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
			
			SessionUtils.addLoginUserInfoToModel(session, theModel);
			
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
		
		if(status==UserDonationStatus.CONFIRMED) {
			donationService.setTotalConfirmedDonate(userDonationService.getDonation(userdonationId).getId());
		}
		userDonationService.changeUserDonationStatus(userdonationId, status);

		return "redirect:" + SessionUtils.getCurrentEndpoint(request);

	}


	@GetMapping("/deleteUserDonation")
	public String deleteUserDonation(HttpServletRequest request,
			@RequestParam("userDonationId") int userDonationId) {

		userDonationService.changeUserDonationStatus(userDonationId, UserDonationStatus.CANCELED);
		
		System.out.println("//////////////////////in deleteUserDonation url: " + SessionUtils.getCurrentEndpoint(request));
		return "redirect:" + SessionUtils.getCurrentEndpoint(request);

	}

}
