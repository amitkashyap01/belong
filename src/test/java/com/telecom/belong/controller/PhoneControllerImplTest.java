package com.telecom.belong.controller;

import static org.junit.Assert.assertEquals;
import com.telecom.belong.controller.impl.PhoneControllerImpl;
import com.telecom.belong.entity.Phone;
import com.telecom.belong.service.PhoneService;
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
public class PhoneControllerImplTest {

    @InjectMocks
    PhoneControllerImpl phoneController;

    @Mock
    PhoneService phoneService;

    private List<String> listOfPhoneNumbers;
    private List<Phone> listOfPhoneDetails;

    @Before
    public void setup(){
        listOfPhoneNumbers = Arrays.asList("0400000001", "0400000002", "0400000003");

        Phone phoneDetails1 = new Phone(1, "Cust001", "0460000001", 'N');
        Phone phoneDetails2 = new Phone(2, "Cust002", "0460000002", 'N');

        listOfPhoneDetails = Arrays.asList(phoneDetails1, phoneDetails2);
    }

    @Test
    public void testGetAllPhoneDetails(){
        Mockito.when(phoneService.getAllPhoneDetails()).thenReturn(listOfPhoneDetails);

        final List<Phone> allPhoneDetails = phoneController.getAllPhoneDetails();

        Mockito.verify(phoneService).getAllPhoneDetails();
        assertEquals(2, allPhoneDetails.size());
    }

    @Test
    public void testGetAllThePhoneNumbers(){
        Mockito.when(phoneService.getAllPhoneNumbers()).thenReturn(listOfPhoneNumbers);

        final List<String> allThePhoneNumbers = phoneController.getAllThePhoneNumbers();

        Mockito.verify(phoneService).getAllPhoneNumbers();
        assertEquals(3, allThePhoneNumbers.size());
    }

    @Test
    public void testGetAllPhoneNumbersByCustomerId(){
        Mockito.when(phoneService.getAllPhoneNumbersByCustomer("CUST001"))
                .thenReturn(listOfPhoneNumbers);

        final List<String> allThePhoneNumbers = phoneController.getAllPhoneNumbersByCustomerId("CUST001");

        Mockito.verify(phoneService).getAllPhoneNumbersByCustomer("CUST001");
        assertEquals(3, allThePhoneNumbers.size());
    }


    @Test
    public void testActivatePhoneNumber(){
        Mockito.when(phoneService.activatePhoneNumber("0460000001"))
                .thenReturn(true);

        final boolean isActivated = phoneController.activatePhoneNumber("0460000001");

        Mockito.verify(phoneService).activatePhoneNumber("0460000001");
        assertEquals(true, isActivated);
    }
}
