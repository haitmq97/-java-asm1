package me.haitmq.spring.mvc.crud.controller;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.awt.Dialog.ModalExclusionType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.haitmq.spring.mvc.crud.common.InitDonation;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.utils.Time;
import me.haitmq.spring.mvc.crud.validation.UniquedDonationCodeConstraintValidator;

import org.springframework.validation.FieldError;

@Controller
@RequestMapping("/v22")
public class Test2 {
	
	private static final Logger logger = LoggerFactory.getLogger(Test2.class);
			
			
	@Autowired
    private DonationService donationService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	@GetMapping("/home")
	public String donationlist4(
			@RequestParam(name="searchingValue", defaultValue = "") String searchingValue, 
			@RequestParam(name = "page", defaultValue = "1") int page, 
			@RequestParam(name = "size", defaultValue = "5") int size ,
			Model theModel) {
		
		

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
	

	
	@GetMapping("testForm2")
	public String testForm2(Model theModel) {
		
		String process = "processTestForm2";

		InitDonation donation = new InitDonation();
		
		if (theModel.containsAttribute("errorAdd")) {
			
			donation = (InitDonation) theModel.getAttribute("initDonation");
			System.out.println("/////////////////// test: " + donation.toString() );
			System.out.println("/////////////////// test: " + donation.getCode() );
        }
		
		theModel.addAttribute("donation",  donation);
		
		theModel.addAttribute("process", process);
		
		return "test/testForm2";
	}
	
	
	@PostMapping("processTestForm2")
	public String processTestForm2(@Valid @ModelAttribute("donationTest") InitDonation donationTest,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		System.out.println(".................................current date: |" + Time.getCurrentDateTimeRaw()+"|");
		System.out.println("...................................start date: |" + donationTest.getStartDate()+"|");
		System.out.println(".................................is start date before current date : |" + Time.isBeforeDate(Time.getCurrentDateTimeRaw(), donationTest.getStartDate())+"|");
		System.out.println(".................................start date len: |" + donationTest.getStartDate().length()+"|");
		
		System.out.println(".................................start date: |" + donationTest.getEndDate()+"|");
		if(donationTest.getStartDate()== null) {
			System.out.println(".................................start date is null: true"  );
		} else {
			System.out.println(".................................start date is null: fales"  );
		}

		if(theBindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.donation", theBindingResult);
			
			redirectAttributes.addFlashAttribute("initDonation", donationTest);
			
			redirectAttributes.addFlashAttribute("errorAdd", true);
			
			List<FieldError> errors = theBindingResult.getFieldErrors();
			
            for (FieldError error : errors) {
                System.out.println(".................Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
            }
			
			return "redirect:/v22/testForm2";
		}
	
		Donation theDonation = modelMapper.map(donationTest, Donation.class);
		
		
		donationService.add(theDonation);
		
		return "test/confirm";
	}
	
	@GetMapping("testFormUpdate")
	public String testFormUpdate(Model theModel) {
		
		int donationid = 23;
		
		String process = "processTestFormUpdate";
		
		InitDonation temInitDonation = new InitDonation();
		
		temInitDonation.getPropertiesFromDonationObj(donationService.getDonation(donationid));

		InitDonation donation = temInitDonation;
		
		if (theModel.containsAttribute("errorAdd")) {
			
			donation = (InitDonation) theModel.getAttribute("initDonation");
			System.out.println("/////////////////// test: " + donation.toString() );
			System.out.println("/////////////////// test: " + donation.getCode() );
        }
		
		theModel.addAttribute("donation",  donation);
		
		theModel.addAttribute("process", process);
		
		return "test/testFormUpdate";
	}
	
	
	
	
	@PostMapping("processTestFormUpdate")
	public String processTestFormUpdate(@Valid @ModelAttribute("donationTest") InitDonation donationTest,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {
		
		System.out.println(".................................start date len: |" + donationTest.getStartDate().length()+"|");
		
		
		if(theBindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.donation", theBindingResult);
			
			redirectAttributes.addFlashAttribute("initDonation", donationTest);
			
			redirectAttributes.addFlashAttribute("errorAdd", true);
			
			List<FieldError> errors = theBindingResult.getFieldErrors();
			
            for (FieldError error : errors) {
                System.out.println(".................Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
            }
			
			return "redirect:/v22/testForm2";
		}
		
		Donation theDonation = donationTest.copyPropertiesToDonationObj(donationService.getDonation(donationTest.getId()));
		
		
		donationService.update(theDonation);
		
		return "test/confirm";
	}
	
	
	
	
		
	
	
	
	public void testadd(Donation donation) {
		donationService.add(donation);
	}
	
	
	@GetMapping("testForm3")
	public String testForm3(Model theModel) {
		
		String process = "processTestForm3";

		Donation donation = new Donation();
		
		
		if (theModel.containsAttribute("errorAdd")) {
			
			donation = (Donation) theModel.getAttribute("donation");
			/*
			System.out.println("/////////////////// test: " + donation.toString() );
			System.out.println("/////////////////// test: " + donation.getCode() );
			
			*/
        }
		/*
		System.out.println("////////////////////////////////////////// doantion(test): " + donation);
		*/
		theModel.addAttribute("donation",  donation);
		
		theModel.addAttribute("process", process);
		
		return "test/testForm3";
	}
	
	@PostMapping("processTestForm3")
	public String processTestForm3(@Valid @ModelAttribute("donationTest") Donation donation,
			BindingResult theBindingResult,
			RedirectAttributes redirectAttributes) {

		if(theBindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.donation", theBindingResult);
			
			redirectAttributes.addFlashAttribute("donation", donation);
			
			redirectAttributes.addFlashAttribute("errorAdd", true);
			
			List<FieldError> errors = theBindingResult.getFieldErrors();
			
            for (FieldError error : errors) {
                System.out.println(".................Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
            }
			
			return "redirect:/v22/testForm3";
		}
		
		System.out.println("donation Service:" + donationService);
		DonationService dService = donationService;
		
		List<Donation> donationList = dService.getDonationList();
		System.out.println("donation Service:" + donationService);
		logger.error("Test 1: value of donationService: {}", donationService );
		for(Donation d: donationList) {
			System.out.println("donation: " + d);
		}
		Donation tempDonation = dService.getDonation(1);
		
		logger.error("Test 2: value of donationService: {}", donationService );
		
		donationService.add(donation);
		logger.error(" error at donation add. value of donationService: {}", donationService);
		
		
		return "test/confirm";
	}
}
