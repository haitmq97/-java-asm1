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
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.Time;

@Controller
@RequestMapping("/v2")
public class UserController2 {

	@Autowired
	private UserService userService;

	
	

	@GetMapping("processLogout")
	public String processLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.removeAttribute("currentUserId");
		return "redirect:/v1/home";
	}

	
	
	
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int userId, Model theModel) {
		User theUser = userService.getUser(userId);
		
		theModel.addAttribute("process", "processUpdate");
		
		theModel.addAttribute("user", theUser);
		
		return "admin/user-form";
	}
	
	@PostMapping("/processUpdate")
	public String processUpdate(@ModelAttribute("user") User theUser) {
		
		
		
 		userService.saveOrUpdate(theUser);
		
		return "redirect:/user/list";
	}
	
	
	@GetMapping("/detail")
	public String userDetail(HttpServletRequest request, Model theModel) {
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("currentUserId");
		
		System.out.println("current user id: " + userId);
		User user = userService.getUser(userId);
 		
		theModel.addAttribute("user", user);

		return "user/user-detail";
	}
	

}	
