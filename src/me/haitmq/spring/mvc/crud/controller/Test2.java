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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.service.DonationService;


@Controller
@RequestMapping("/v22")
public class Test2 {
	@Autowired
    private DonationService donationService;
	
	
	@GetMapping("/home")
	public String donationlist4(
			@RequestParam(name="searchingValue", defaultValue = "") String searchingValue, 
			@RequestParam(name = "page", defaultValue = "1") int page, 
			@RequestParam(name = "size", defaultValue = "5") int size ,
			Model theModel) {
		
		
		donationService.autoUpdateStatusALL();
		Page<Donation> donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
		System.out.println(donations.getClass());
		theModel.addAttribute("donations", donations);
		/* theModel.addAttribute("class", donations.getClass()); */
		theModel.addAttribute("content", donations.getContent());
		theModel.addAttribute("number", donations.getNumber());
		theModel.addAttribute("numberofelement", donations.getNumberOfElements());
		theModel.addAttribute("totalpage", donations.getTotalPages());
		theModel.addAttribute("totalelement", donations.getTotalElements());
		theModel.addAttribute("size", donations.getSize());
		theModel.addAttribute("hascontent", donations.hasContent());
		theModel.addAttribute("hasnext", donations.hasNext());
		theModel.addAttribute("hasprevious", donations.hasPrevious());
		theModel.addAttribute("isempty", donations.isEmpty());
		theModel.addAttribute("isfirst", donations.isFirst());
		theModel.addAttribute("islast", donations.isLast());
		
		theModel.addAttribute("currentSearchingValue", searchingValue);
		theModel.addAttribute("currentSize", size);
		theModel.addAttribute("currentPage", page);
		
		
		System.out.println("donations: " + donations.getContent());
		
		
		System.out.println("size: " + size);
		System.out.println("page: " + page);
		System.out.println("searchingValue: " + searchingValue);
		return "public/test22";
	}
	
	@GetMapping("/test")
	public String test1(HttpServletRequest request, Model theModel) {
		

		
		if (theModel.containsAttribute("loginUser")) {
			System.out.println("///////////////////// is contain login user true");
            theModel.addAttribute("loginUser", theModel.getAttribute("loginUser"));
        } else {
            theModel.addAttribute("loginUser", new LoginUser());
            System.out.println("///////////////////// is contain login user false");
        }
		
		if (theModel.containsAttribute("currentLoginUser")) {
			theModel.addAttribute("currentLoginUser", theModel.getAttribute("currentLoginUser"));
			System.out.println("///////////////////// is contain current login user true");
		} else {
			theModel.addAttribute("currentLoginUser", new LoginUser());
			System.out.println("///////////////////// is contain current login user false");
		}
		
		if (theModel.containsAttribute("errorLogin")) {
			theModel.addAttribute("errorLogin", true);
		} else {
			theModel.addAttribute("errorLoin", false);
		}
		
		HttpSession session = request.getSession();
		
		Object sessionVariableObject = session.getAttribute("sessionVariable");
		if (sessionVariableObject != null) {
		    // Bây giờ bạn có thể gọi getClass() mà không lo lỗi NullPointerException
		    String className = sessionVariableObject.getClass().getSimpleName();
		    
		    System.out.println("///////////////////session: " + session.getAttribute("sessionVariable"));
			System.out.println("///////////////////session type of: " + className);
		} else {
		    System.out.println("///////////////////session: " + null);
		}
		
	
		
		return "test/test1";
	}
	
	
	@PostMapping("/processtest")
	public String processtest1(HttpServletRequest request,
            @Valid @ModelAttribute("loginUser") LoginUser loginUser, 
            BindingResult theBindingResult,
            RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Boolean sessionVariable = false;
		
		if (theBindingResult.hasErrors()) {
            // If there are validation errors, add them to the redirect attributes
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUser", theBindingResult);
            redirectAttributes.addFlashAttribute("loginUser", loginUser);
            redirectAttributes.addFlashAttribute("errorLogin", true);
            System.out.println("///////////////////// binding result true");
        } else {
        	redirectAttributes.addFlashAttribute("currentLoginUser", loginUser);
        	
        	
        	
        	
        	sessionVariable = true;
        	System.out.println("///////////////////// binding result false");
        }
		
		session.setAttribute("sessionVariable", sessionVariable);
		
		return "redirect:/v22/test";
	}
	


}
