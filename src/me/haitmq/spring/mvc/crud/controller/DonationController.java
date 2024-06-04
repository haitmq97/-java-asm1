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

import me.haitmq.spring.mvc.crud.entity.Donation;

import me.haitmq.spring.mvc.crud.service.DonationService;

@Controller
@RequestMapping("/donation")
public class DonationController {
	@Autowired
	private DonationService donationService;

	
	@GetMapping("/list")
	public String donationlist(@RequestParam(defaultValue = "1") int page, @RequestParam(name="size", defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {
		
		System.out.println("size: " + size);
		Page<Donation> donations = donationService.findAll(page, size);
		
		
		if(!searchingValue.equals("")) {
			donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
			theModel.addAttribute("searchingValue", searchingValue);
		}
		
		
		
		theModel.addAttribute("donations", donations);
		
		theModel.addAttribute("currentPage", page);
		theModel.addAttribute("totalPage", donations.getTotalPages());

		theModel.addAttribute("currentSize", size);

		int nextPage = page + 1;
		int prevPage = page - 1;

		if (page <= 1) {
			prevPage = 1;
		}

		theModel.addAttribute("prevPage", prevPage);
		System.out.println("current page" + page);
		System.out.println("total page: " + donations.getTotalPages());
		if (page >= (donations.getTotalPages())) {
			nextPage = donations.getTotalPages();
		}

		theModel.addAttribute("nextPage", nextPage);

//		return "user/donationList";
		return "public/donation-table";
	}
	
	@GetMapping("donation-details")
	public String donationDetails(HttpServletRequest request, @RequestParam("id") int theId, Model theModel) {
		
		Donation donation = donationService.getDonation(theId);

		theModel.addAttribute("donation", donation);

		return "user/donationDetails";
	}
	

	
	
	/// for admin
	
	@GetMapping("/listForAdmin")
	public String donationListForAdmin(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(name = "searchingValue", defaultValue = "", required = false) String searchingValue,
			Model theModel) {
		Page<Donation> donations = donationService.findAll(page, size);
		
		
		if(!searchingValue.equals("")) {
			donations = donationService.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, page, size);
			theModel.addAttribute("searchingValue", searchingValue);
		}
	
		theModel.addAttribute("donations", donations);
		
		theModel.addAttribute("currentPage", page);

		theModel.addAttribute("currentSize", size);

		int nextPage = page + 1;
		int prevPage = page - 1;

		if (page <= 0) {
			prevPage = 0;
		}

		theModel.addAttribute("prevPage", prevPage);

		if (page >= (donations.getTotalPages() - 1)) {
			nextPage = donations.getTotalPages() - 1;
		}

		theModel.addAttribute("nextPage", nextPage);

		return "admin/donation-table";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("donationId") int donationId) {
		donationService.delete(donationId);
		
		return "redirect:/donation/listForAdmin";
		
	}
	
	
	@GetMapping("/addDonation")
	public String addDonation(Model theModel) {
		Donation donation = new Donation();
		
		theModel.addAttribute("process", "processAdd");
		
		theModel.addAttribute("donation", donation);

		return "admin/donation-form";
	}
	
	@PostMapping("/processAdd")
	public String processAdd(@ModelAttribute("donation") Donation theDonation) {
		
 		donationService.add(theDonation);
		

		return "redirect:/donation/listForAdmin";
	}
	
	@GetMapping("/updateDonation")
	public String updateDonation(@RequestParam("donationId") int donationId, Model  theModel) {
		Donation donation = donationService.getDonation(donationId);
		
		theModel.addAttribute("process", "processAdd");
		
		theModel.addAttribute("donation", donation);

		return "admin/donation-form";
	}
	
	@PostMapping("/processUpdate")
	public String processUpdate(@ModelAttribute("donation") Donation theDonation) {
		
 		donationService.update(theDonation);
		

		return "redirect:/donation/listForAdmin";
	}
	
}
