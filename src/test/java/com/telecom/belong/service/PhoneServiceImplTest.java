package com.telecom.belong.service;

import static org.junit.Assert.assertEquals;
import com.telecom.belong.dao.PhoneDao;
import com.telecom.belong.entity.Phone;
import com.telecom.belong.exception.NoDataFoundException;
import com.telecom.belong.service.impl.PhoneServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceImplTest {

    @InjectMocks
    PhoneServiceImpl phoneService;

    @Mock
    PhoneDao phoneDao;

    private List<String> listOfPhoneNumers;
    private List<Phone> listOfPhoneDetails;

    @Before
    public void setup(){
        listOfPhoneNumers = Arrays.asList("0400000001", "0400000002", "0400000003");

        Phone phoneDetails1 = new Phone(1, "Cust001", "0460000001", 'N');
        Phone phoneDetails2 = new Phone(2, "Cust002", "0460000002", 'N');

        listOfPhoneDetails = Arrays.asList(phoneDetails1, phoneDetails2);
    }

    @Test
    public void testGetAllPhoneNumbers(){
        Mockito.when(phoneDao.getAllPhoneNumbers()).thenReturn(listOfPhoneNumers);

        final List<String> allPhoneNumbers = phoneService.getAllPhoneNumbers();

        Mockito.verify(phoneDao).getAllPhoneNumbers();
        assertEquals(3, allPhoneNumbers.size());
    }

    @Test(expected = NoDataFoundException.class)
    public void testGetAllPhoneNumbers_NoDataFound(){
        listOfPhoneNumers = null;
        Mockito.when(phoneDao.getAllPhoneNumbers()).thenReturn(listOfPhoneNumers);

        phoneService.getAllPhoneNumbers();
    }

    @Test
    public void testGetAllPhoneNumbersByCustomer(){
        Mockito.when(phoneDao.getAllPhoneNumbersByCustomer("XYZ")).thenReturn(listOfPhoneNumers);

        final List<String> allPhoneNumbers = phoneService.getAllPhoneNumbersByCustomer("XYZ");

        Mockito.verify(phoneDao).getAllPhoneNumbersByCustomer("XYZ");
        assertEquals(3, allPhoneNumbers.size());
    }

    @Test(expected = NoDataFoundException.class)
    public void testGetAllPhoneNumbersByCustomer_NoDataFound(){
        listOfPhoneNumers = null;
        Mockito.when(phoneDao.getAllPhoneNumbersByCustomer("XYZ")).thenReturn(listOfPhoneNumers);

        phoneService.getAllPhoneNumbersByCustomer("XYZ");
    }

    @Test
    public void testActivatePhoneNumber(){
        Mockito.when(phoneDao.activatePhoneNumber("0460000001")).thenReturn(1);
        final boolean b = phoneService.activatePhoneNumber("0460000001");

        Mockito.verify(phoneDao).activatePhoneNumber("0460000001");
        assertEquals(true, b);
    }

    @Test
    public void testActivatePhoneNumber_NumberNotFound(){
        Mockito.when(phoneDao.activatePhoneNumber("0460000001")).thenReturn(0);
        final boolean b = phoneService.activatePhoneNumber("0460000001");

        Mockito.verify(phoneDao).activatePhoneNumber("0460000001");
        assertEquals(false, b);
    }

    @Test(expected = NoDataFoundException.class)
    public void testActivatePhoneNumber_NumberNotProvided(){
        phoneService.activatePhoneNumber("");

        Mockito.verify(phoneDao, Mockito.times(0)).activatePhoneNumber("");
    }

    @Test
    public void testGetAllPhoneDetails(){
        Mockito.when(phoneDao.getAllPhoneDetails()).thenReturn(listOfPhoneDetails);

        final List<Phone> allPhoneDetails = phoneService.getAllPhoneDetails();

        Mockito.verify(phoneDao).getAllPhoneDetails();
        assertEquals(2, allPhoneDetails.size());
    }

    @Test(expected = NoDataFoundException.class)
    public void testGetAllPhoneDetails_NoDataFound(){
        listOfPhoneDetails = null;
        Mockito.when(phoneDao.getAllPhoneDetails()).thenReturn(listOfPhoneDetails);

        final List<Phone> allPhoneDetails = phoneService.getAllPhoneDetails();

        Mockito.verify(phoneDao).getAllPhoneDetails();
        assertEquals(null, allPhoneDetails);
    }

}
