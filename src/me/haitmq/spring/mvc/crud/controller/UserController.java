package me.haitmq.spring.mvc.crud.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import me.haitmq.spring.mvc.crud.content_path.ViewConstants;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.Role;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.role.UserRole;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.LoginUserInfomation;
import me.haitmq.spring.mvc.crud.utils.SessionUtils;
import me.haitmq.spring.mvc.crud.utils.Time;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDonationService userDonationService;

	
	////////////////////////
	/*
	@GetMapping("/list")
	public String userList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {

		Page<User> users = userService.findByEmailOrUserNameOrPhoneNumber(page, size, searchingValue);
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

		return "admin/user-list";
	}
	
	*/

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
	
	@PostMapping("/processAdd")
	public String processAdd(@ModelAttribute("user") User theUser) {
		
 		userService.saveOrUpdate(theUser);
		

		return "redirect:/user/list";
	}
	
	*/
	
	
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int userId, Model theModel) {
		User theUser = userService.getUser(userId);
		
		theModel.addAttribute("process", "processUpdate");
		
		theModel.addAttribute("user", theUser);
		
		return "admin/user-form";
	}
	
	/*
	
	@PostMapping("/processUpdate")
	public String processUpdate(@ModelAttribute("user") User theUser,
			@RequestParam(name="currentUrl", defaultValue = "/user/profile") String currentUrl) {
		
		
		
 		userService.saveOrUpdate(theUser);
		
		return "redirect:" + currentUrl;
	}
	
	@GetMapping("/changeStatus")
	public String changeStatus(HttpServletRequest request,@RequestParam("userId") int userId) {
		HttpSession session = request.getSession();
		
		
		User theUser = userService.getUser(userId);
		theUser.setStatus((theUser.getStatus() == UserStatus.ACTIVE? UserStatus.LOCKED: UserStatus.ACTIVE));
		userService.saveOrUpdate(theUser);
		return "redirect:/user/list";
	}
	*/
	
	
	@GetMapping("/register")
	public String showRegisterForm(Model theModel) {
		User newUser = new User();
		theModel.addAttribute("user", newUser);
		theModel.addAttribute("process", "processRegister");
		
		return "common/register-from";
	}
	
	
	@PostMapping("/processRegister")
	public String processRegister(@ModelAttribute("user")User theUser) {
		
		
		return "redirect:/v1/home";
	}
	
	
	@GetMapping("/profile")
	public String userProfile(HttpServletRequest request, Model theModel) {
		
		// kiểm tra quyền (isLogined, isAdmin) (bao gồm phần header)

		HttpSession session = request.getSession();
		
		Integer currentUserId = LoginUserInfomation.getCurrentUserId(session);
		
		/*
		Boolean isLogined = false;

		Boolean isAdmin = false;

		if (currentUserId != null) {
			isLogined = true;
			isAdmin = userService.isAdmin(currentUserId);
		}

		theModel.addAttribute("isLogined", isLogined);

		theModel.addAttribute("isAdmin", isAdmin);
		*/
		
		LoginUserInfomation.addLoginUserInfoToModel(session, theModel);
		

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> current user id: " + currentUserId);
		User user = userService.getUser(currentUserId);
 		
		theModel.addAttribute("user", user);
		
		theModel.addAttribute("process", "processUpdateUser");
		
		// nav and authorites model attributes
		
		


		System.out.println("=======================>>>>>>>>>>>>>>> home page Current userId :" + currentUserId);
		
		

		
		
		// userDonaion list

		List<UserDonation> userDonations = userDonationService.getUserDonationByUserId(currentUserId);
		theModel.addAttribute("userDonations", userDonations);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   test: " + userDonations);
		
		SessionUtils.setCurrentEndpoint(request);
			
		return ViewConstants.V_USER_PROFILE;
	}
	
	@PostMapping("/processUpdateUser")
	public String processUpdate(HttpServletRequest request, @ModelAttribute("user") User theUser) {

		userService.update(theUser, UserRole.USER);

		return "redirect:" + SessionUtils.getCurrentEndpoint(request);
	}
	
}	
