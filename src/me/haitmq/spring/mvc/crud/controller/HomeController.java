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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;

@Controller
@RequestMapping("/v1")
public class HomeController {
	@Autowired
	private UserService userService;

	@Autowired
	private DonationService donationService;

	/*
	 * @RequestMapping("/home") public String homePage() { return "home"; }
	 * 
	 * 
	 * 
	 * @GetMapping("/home2") public String
	 * listByPage(@ModelAttribute("currentUser")User currentUser
	 * , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue =
	 * "3") int size, Model theModel) {
	 * 
	 * Page<Donation> paginatedData = donationService.getPaginatedData(page, size);
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
	 * theModel.addAttribute("user", new User());
	 * 
	 * 
	 * 
	 * return "home2"; }
	 * 
	 * 
	 * @PostMapping("/login2") public String login2(@ModelAttribute("user") User
	 * user ,RedirectAttributes redirectAttributes) { User dbUser =
	 * userService.getUserByUserName(user.getUserName());
	 * System.out.println("====>>>>>> User from db: " + dbUser); if(dbUser==null) {
	 * System.out.println("====>>>>>> Username is not exist"); } else {
	 * if(!userService.isPasswordMatched(dbUser.getUserName(), user.getPassword()))
	 * { System.out.println("====>>>>>> Password not matched"); dbUser=null; } }
	 * 
	 * redirectAttributes.addFlashAttribute("currentUser", dbUser);
	 * 
	 * return "redirect:/v1/home3"; }
	 * 
	 * 
	 * 
	 * /// sử dụng session để lưu thông tin đăng nhập
	 * 
	 * 
	 * @GetMapping("/home3") public String listByPage2(HttpServletRequest request,
	 * 
	 * @ModelAttribute("currentUser")User currentUser,
	 * 
	 * @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3")
	 * int size, Model theModel) {
	 * 
	 * HttpSession session = request.getSession();
	 * 
	 * Integer currentUserId = (Integer) session.getAttribute("currentUserId");
	 * 
	 * System.out.
	 * println("====================>>>>>> current user id from session: " +
	 * currentUserId);
	 * 
	 * Page<Donation> paginatedData = donationService.getPaginatedData(page, size);
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
	 * theModel.addAttribute("user", new User());
	 * 
	 * 
	 * 
	 * return "home3"; }
	 * 
	 * 
	 * @PostMapping("/login") public String login(HttpServletRequest request,
	 * 
	 * @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
	 * User dbUser = userService.getUserByUserName(user.getUserName());
	 * System.out.println("====>>>>>> User from db: " + dbUser); if( (dbUser!=null)
	 * &&(userService.isPasswordMatched(dbUser.getUserName(), user.getPassword())) )
	 * { HttpSession session = request.getSession();
	 * 
	 * session.setAttribute("currentUserId", dbUser.getId()); }
	 * 
	 * 
	 * int a =1; int b =2;
	 * 
	 * 
	 * redirectAttributes.addFlashAttribute("currentUser", dbUser);
	 * 
	 * return "redirect:/v1/home3"; }
	 * 
	 */

	@GetMapping("/home")
	public String homePage(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");
		System.out.println("==============>>>> User id: " + currentUserId);
		
		
		return "donation/home-page";
	}
}
