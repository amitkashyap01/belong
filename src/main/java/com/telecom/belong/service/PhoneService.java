package com.telecom.belong.service;

import com.telecom.belong.entity.Phone;

import java.util.List;

public interface PhoneService {
    List<String> getAllPhoneNumbers();
    List<String> getAllPhoneNumbersByCustomer(final String customerId);
    boolean activatePhoneNumber(final String phoneNumber);

    List<Phone> getAllPhoneDetails();
}
