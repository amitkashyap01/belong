package com.telecom.belong.controller;

import com.telecom.belong.entity.Phone;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/phone")
public interface PhoneController {

    @GetMapping("/")
    List<Phone> getAllPhoneDetails();

    @GetMapping("/phoneNumber")
    List<String> getAllThePhoneNumbers();

    @GetMapping("/phoneNumber/{customerId}")
    List<String> getAllPhoneNumbersByCustomerId(@PathVariable final String customerId);

    @PutMapping("/phoneNumber/{number}")
    boolean activatePhoneNumber(@PathVariable final String number);
}
