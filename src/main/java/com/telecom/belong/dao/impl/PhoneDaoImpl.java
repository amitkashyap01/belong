package com.telecom.belong.dao.impl;

import com.telecom.belong.dao.PhoneDao;
import com.telecom.belong.entity.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PhoneDaoImpl implements PhoneDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getAllPhoneNumbers() {
        LOGGER.debug("PhoneDaoImpl: getAllPhoneNumbers: called");
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        final Root<Phone> root = criteriaQuery.from(Phone.class);

        criteriaQuery.select(root.get("phoneNumber"));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<String> getAllPhoneNumbersByCustomer(String customerId) {
        LOGGER.debug("PhoneDaoImpl: getAllPhoneNumbersByCustomer: called");
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        final Root<Phone> root = criteriaQuery.from(Phone.class);

        criteriaQuery.select(root.get("phoneNumber"))
                        .where(criteriaBuilder.equal(root.get("customerId"), customerId));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public int activatePhoneNumber(String phoneNumber) {
        LOGGER.debug("PhoneDaoImpl: activePhoneNumber: called");
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaUpdate<Phone> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Phone.class);
        final Root<Phone> root = criteriaUpdate.from(Phone.class);

        criteriaUpdate.set(root.get("active"), 'Y')
                        .where(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));

        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    @Override
    public List<Phone> getAllPhoneDetails() {
        LOGGER.debug("PhoneDaoImpl: getAllPhoneDetails: called");
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Phone> criteriaQuery = criteriaBuilder.createQuery(Phone.class);
        final Root<Phone> root  = criteriaQuery.from(Phone.class);

        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
