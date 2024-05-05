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

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.Role;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.service.UserDonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.Time;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDonationService userDonationService;

	/*
	 * @GetMapping("/list") public String list(Model theModel) { // get customer
	 * list from DAO List<User> userList = userService.getUserList();
	 * 
	 * // add customer to the model theModel.addAttribute("users", userList);
	 * 
	 * System.out.println("=======>>>>>>user list: " + userList);
	 * 
	 * for (User user : userList) { System.out.println("=======>>>>>>user user: " +
	 * user); System.out.println("=======>>>>>>user role: : " +
	 * user.getRole().getRoleName());
	 * 
	 * System.out.println("=======>>>>>>user status: " + user.getStatus());
	 * 
	 * }
	 * 
	 * return "user-list"; }
	 * 
	 * @GetMapping("/showFormForAdd") public String showFormForAdd(Model theModel) {
	 * 
	 * // create a customer object and add it to model
	 * 
	 * User theUser = new User(); theModel.addAttribute("user", theUser);
	 * 
	 * System.out.println("============>>>>> test in showFormForAdd:");
	 * 
	 * System.out.println("============>>>>> test in showFormForAdd--- test role: "
	 * + theUser.getRole());
	 * 
	 * return "add-user-form"; }
	 * 
	 * @PostMapping("/saveUser") public String
	 * saveUser(@Valid @ModelAttribute("user") User theUser, BindingResult
	 * theBindingResult,
	 * 
	 * @RequestParam("role") String theRole) {
	 * 
	 * if (theBindingResult.hasErrors()) {
	 * 
	 * 
	 * System.out.println("Email fields: |" + theUser.getEmail() + "|");
	 * System.out.println("phone number fields: |" + theUser.getPhoneNumber() +
	 * "|"); System.out.println("||"); System.out.println("length: " + "".length());
	 * for (ObjectError error : theBindingResult.getAllErrors()) {
	 * System.out.println(error.getDefaultMessage()); }
	 * 
	 * return "add-user-form"; } else {
	 * System.out.println("=====>>>>>>============================");
	 * System.out.println("=====>>>>>>In controller method: ");
	 * System.out.println("=====>>>>>>BEFORE add role ");
	 * System.out.println("=====>>>>>>the User: " + theUser);
	 * System.out.println("=====>>>>>>the User role: " + theUser.getRole());
	 * System.out.println("=====>>>>>>============================"); if
	 * (theUser.getRole() == null) { Role role = new Role(theRole);
	 * theUser.setRole(role);
	 * 
	 * } else { theUser.getRole().setRoleName(theRole); }
	 * System.out.println("=====>>>>>>============================");
	 * System.out.println("=====>>>>>>In controller method: ");
	 * System.out.println("=====>>>>>>AFTER add role ");
	 * System.out.println("=====>>>>>>the User: " + theUser);
	 * System.out.println("=====>>>>>>the User role: " + theUser.getRole());
	 * System.out.println("=====>>>>>>============================");
	 * 
	 * theUser.setStatus(1);
	 * 
	 * // save customer userService.saveOrUpdate(theUser); return
	 * "redirect:/user/list"; }
	 * 
	 * }
	 * 
	 * @PostMapping("/saveUser2") public String
	 * saveUser2(@Valid @ModelAttribute("user") User theUser, BindingResult
	 * theBindingResult,
	 * 
	 * @RequestParam("role_1") String theRole, @RequestParam("roleid") String
	 * roleId) {
	 * 
	 * if (theBindingResult.hasErrors()) {
	 * 
	 * return "add-user-form"; } else { // !!!!!!!!!tai sao role lai duoc tao truoc
	 * khi set up System.out.println("============>>>> role:" + theUser.getRole());
	 * System.out.println("============>>>> Instance of role:" +
	 * theUser.getRole().getClass().getSimpleName());
	 * System.out.println("============>>>> createdDate:" + theUser.getCreated());
	 * System.out.println("============>>>>role id from jsp page: |" + roleId +
	 * "|");
	 * 
	 * System.out .println("============>>>>role id from jsp page (getClass): " +
	 * roleId.getClass().getSimpleName());
	 * 
	 * if (roleId.contentEquals("")) {
	 * 
	 * theUser.setRole(new Role(theRole));
	 * System.out.println("============>>>> role is null");
	 * 
	 * } else { theUser.getRole().setId(Integer.parseInt(roleId));
	 * theUser.getRole().setRoleName(theRole);
	 * System.out.println("============>>>> role is NOT null"); }
	 * 
	 * theUser.setStatus(1); // save customer userService.saveOrUpdate(theUser);
	 * return "redirect:/user/list"; }
	 * 
	 * }
	 * 
	 * @PostMapping("/saveUser3") public String
	 * saveUser3(@Valid @ModelAttribute("user") User theUser, BindingResult
	 * theBindingResult,
	 * 
	 * @RequestParam("role_1") String theRole, @RequestParam("role_id") String
	 * roleId) {
	 * 
	 * if (theBindingResult.hasErrors()) {
	 * 
	 * return "add-user-form"; } else { // !!!!!!!!!tai sao role lai duoc tao truoc
	 * khi set up System.out.println("============>>>> role:" + theUser.getRole());
	 * System.out.println("============>>>> createdDate:" + theUser.getCreated());
	 * 
	 * if (theUser.getRole() == null) {
	 * 
	 * theUser.setRole(new Role(theRole));
	 * System.out.println("============>>>> role is null");
	 * 
	 * } else { theUser.getRole().setRoleName(theRole);
	 * System.out.println("============>>>> role is NOT null"); }
	 * 
	 * theUser.setStatus(1); // save customer userService.saveOrUpdate(theUser);
	 * return "redirect:/user/listByPage"; }
	 * 
	 * }
	 * 
	 * @GetMapping("/listByPage") public String
	 * listByPage(@RequestParam(defaultValue = "0") int
	 * page, @RequestParam(defaultValue = "10") int size,
	 * 
	 * @RequestParam(name = "searchingValue", defaultValue = "", required = false)
	 * String searchingValue, Model theModel) {
	 * 
	 * Page<User> paginatedData = userService.getPaginatedData(page, size);
	 * 
	 * 
	 * theModel.addAttribute("searchingValue", searchingValue);
	 * 
	 * if (!(searchingValue.equals(""))) { paginatedData =
	 * userService.findAllByEmailOrPhoneNumber(page, size, searchingValue);
	 * theModel.addAttribute("searchingValue", searchingValue); }
	 * 
	 * theModel.addAttribute("paginatedData", paginatedData);
	 * 
	 * theModel.addAttribute("currentPage", page);
	 * 
	 * theModel.addAttribute("currentSize", size);
	 * 
	 * int nextPage = page + 1; int prevPage = page - 1;
	 * 
	 * if (page <= 0) { prevPage = 0; }
	 * 
	 * theModel.addAttribute("prevPage", prevPage);
	 * 
	 * if (page >= (paginatedData.getTotalPages() - 1)) { nextPage =
	 * paginatedData.getTotalPages() - 1; }
	 * 
	 * theModel.addAttribute("nextPage", nextPage);
	 * 
	 * return "page-list"; }
	 * 
	 * @RequestMapping("/testSearching") public ModelAndView refesh() { ModelAndView
	 * mView = new ModelAndView("redirect:/user/listByPage");
	 * 
	 * return mView;
	 * 
	 * }
	 * 
	 * @GetMapping("/showFormForUpdate") public String
	 * showformForUpdate(@RequestParam("userId") int theId, Model theModel) {
	 * 
	 * User user = userService.getUser(theId);
	 * 
	 * theModel.addAttribute("user", user);
	 * 
	 * System.out.println("============>>>>> test in showFormForUpdate:");
	 * System.out.
	 * println("============>>>>> test in showFormForUpdate--- test user: " + user);
	 * 
	 * System.out.
	 * println("============>>>>> test in showFormForUpdate--- test role: " +
	 * user.getRole());
	 * 
	 * return "add-user-form"; }
	 * 
	 * @GetMapping("/deleteUser") public String
	 * deleteCustomer(@RequestParam("userId") int theId) {
	 * 
	 * userService.deleteUser(theId);
	 * 
	 * return "redirect:/user/list"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // lock or unlock User
	 * 
	 * @GetMapping("/lockOrUnlock") public String
	 * statushandle(@RequestParam("userId") int theId) { User tempUser =
	 * userService.getUser(theId);
	 * 
	 * tempUser.setStatus(tempUser.getStatus() == 1 ? 0 : 1);
	 * 
	 * userService.saveOrUpdate(tempUser);
	 * 
	 * return "redirect:/user/list"; }
	 * 
	 * // show user detail
	 * 
	 * @GetMapping("/userDetails") public String userDetails(@RequestParam("userId")
	 * int theId, Model theModel) { User tempUser = userService.getUser(theId);
	 * 
	 * theModel.addAttribute("user", tempUser);
	 * 
	 * return "user-details"; }
	 * 
	 */

	////////////////////////
	@GetMapping("/list")
	public String userList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {

		Page<User> users = userService.findByEmailOrPhoneNumberOrStatus(searchingValue, page, size);
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

	@GetMapping("processLogout")
	public String processLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.removeAttribute("currentUserId");
		return "redirect:/v1/home";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int theId) {
		userService.deleteUser(theId);

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
	
	@PostMapping("/processAdd")
	public String processAdd(@ModelAttribute("user") User theUser) {
		
 		userService.saveOrUpdate(theUser);
		

		return "redirect:/user/list";
	}
	
	
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int userId, Model theModel) {
		User theUser = userService.getUser(userId);
		
		theModel.addAttribute("process", "processUpdate");
		
		theModel.addAttribute("user", theUser);
		
		return "admin/user-form";
	}
	
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
		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");
		
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> current user id: " + currentUserId);
		User user = userService.getUser(currentUserId);
 		
		theModel.addAttribute("user", user);
		
		theModel.addAttribute("process", "processUpdate");
		
		// nav and authorites model attributes
		
		


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
		
		
		
		
		// userDonaion list

		List<UserDonation> userDonations = userDonationService.getUserDonationByUserId(currentUserId);
		theModel.addAttribute("userDonations", userDonations);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   test: " + userDonations);
			
		return "user/user-profile";
	}
	
}	
