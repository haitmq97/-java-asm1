package me.haitmq.spring.mvc.crud.controller;



import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.haitmq.spring.mvc.crud.entity.Donate;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.DonateService;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.utils.Time;

@Controller
@RequestMapping("/donate")
public class DonateController {
	private static final Logger log = LoggerFactory.getLogger(DonateController.class);



	@Autowired
	private DonateService donateService;

	@Autowired
	private DonationService donationService;

	@Autowired
	private UserService userService;
	/*

	@GetMapping("donate-form2")
	public String donateForm2(HttpServletRequest request, @RequestParam("donationId") int donationId, Model theModel) {
		theModel.addAttribute("donate", new Donate());
		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");
		System.out.println("==================>>>> currentUserId: " + currentUserId);
		System.out.println("==================>>>> donationId: " + donationId);
		theModel.addAttribute("donationId", donationId);
		return "donate-form";
	}

	@PostMapping("/donating2")
	public String donating(HttpServletRequest request, @ModelAttribute("donate")Donate donate, @RequestParam("donationId")int donationId) {
		System.out.println("=============>>>>>> donationId: " + donationId);
		HttpSession session = request.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");
		Donation currentDonation = donationService.getDonation(donationId);
		User user = userService.getUser(currentUserId);
		donate.setUser(user);
		donate.setDonation(currentDonation);
		donateService.save(donate);
		return "redirect:/v1/home3";
	}
	*/
	
	/////////////////////////////////////////////
	
	@GetMapping("donateForm")
	public String donateForm(HttpServletRequest request,@RequestParam("id") int donationId, Model theModel) {
		try {
			// get current user id and role
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			// if the user has not logged in yet then show login form
			if(currentUserId == null) {
				return "redirect:/v1/home";
			}
			// add new Donate obj to the model
			theModel.addAttribute("donate", new Donate());
			
			// add process form link to model
			theModel.addAttribute("process", "processDonating");
			
			// add donationId to the model (for process form)
			theModel.addAttribute("donationId", donationId);
			
			// return view
			return "user/donate-form";
		} catch (Exception e) {
			//log.error("DonateController ERROR - donateForm(): ", e);
			return "common/error-page";
		}
	}
	
	@PostMapping("/processDonating")
	public String processDonating(HttpServletRequest request, @ModelAttribute("donate")Donate donate, @RequestParam("donationId") int donationId) {
		
		try {
			HttpSession session = request.getSession();
			Integer currentUserId = (Integer) session.getAttribute("currentUserId");
			
			User user = userService.getUser(currentUserId);
			Donation donation = donationService.getDonation(donationId);
			donate.setUser(user);
			donate.setDonation(donation);
			donateService.save(donate);
			return "redirect:/v1/home";
		} catch (Exception e) {
			//log.error("DonateController ERROR - processDonating(): ", e);
			return "common/error-page";
		}
	}
	
	@GetMapping("/list")
	public String donateList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			
			Model theModel) {

		try {
			Page<Donate> donates = donateService.findAllSortByStatusByCreatedDate(page, size);

			theModel.addAttribute("datas", donates);

			theModel.addAttribute("currentPage", page);

			theModel.addAttribute("currentSize", size);

			int nextPage = page + 1;
			int prevPage = page - 1;

			if (page <= 0) {
				prevPage = 0;
			}

			theModel.addAttribute("prevPage", prevPage);

			if (page >= (donates.getTotalPages() - 1)) {
				nextPage = donates.getTotalPages() - 1;
			}

			theModel.addAttribute("nextPage", nextPage);
			
			System.out.println("=======================>>>>> test time");
			System.out.println("=======================>>>>> current time: " + Time.getCurrentDateTime());

			return "admin/donate-list";
		} catch (Exception e) {
			//log.error("DonateController ERROR - list(): ", e);
			return "common/error-page";
		}
	}
	
	@GetMapping("statusComfirm")
	public String statusProcessing(@RequestParam("id")int theId) {
		donateService.donateComfirm(theId);
		return "redirect:/donate/list";
	}
	
	
}
