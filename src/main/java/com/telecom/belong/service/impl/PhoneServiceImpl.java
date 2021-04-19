package com.telecom.belong.service.impl;

import com.telecom.belong.dao.PhoneDao;
import com.telecom.belong.entity.Phone;
import com.telecom.belong.exception.NoDataFoundException;
import com.telecom.belong.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneServiceImpl.class);

    @Autowired
    PhoneDao phoneDao;

    @Override
    public List<String> getAllPhoneNumbers() throws NoDataFoundException {
        LOGGER.debug("PhoneServiceImpl: getAllPhoneNumbers method: called");
        final List<String> allPhoneNumbers = phoneDao.getAllPhoneNumbers();

        if (!checkFromEmpty(allPhoneNumbers))
            return allPhoneNumbers;
        else {
            LOGGER.warn("No phone numbers found in database");
            throw new NoDataFoundException("No phone numbers found");
        }
    }

    @Override
    public List<String> getAllPhoneNumbersByCustomer(String customerId) {
        LOGGER.debug("PhoneServiceImpl: getAllPhoneNumbersByCustomer method: called");
        final List<String> allPhoneNumbers = phoneDao.getAllPhoneNumbersByCustomer(customerId);

        if (!checkFromEmpty(allPhoneNumbers))
            return allPhoneNumbers;
        else {
            LOGGER.warn("No phone numbers found for customer " + customerId);
            throw new NoDataFoundException("No phone numbers found for customer " + customerId);
        }
    }

    @Transactional
    @Override
    public boolean activatePhoneNumber(String phoneNumber) {
        LOGGER.debug("PhoneServiceImpl: activatePhoneNumber method: called");

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new NoDataFoundException("Phone number not provided");
        } else if (phoneDao.activatePhoneNumber(phoneNumber) == 0) {
            LOGGER.warn("Phone number {} couldn't be found. Hence, not activated.", phoneNumber);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Phone> getAllPhoneDetails() {
        LOGGER.debug("PhoneServiceImpl: getAllPhoneDetails method: called");

        final List<Phone> allPhones = phoneDao.getAllPhoneDetails();

        if (!checkFromEmpty(allPhones))
            return allPhones;
        else {
            LOGGER.warn("No phone details found");
            throw new NoDataFoundException("No phone details found");
        }
    }

    private boolean checkFromEmpty(final List<?> list) {
        return (null == list) || (list.size() == 0);
    }
}
