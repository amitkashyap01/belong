package com.telecom.belong.controller;

import com.telecom.belong.entity.Phone;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/phone")
public interface PhoneController {

    @GetMapping("/")
    @ApiOperation(value = "Get all the phone details",
            notes = "This API returns all the phone details from database. The detail contains " +
                    "customer id, phone number and active flag.")
    List<Phone> getAllPhoneDetails();

    @GetMapping("/phoneNumber")
    @ApiOperation(value = "Get all phone numbers",
            notes = "This API returns all the phone numbers available in database.")
    List<String> getAllThePhoneNumbers();

    @GetMapping("/phoneNumber/{customerId}")
    @ApiOperation(value = "Get all phone numbers for a single customer",
            notes = "This API returns all the phone numbers for a single customer from database.")
    List<String> getAllPhoneNumbersByCustomerId(@PathVariable final String customerId);

    @PutMapping("/phoneNumber/{number}")
    @ApiOperation(value = "Activate a phone number",
            notes = "This API activates a given phone number " +
                    "by setting the active flag to 'Y' and return true if activated")
    boolean activatePhoneNumber(@PathVariable final String number);
}
