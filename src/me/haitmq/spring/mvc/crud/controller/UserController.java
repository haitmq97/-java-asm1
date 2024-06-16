package me.haitmq.spring.mvc.crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import me.haitmq.spring.mvc.crud.utils.SessionUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDonationService userDonationService;

	@GetMapping("/registerForm")
	public String showFormForAdd(Model theModel) {

		User theUser = new User();
		theModel.addAttribute("user", theUser);

		return "common/register-form";
	}

	@GetMapping("login")
	public String showLoginForm(Model theModel) {
		User theLoginUser = new User();
		theModel.addAttribute("user", theLoginUser);
		theModel.addAttribute("process", "processLogin");

		return "common/login-form";
	}

	/*
	 * @PostMapping("processLogin") public String processLogin(HttpServletRequest
	 * request, @ModelAttribute("user") User loginUser) { int userId =
	 * userService.getIdIfUserExisted(loginUser);
	 * 
	 * if (userId != -1) { HttpSession session = request.getSession();
	 * 
	 * session.setAttribute("currentUserId", userId);
	 * 
	 * 
	 * Integer currentUserId = (Integer) session.getAttribute("currentUserId");
	 * 
	 * System.out.println("=========================>>>>>> user id: " +
	 * currentUserId); }
	 * 
	 * return "redirect:/v1/home"; }
	 */
	@GetMapping("processLogout")
	public String processLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.removeAttribute("currentUserId");
		return "redirect:/v1/home";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int theId) {
		userService.delete(theId);

		return "redirect:/user/list";
	}

	@GetMapping("/details")
	public String userDetail(@RequestParam("userId") int userId, Model theModel) {
		User user = userService.getUser(userId);

		theModel.addAttribute("user", user);

		return "admin/user-detail";
	}

	@GetMapping("/addUser")
	public String addUser(Model theModel) {
		User user = new User();

		theModel.addAttribute("process", "processAdd");

		theModel.addAttribute("user", user);

		return "admin/user-form";
	}
	/*
	 * 
	 * @PostMapping("/processAdd") public String processAdd(@ModelAttribute("user")
	 * User theUser) {
	 * 
	 * userService.saveOrUpdate(theUser);
	 * 
	 * 
	 * return "redirect:/user/list"; }
	 * 
	 */

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int userId, Model theModel) {
		User theUser = userService.getUser(userId);

		theModel.addAttribute("process", "processUpdate");

		theModel.addAttribute("user", theUser);

		return "admin/user-form";
	}

	/*
	 * 
	 * @PostMapping("/processUpdate") public String
	 * processUpdate(@ModelAttribute("user") User theUser,
	 * 
	 * @RequestParam(name="currentUrl", defaultValue = "/user/profile") String
	 * currentUrl) {
	 * 
	 * 
	 * 
	 * userService.saveOrUpdate(theUser);
	 * 
	 * return "redirect:" + currentUrl; }
	 * 
	 * @GetMapping("/changeStatus") public String changeStatus(HttpServletRequest
	 * request,@RequestParam("userId") int userId) { HttpSession session =
	 * request.getSession();
	 * 
	 * 
	 * User theUser = userService.getUser(userId);
	 * theUser.setStatus((theUser.getStatus() == UserStatus.ACTIVE?
	 * UserStatus.LOCKED: UserStatus.ACTIVE)); userService.saveOrUpdate(theUser);
	 * return "redirect:/user/list"; }
	 */

	@GetMapping("/register")
	public String showRegisterForm(Model theModel) {
		User newUser = new User();
		theModel.addAttribute("user", newUser);
		theModel.addAttribute("process", "processRegister");

		return "common/register-from";
	}

	@PostMapping("/processRegister")
	public String processRegister(@ModelAttribute("user") User theUser) {

		return "redirect:/v1/home";
	}

	

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
			boolean errorProcess=false;
			
			InitUser updateUser = new InitUser();
			
			updateUser.getPropertiesFromDonationObj(userService.getUser(currentUserId));
			
			// check if the last update is success
			if (theModel.containsAttribute("errorUser")) {
				updateUser = (InitUser) theModel.getAttribute("errorUser");
	            errorProcess = true;
	        }
			
			// get userDonationList to display
			Page<UserDonation> userDonations = userDonationService.findByUserNameSortByCreatedDate(currentUser.getUserName(),
					searchingValue, page, size);

			theModel.addAttribute("currentUser", currentUser);
			
			theModel.addAttribute("process", "processUpdateUser");

			theModel.addAttribute("errorProcess", errorProcess);
			
			theModel.addAttribute("user", updateUser);

			theModel.addAttribute("userDonations", userDonations);
			
			SessionUtils.setCurrentEndpoint(request);

			return ViewConstants.V_USER_PROFILE;
		} catch (IllegalStateException ie) {
			return ViewConstants.V_ERROR_LOGIN;
		} catch (Exception e) {
			return ViewConstants.V_ERROR;
		}
	}

	/*
	 * @GetMapping("/profile") public String userProfile(HttpServletRequest request,
	 * 
	 * @RequestParam(defaultValue = "1") int page, @RequestParam(name = "size",
	 * defaultValue = "5") int size,
	 * 
	 * @RequestParam(name = "searchingValue", defaultValue = "", required = false)
	 * String searchingValue, Model theModel) {
	 * 
	 * try { // kiểm tra quyền (isLogined, isAdmin) (bao gồm phần header)
	 * 
	 * HttpSession session = request.getSession();
	 * 
	 * Integer currentUserId = SessionUtils.getCurrentUserId(session);
	 * 
	 * // check current user is login if (currentUserId == null) { throw new
	 * IllegalStateException("UserController-profile: User is not login."); }
	 * 
	 * SessionUtils.addLoginUserInfoToModel(session, theModel);
	 * 
	 * // get current user to add to the model User user =
	 * userService.getUser(currentUserId);
	 * 
	 * theModel.addAttribute("user", user);
	 * 
	 * theModel.addAttribute("process", "processUpdateUser");
	 * 
	 * // nav and authorites model attributes
	 * 
	 * 
	 * 
	 * 
	 * System.out.
	 * println("=======================>>>>>>>>>>>>>>> home page Current userId :" +
	 * currentUserId);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // userDonaion list
	 * 
	 * List<UserDonation> userDonations =
	 * userDonationService.getUserDonationByUserId(currentUserId);
	 * theModel.addAttribute("userDonations", userDonations);
	 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   test: " +
	 * userDonations);
	 * 
	 * SessionUtils.setCurrentEndpoint(request);
	 * 
	 * return ViewConstants.V_USER_PROFILE; } catch (IllegalStateException ie) {
	 * return ViewConstants.V_ERROR_LOGIN; } catch (Exception e) { return
	 * ViewConstants.V_ERROR; } }
	 * 
	

	@PostMapping("/processUpdateUser")
	public String processUpdate(HttpServletRequest request, @ModelAttribute("user") User theUser) {

		userService.update(theUser, UserRole.USER);

		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	 */
	
	@PostMapping("/processUpdateUser")
	public String processUpdate(HttpServletRequest request, 
			@ModelAttribute("user") InitUser theUser,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		try {
			if(theBindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", theBindingResult);
				
				redirectAttributes.addFlashAttribute("errorUser", theUser);
				
				redirectAttributes.addFlashAttribute("errorForm", true);
				
			} else {
		    	
		    	userService.update(theUser.copyPropertiesToUserObj(userService.getUser(theUser.getId())), UserRole.ADMIN);
				redirectAttributes.addFlashAttribute("successProcess", true);
		    }

			return "redirect:" + SessionUtils.getCurrentEndpoint(request);
		} catch (Exception e) {
			return ViewConstants.V_ERROR;
		}
	}

}
