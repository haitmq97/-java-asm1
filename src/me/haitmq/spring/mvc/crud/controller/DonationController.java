package me.haitmq.spring.mvc.crud.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.hibernate.search.exception.impl.LogErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;

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
	public String donationlist(Model theModel) {
		List<Donation> donations = donationService.getDonationList();
		
		theModel.addAttribute("donations",donations);
		
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
		
		
		
		return "donation-detail";
	}
}
