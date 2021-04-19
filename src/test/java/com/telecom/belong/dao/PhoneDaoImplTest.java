package com.telecom.belong.dao;

import static org.junit.Assert.assertEquals;

import com.telecom.belong.dao.impl.PhoneDaoImpl;
import com.telecom.belong.entity.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PhoneDaoImplTest {

    @InjectMocks
    PhoneDaoImpl phoneDao;

    @Mock
    EntityManager entityManager;

    @Mock
    CriteriaBuilder criteriaBuilder;

    @Mock
    CriteriaQuery<Phone> criteriaQueryPhone;

    @Mock
    CriteriaQuery<String> criteriaQueryString;

    @Mock
    CriteriaUpdate<Phone> criteriaUpdate;

    @Mock
    Root<Phone> root;

    @Mock
    TypedQuery<Phone> typedQueryPhone;

    @Mock
    TypedQuery<String> typedQueryString;

    @Mock
    private Path path;

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

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(String.class)).thenReturn(criteriaQueryString);
        Mockito.when(criteriaQueryString.from(Phone.class)).thenReturn(root);
        Mockito.when(criteriaQueryString.select(root.get("phoneNumber"))).thenReturn(criteriaQueryString);
        Mockito.when(entityManager.createQuery(criteriaQueryString)).thenReturn(typedQueryString);
        Mockito.when(typedQueryString.getResultList()).thenReturn(listOfPhoneNumers);

        final List<String> allPhoneNumbers = phoneDao.getAllPhoneNumbers();

        assertEquals(3, allPhoneNumbers.size());
    }


    @Test
    public void testGetAllPhoneNumbersByCustomer(){

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(String.class)).thenReturn(criteriaQueryString);
        Mockito.when(criteriaQueryString.from(Phone.class)).thenReturn(root);
        Mockito.when(criteriaQueryString.select(root.get("phoneNumber"))).thenReturn(criteriaQueryString);
        Mockito.when(entityManager.createQuery(criteriaQueryString)).thenReturn(typedQueryString);
        Mockito.when(typedQueryString.getResultList()).thenReturn(listOfPhoneNumers);


        final List<String> allPhoneNumbers = phoneDao.getAllPhoneNumbersByCustomer("CUST001");

        Mockito.verify(criteriaQueryString)
                .where(criteriaBuilder.equal(root.get("customerId"), "CUST001"));

        assertEquals(3, allPhoneNumbers.size());
    }

    @Test
    public void testGetAllPhoneDetails(){
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(Phone.class)).thenReturn(criteriaQueryPhone);
        Mockito.when(criteriaQueryPhone.from(Phone.class)).thenReturn(root);
        Mockito.when(entityManager.createQuery(criteriaQueryPhone)).thenReturn(typedQueryPhone);
        Mockito.when(typedQueryPhone.getResultList()).thenReturn(listOfPhoneDetails);

        final List<Phone> allPhoneDetails = phoneDao.getAllPhoneDetails();

        assertEquals(2, listOfPhoneDetails.size());
    }

    @Test
    public void testActivatePhoneNumber(){
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createCriteriaUpdate(Phone.class)).thenReturn(criteriaUpdate);
        Mockito.when(criteriaUpdate.from(Phone.class)).thenReturn(root);

        Mockito.when(criteriaUpdate.set(root.get("active"), 'Y')).thenReturn(criteriaUpdate);
        Mockito.when(entityManager.createQuery(criteriaUpdate)).thenReturn(typedQueryPhone);
        Mockito.when(typedQueryPhone.executeUpdate()).thenReturn(1);

        final int number = phoneDao.activatePhoneNumber("04600000001");

        Mockito.verify(criteriaUpdate)
                .where(criteriaBuilder.equal(root.get("phoneNumber"), "04600000001"));

        assertEquals(1, number);
    }
}


