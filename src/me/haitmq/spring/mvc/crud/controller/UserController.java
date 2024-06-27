package me.haitmq.spring.mvc.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import me.haitmq.spring.mvc.crud.common.InitUser;
import me.haitmq.spring.mvc.crud.content_path.ViewConstants;

import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.role.UserRole;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.BinddingResultsCustomFunction;
import me.haitmq.spring.mvc.crud.utils.SessionUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserDonationService userDonationService;


	@GetMapping("/profile")
	public String userProfile(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {

		try {
			// kiểm tra quyền (isLogined, isAdmin) (bao gồm phần header)

			HttpSession session = request.getSession();

			Integer currentUserId = SessionUtils.getCurrentUserId(session);

			// check current user is login
			if (currentUserId == null) {
				throw new IllegalStateException("UserController-profile: User is not login.");
			}

			SessionUtils.addLoginUserInfoToModel(session, theModel);

			// get current user to add to the model
			User currentUser = userService.getUser(currentUserId);
			
			// create an update user (initUser) for update model
			//boolean errorProcess=false;
			
			InitUser updateUser = new InitUser();
			
			updateUser.getPropertiesFromUserObj(userService.getUser(currentUserId));
			
			// check if the last update is success
			if (theModel.containsAttribute("errorUser")) {
				updateUser = (InitUser) theModel.getAttribute("errorUser");
	            //errorProcess = true;
	        } else if(theModel.containsAttribute("successUpdate")) {
	        
	        	theModel.addAttribute("successUpdate", true);
	        	
	        }
			
			// get userDonationList to display
			Page<UserDonation> userDonations = userDonationService.findByUserNameSortByCreatedDate(currentUser.getUserName(),
					searchingValue, page, size);

			theModel.addAttribute("currentUser", currentUser);
			
			theModel.addAttribute("process", "processUpdateUser");

			//theModel.addAttribute("errorProcess", errorProcess);
			
			theModel.addAttribute("user", updateUser);

			theModel.addAttribute("userDonations", userDonations);
			
			SessionUtils.setCurrentEndpoint(request);

			return ViewConstants.V_USER_PROFILE;
			
		} catch (IllegalStateException e) {

			log.error("UserController - userProfile - NOT LOGIN: {}", e);

			return ViewConstants.V_ERROR_LOGIN;

		} catch (Exception e) {

			log.error("UserController - userProfile - ERROR FUNCTIONNAL: {}", e);

			return ViewConstants.V_ERROR;
		}
	}

	
	
	// for user to update infomation in profile
	@PostMapping("/processUpdateUser")
	public String processUpdate(HttpServletRequest request, @ModelAttribute("user") InitUser theUser,
			BindingResult theBindingResult, RedirectAttributes redirectAttributes) {

		try {
			
			HttpSession session = request.getSession();

			Integer currentUserId = SessionUtils.getCurrentUserId(session);

			// check current user is login
			if (currentUserId == null) {
				throw new IllegalStateException("UserController-profile: User is not login.");
			}
			
			if (BinddingResultsCustomFunction.isErrorForUpdateUser(theBindingResult, userService, theUser)) {
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user",
						theBindingResult);

				redirectAttributes.addFlashAttribute("errorUser", theUser);

				redirectAttributes.addFlashAttribute("errorForm", true);

			} else {

				userService.update(theUser.copyPropertiesToUserObj(userService.getUser(theUser.getId())),
						UserRole.USER);
				redirectAttributes.addFlashAttribute("successUpdate", true);
			}

			return "redirect:" + SessionUtils.getCurrentEndpoint(request);
			
		} catch (IllegalStateException e) {

			log.error("UserController - userProfile - NOT LOGIN: {}", e);

			return ViewConstants.V_ERROR_LOGIN;

		} catch (Exception e) {

			log.error("UserController - userProfile - ERROR FUNCTIONNAL: {}", e);

			return ViewConstants.V_ERROR;
		}

	}

}
