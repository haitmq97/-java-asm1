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

	
	/*
	@GetMapping("/list")
	public String donationlist(Model theModel) {
		List<Donation> donations = donationService.getDonationList();

		theModel.addAttribute("donations", donations);

		return "donation-list";
	}

	@GetMapping("/donation-detail")
	public String userDetails(@RequestParam("donationId") int theId, Model theModel) {
		Donation tempDonation = donationService.getDonation(theId);

		theModel.addAttribute("user", tempDonation);

		return "donation-detail";
	}

	@GetMapping("/showFormForAdd")
	public String getDonationForm(Model theModel) {
		theModel.addAttribute("donation", new Donation());
		return "donation-form";
	}

	@PostMapping("/save-donation")
	public String saveOrUpdate(@ModelAttribute("donation") Donation donation) {
		donationService.saveOrUpdate(donation);

		return "redirect:/donation/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int theId) {
		System.out.println("test 1");
		donationService.delete(theId);
		System.out.println("test 2");
		return "redirect:/donation/list";
	}

	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("id") int theId, Model theModel) {
		Donation tempDonation = donationService.getDonation(theId);
		theModel.addAttribute("donation", tempDonation);

		return "donation-form";
	}

	@GetMapping("datetest")
	public String dateTest() {

		return "datetest";
	}

	@GetMapping("/detail")
	public String detail(@RequestParam("id") int theId, Model theModel) {
		Donation donation = donationService.getDonation(theId);

		theModel.addAttribute("donation", donation);

		return "donation-detail";
	}

	@GetMapping("/donation-table")
	public String donationTable(Model theModel) {
		List<Donation> donations = donationService.getDonationList();

		theModel.addAttribute("donations", donations);
		return "donation-table";
	}

	@GetMapping("/master")
	public String master() {
		return "master-layout-donation";
	}

	@GetMapping("donation-details")
	public String donationDetails(HttpServletRequest request, @RequestParam("id") int theId, Model theModel) {
		HttpSession session = request.getSession();
		
		System.out.println("==================>>>>>>> from donation controler in donation-details method - currentUserId: " + session.getAttribute("currentUserId"));;
		
		Donation donation = donationService.getDonation(theId);

		theModel.addAttribute("donation", donation);

		return "donationDetails";
	}
	*/

	
	////////////////////////////////////////////////////
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
		HttpSession session = request.getSession();
		
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
		
 		donationService.saveOrUpdate(theDonation);
		

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
		
 		donationService.saveOrUpdate(theDonation);
		

		return "redirect:/donation/listForAdmin";
	}
	
}
