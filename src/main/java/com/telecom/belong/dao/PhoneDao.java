package com.telecom.belong.dao;

import com.telecom.belong.entity.Phone;

import java.util.List;

public interface PhoneDao {
    List<String> getAllPhoneNumbers();
    List<String> getAllPhoneNumbersByCustomer(final String customerId);
    int activatePhoneNumber(final String phoneNumber);

    List<Phone> getAllPhoneDetails();
}