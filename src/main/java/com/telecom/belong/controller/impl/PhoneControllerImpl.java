package com.telecom.belong.controller.impl;

import com.telecom.belong.controller.PhoneController;
import com.telecom.belong.entity.Phone;
import com.telecom.belong.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneControllerImpl implements PhoneController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneControllerImpl.class);

    @Autowired
    PhoneService phoneService;

    @Override
    public List<Phone> getAllPhoneDetails() {
        LOGGER.info("Received request to get all phone details");
        return phoneService.getAllPhoneDetails();
    }

    @Override
    public List<String> getAllThePhoneNumbers(){
        LOGGER.info("Received request to get all phone numbers");
        return phoneService.getAllPhoneNumbers();
    }

    @Override
    public List<String> getAllPhoneNumbersByCustomerId(String customerId){
        LOGGER.info("Received request to get phone numbers for customer {}", customerId);
        return phoneService.getAllPhoneNumbersByCustomer(customerId);
    }

    @Override
    public boolean activatePhoneNumber(String number) {
        LOGGER.info("Received request to activate phone number {}", number);
        return phoneService.activatePhoneNumber(number);
    }
}
