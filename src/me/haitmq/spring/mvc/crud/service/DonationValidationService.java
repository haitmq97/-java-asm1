package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.haitmq.spring.mvc.crud.entity.Donation;

@Service
public class DonationValidationService {

    @Autowired
    private DonationService donationService;

    @Transactional(readOnly = true)
    public boolean isDonationCodeUnique(String theCode) {
        List<Donation> donationList = donationService.getDonationList();
        return donationList.stream().noneMatch(donation -> donation.getCode().equals(theCode));
    }
}
